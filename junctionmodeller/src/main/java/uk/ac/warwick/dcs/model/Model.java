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

//NOTE: no longer runnable
/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
class Model implements Runnable {
    private static int NUM_ITERATIONS = 10000;

    private final long id;
    private final Evaluator evaluation;
    private final Optimiser optimiser;
    private final IModelVisualisation visualisation;
    // Note: fields inside junction config are never updated,
    // optimised junction values are held in junction data.
    // TODO: if we want to be able to save optimised junction we
    //  need a class to update junctionConfig with junctionData values
    private final JunctionConfiguration junctionConfig;
    private final JunctionData junctionData; // holds optimised values

    public Model(long id, JunctionConfiguration junctionConfig, IModelVisualisation visualisation) {
        this.id = id;
        this.junctionConfig = junctionConfig;
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

    /**
     * Optimise the model, finding the best values that minimise the evaluation function.
     * @return can ignore, returns a reference to internal model data
     */
    public JunctionData optimiseModel() {
        optimiser.optimiseAll(NUM_ITERATIONS);
        return junctionData;
    }

    @Override
    public int hashCode() { return (int)id; }

    /**
     * Goes through several iterations of optimisation if applicable
     * then updates its evaluation
     */
    public void runOnce() {
        if (optimiser != null){
            optimiser.optimiseAll(NUM_ITERATIONS);
        }
        evaluation.getEvaluation(junctionData);
        // update visualisation with new values
    }

    // should do something more useful
    @Override
    public void run() {
        // not optimising => evaluate and return
        if (optimiser == null) {
            Platform.runLater(() -> {
                JunctionMetrics metrics = evaluation.getEvaluation(junctionData);
                visualisation.notify(new EvaluationUpdate(metrics));
            });
        } else {
            Platform.runLater(() -> {
                optimiser.optimiseAll(NUM_ITERATIONS);
                // TODO: visualiser data to avoid race condition
                JunctionMetrics metrics = evaluation.getEvaluation(junctionData);
                visualisation.notify(new OptimisationUpdate(junctionData));
                visualisation.notify(new EvaluationUpdate(metrics));
            });
        }
    }
}
