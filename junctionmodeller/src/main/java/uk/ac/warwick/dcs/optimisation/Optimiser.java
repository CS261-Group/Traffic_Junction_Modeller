package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.EvaluationFunction;

public abstract class Optimiser {
    protected final JunctionConfiguration junctionConfig;
    protected final JunctionData junctionData;
    protected final EvaluationFunction evaluationFunction;

    public Optimiser(JunctionConfiguration junctionConfig, JunctionData junctionData, Evaluator evaluation) {
        this.junctionConfig = junctionConfig;
        this.junctionData = junctionData;

        // create evaluation function
        this.evaluationFunction = new EvaluationFunction(evaluation);
    }

    /**
     * Makes numIterations steps of a local search
     * @param numIterations number of steps to make
     */
    public abstract void optimise(int numIterations);

}
