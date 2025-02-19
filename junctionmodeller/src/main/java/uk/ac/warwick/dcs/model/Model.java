package uk.ac.warwick.dcs.model;

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
