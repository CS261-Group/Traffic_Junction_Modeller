package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.optimisation.Optimiser;

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
