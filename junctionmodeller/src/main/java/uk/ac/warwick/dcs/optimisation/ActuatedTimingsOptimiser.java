package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.CycleTimeSearchSpace;
import uk.ac.warwick.dcs.optimisation.localsearch.HillClimb;
import uk.ac.warwick.dcs.optimisation.noniterative.ActuatedCycleAndGreenTimeInitialiser;

public class ActuatedTimingsOptimiser extends Optimiser {
    double junctionCycleLostTime; // used for re-initialising
    HillClimb<CycleTimeSearchSpace> localSearch;

    public ActuatedTimingsOptimiser(JunctionConfiguration junctionConfiguration, JunctionData junctionData, Evaluator evaluation){
        super(junctionConfiguration, junctionData, evaluation);
        this.junctionCycleLostTime = junctionConfig.getCycleLostTime();

        // junctions being optimised need initial data
        new ActuatedCycleAndGreenTimeInitialiser(junctionData, junctionCycleLostTime);

        CycleTimeSearchSpace searchSpace = new CycleTimeSearchSpace(junctionCycleLostTime, junctionData.getTotalMaxGreenTimes());
        localSearch = new HillClimb<>(junctionData, evaluationFunction, searchSpace);
    }

    @Override
    public void optimiseAll(int numIterations) {
        this.cycleTiming(numIterations);
    }

    public void cycleTiming(int numIterations) {
        for (int t = 0; t < numIterations; t++) {
            if (!localSearch.makeStep()) {
                return;
            }
        }
    }
}
