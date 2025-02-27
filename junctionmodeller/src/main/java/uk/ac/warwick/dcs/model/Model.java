package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.optimisation.Optimiser;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
class Model {
    private final long id;
    private final Optimiser optimiser;
    private final Evaluation evaluation;

    public Model(long id) {
        this.id = id;
        this.optimiser = new Optimiser();
        this.evaluation = new Evaluation();
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
}
