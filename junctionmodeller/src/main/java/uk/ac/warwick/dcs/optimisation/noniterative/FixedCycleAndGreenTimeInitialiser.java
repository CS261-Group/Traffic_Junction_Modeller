package uk.ac.warwick.dcs.optimisation.noniterative;

import uk.ac.warwick.dcs.evaluation.junctiondata.GroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.Iterator;

// take junction data (fixed traffic light case)
// and initilaise cycle and green time values (assume we are optimising)
public class FixedCycleAndGreenTimeInitialiser extends Initialiser{

    public FixedCycleAndGreenTimeInitialiser(JunctionData junctionData, double cycleLostTime){
        CycleTimeOptimiser cycleTimeOptimiser = new CycleTimeOptimiser();
        GreenTimeEstimator greenTimeEstimator = new GreenTimeEstimator();

        double[] maxFlowRatios = junctionData.getMaxFlowRatioForEachGroup();
        double sumFlowRatios = getSumFlowRatios(maxFlowRatios);

        double optimalCycleTime = cycleTimeOptimiser.cycleTime(cycleLostTime, sumFlowRatios);
        junctionData.setCycleTime(optimalCycleTime);

        for (Iterator<GroupData> dataIterator = junctionData.iteratorF(); dataIterator.hasNext(); ) {
            GroupData group = dataIterator.next();
            double flowRatio = group.getMaxFlowRatio();
            double greenTime = greenTimeEstimator.greenTimeForGroup(optimalCycleTime, cycleLostTime, flowRatio, sumFlowRatios);
            group.setGreenTime(greenTime);
        }
    }


}
