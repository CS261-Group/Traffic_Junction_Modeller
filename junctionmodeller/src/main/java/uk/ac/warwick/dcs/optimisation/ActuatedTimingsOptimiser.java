package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.CycleTimeSearchSpace;
import uk.ac.warwick.dcs.optimisation.localsearch.ExtensionHeadwaySearchSpace;
import uk.ac.warwick.dcs.optimisation.localsearch.HillClimb;
import uk.ac.warwick.dcs.optimisation.noniterative.ActuatedCycleAndGreenTimeInitialiser;

/**
 * Optimises the extension headway of groups,
 * and then the cycle time (which determines average green times)
 * for actuated junctions
 */
public class ActuatedTimingsOptimiser extends Optimiser {
    double junctionCycleLostTime; // used for re-initialising
    HillClimb<CycleTimeSearchSpace> localSearchCycleTime;
    HillClimb<ExtensionHeadwaySearchSpace> localSearchExtensionHeadway;

    public ActuatedTimingsOptimiser(JunctionConfiguration junctionConfiguration, JunctionData junctionData, Evaluator evaluation){
        super(junctionConfiguration, junctionData, evaluation);
        this.junctionCycleLostTime = junctionConfig.getCycleLostTime();

        // junctions being optimised need initial data
        new ActuatedCycleAndGreenTimeInitialiser(junctionData, junctionCycleLostTime);

        CycleTimeSearchSpace searchSpaceCT = new CycleTimeSearchSpace(junctionCycleLostTime, junctionData.getTotalMaxGreenTimes());
        localSearchCycleTime = new HillClimb<>(junctionData, evaluationFunction, searchSpaceCT);

        ExtensionHeadwaySearchSpace searchSpaceEH = new ExtensionHeadwaySearchSpace(junctionCycleLostTime, junctionData.getNumGroups());
        localSearchExtensionHeadway = new HillClimb<>(junctionData, evaluationFunction, searchSpaceEH);
    }

    /**
     * Optimise both extension headways and cycle time. Currently, never ends early
     * @param numIterations number of steps to make
     * @return true if all iterations are completed, false otherwise
     */
    @Override
    public boolean optimise(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            if (!localSearchExtensionHeadway.makeStep() & !localSearchCycleTime.makeStep()) {
                return false;
            }
        }
        return true;
    }
}
