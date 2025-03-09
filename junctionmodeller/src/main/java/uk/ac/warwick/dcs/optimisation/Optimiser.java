package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.EvaluationFunction;

/**
 * Optimiser holds an instance of a junction
 * And optimises it to minimise a evaluation function
 */
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
    public abstract boolean optimise(int numIterations);

}
