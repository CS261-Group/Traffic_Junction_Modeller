package uk.ac.warwick.dcs.optimisation;


import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.FixedLightsTimingSearchSpace;
import uk.ac.warwick.dcs.optimisation.localsearch.HillClimb;
import uk.ac.warwick.dcs.optimisation.noniterative.FixedCycleAndGreenTimeInitialiser;

public class FixedTimingsOptimiser extends Optimiser {
    HillClimb<FixedLightsTimingSearchSpace> localSearch;

    public FixedTimingsOptimiser(JunctionConfiguration junctionConfig, JunctionData junctionData, Evaluator evaluation){
        super(junctionConfig, junctionData, evaluation);

        // junctions being optimised need initial data
        new FixedCycleAndGreenTimeInitialiser(junctionData, junctionConfig.getCycleLostTime());

        FixedLightsTimingSearchSpace searchSpace = new FixedLightsTimingSearchSpace(junctionConfig.getNumberOfGroups());
        localSearch = new HillClimb<>(junctionData, evaluationFunction, searchSpace);
    }

    @Override
    public void optimise(int numIterations) {
        this.greenTimings(numIterations);
    }

    public void greenTimings(int numIterations){
        for (int t = 0; t < numIterations; t++) {
            if (!localSearch.makeStep()) {
                return;
            }
        }
    }

}
