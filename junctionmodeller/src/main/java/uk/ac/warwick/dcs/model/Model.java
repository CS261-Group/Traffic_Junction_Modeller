package uk.ac.warwick.dcs.model;

import javafx.application.Platform;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.model.messaging.EvaluationUpdate;
import uk.ac.warwick.dcs.model.messaging.OptimisationUpdate;
import uk.ac.warwick.dcs.optimisation.ActuatedTimingsOptimiser;
import uk.ac.warwick.dcs.optimisation.FixedTimingsOptimiser;
import uk.ac.warwick.dcs.optimisation.Optimiser;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

import java.util.concurrent.Semaphore;

/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
class Model implements Runnable {
    private static final int NUM_ITERATIONS = 10000;

    private final long id;
    private final Evaluator evaluation;
    private final Optimiser optimiser;
    private final IModelVisualisation visualisation;
    private final JunctionData junctionData; // holds optimised values

    private volatile boolean running;

    public Model(long id, JunctionConfiguration junctionConfig, IModelVisualisation visualisation) {
        this.id = id;
        this.junctionData = new JunctionData(junctionConfig);
        this.evaluation = new Evaluator(junctionConfig); // does not evaluate yet, just creates class

        // optimiser initialises values
        if (junctionConfig.getOptimising()) {
            if (junctionConfig.getTrafficLightType() == TrafficLightType.FIXEDCYCLE) {
                this.optimiser = new FixedTimingsOptimiser(junctionConfig, junctionData, evaluation);
            }
            else {
                this.optimiser = new ActuatedTimingsOptimiser(junctionConfig, junctionData, evaluation);
            }
        } else {
            this.optimiser = null;
        }

        this.visualisation = visualisation;
    }

    /**
     * @return The ID of this model instance.
     */
    public long getId() { return id; }

    /**
     * Evaluate the model using its own junction configuration.
     * @return JunctionMetrics for the junction configuration.
     */
    public JunctionMetrics evaluateModel() {
        return evaluation.getEvaluation(junctionData);
    }

    @Override
    public int hashCode() { return (int)id; }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;

        Semaphore semaphore = new Semaphore(0);

        // if optimising
        if (optimiser != null) {
            while (running && !Thread.currentThread().isInterrupted()) {
                optimiser.optimise(NUM_ITERATIONS);
                JunctionMetrics metrics = evaluation.getEvaluation(junctionData);

                Platform.runLater(() -> {
                    try {
                        if (optimiser instanceof FixedTimingsOptimiser) { // optimising fixed timings
                            visualisation.notify(new OptimisationUpdate<>(junctionData.getFixedCycleVisualisationData()));

                        } else { // optimising actuation
                            assert optimiser instanceof ActuatedTimingsOptimiser;
                            visualisation.notify(new OptimisationUpdate<>(junctionData.getActuationVisualisationData()));
                        }

                        visualisation.notify(new EvaluationUpdate(metrics));
                    } finally {
                        semaphore.release();
                    }
                });

                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // not optimising => evaluate and return
        // this update doesn't require semaphore trickery
        Platform.runLater(() -> {
            JunctionMetrics metrics = evaluation.getEvaluation(junctionData);
            visualisation.notify(new EvaluationUpdate(metrics));
        });

        running = false;
    }
}
