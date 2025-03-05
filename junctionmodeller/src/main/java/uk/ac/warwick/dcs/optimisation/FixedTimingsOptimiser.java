package uk.ac.warwick.dcs.optimisation;


import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.Evaluator;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.localsearch.FixedLightsTimingSearchSpace;
import uk.ac.warwick.dcs.optimisation.localsearch.HillClimb;

public class FixedTimingsOptimiser extends Optimiser {
    HillClimb<FixedLightsTimingSearchSpace> localSearch;

    public FixedTimingsOptimiser(JunctionConfiguration junctionConfig, JunctionData junctionData, Evaluator evaluation){
        super(junctionConfig, junctionData, evaluation);

        FixedLightsTimingSearchSpace searchSpace = new FixedLightsTimingSearchSpace(junctionConfig.getNumberOfGroups());
        localSearch = new HillClimb<>(junctionData, evaluationFunction, searchSpace);
    }

    @Override
    public void optimiseAll() {
        this.greenTimings();
    }

    public void greenTimings(){
        for (int t = 0; t < ITERATIONS; t++) {
            if (!localSearch.makeStep()) {
                return;
            }
        }
    }

}
