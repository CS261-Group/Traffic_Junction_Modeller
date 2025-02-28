package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;
import uk.ac.warwick.dcs.optimisation.Optimiser;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
class Model implements Runnable {
    private final long id;
    private final Optimiser optimiser;
    private final Evaluation evaluation;
    private final IModelVisualisation visualisation;
    private boolean running;

    public Model(long id, IModelVisualisation visualisation) {
        this.id = id;
        this.optimiser = new Optimiser();
        this.evaluation = new Evaluation();
        this.visualisation = visualisation;
        this.running = false;
    }

    /**
     *
     * @return The ID of this model instance.
     */
    public long getId() { return id; }

    public Optimiser getOptimiser() {
        return optimiser;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    /**
     * Evaluate the model using a specific junction configuration.
     * @param junctionConfiguration The configuration for the junction to be evaluated.
     * @return JunctionMetrics for the given junction configuration.
     */
    public JunctionMetrics evaluateModel(JunctionConfiguration junctionConfiguration) {
        return evaluation.getEvaluation(junctionConfiguration);
    }


    /**
     * Optimise the model using a specific junction configuration.
     * @param junctionConfiguration The configuration for the junction to be evaluated.
     */
    public void optimiseModel(JunctionConfiguration junctionConfiguration) {
        //TO DO: implement this 
    }

    @Override
    public int hashCode() { return (int)id; }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            System.out.println("Sending update.");

            // if there is a visualisation, send the updates as required
            if (visualisation != null) {
                visualisation.notify(new ModelUpdate());
            }

            // TODO: remove this busy-wait
            try {
                Thread.sleep(500L);
            } catch (InterruptedException ex) {

            }
        }
    }
}
