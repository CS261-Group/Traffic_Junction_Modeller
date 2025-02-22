package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.optimisation.Optimiser;

/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
public class Model {
    private Optimiser optimiser;
    private Evaluation evaluation;

    public Model() {
        this.optimiser = new Optimiser();
        this.evaluation = new Evaluation();
    }

    public Optimiser getOptimiser() {
        return optimiser;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
}
