package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.EvaluationFunction;
import uk.ac.warwick.dcs.optimisation.noniterative.FixedCycleAndGreenTimeInitialiser;

public abstract class Optimiser {
    protected final int ITERATIONS = 100;
    protected final JunctionConfiguration junctionConfig;
    protected final JunctionData junctionData;
    protected final EvaluationFunction evaluationFunction;

    public Optimiser(JunctionConfiguration junctionConfig, JunctionData junctionData, Evaluator evaluation) {
        this.junctionConfig = junctionConfig;
        this.junctionData = junctionData;

        // create evaluation function
        this.evaluationFunction = new EvaluationFunction(evaluation);
    }

    public abstract void optimiseAll();
}
