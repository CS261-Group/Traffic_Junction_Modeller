package uk.ac.warwick.dcs.optimisation.noniterative;

import uk.ac.warwick.dcs.evaluation.junctiondata.ActuatedGroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.GroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.Iterator;

public class ActuatedCycleAndGreenTimeInitialiser extends Initialiser{
    public ActuatedCycleAndGreenTimeInitialiser(JunctionData junctionData, double cycleLostTime){
        CycleTimeOptimiser cycleTimeOptimiser = new CycleTimeOptimiser();
        GreenTimeEstimator greenTimeEstimator = new GreenTimeEstimator();

        double[] maxFlowRatios = junctionData.getMaxFlowRatioForEachGroup();
        double sumFlowRatios = getSumFlowRatios(maxFlowRatios);

        double averageCycleTime = cycleTimeOptimiser.averageCycleTime(cycleLostTime, sumFlowRatios);
        junctionData.setCycleTime(averageCycleTime);

        for (Iterator<ActuatedGroupData> dataIterator = junctionData.iteratorA(); dataIterator.hasNext(); ) {
            ActuatedGroupData group = dataIterator.next();
            double flowRatio = group.getMaxFlowRatio();
            double extensionHeadway = group.getExtensionHeadway();
            double greenTime = greenTimeEstimator.actuatedGreenTimeForGroup(flowRatio, extensionHeadway, averageCycleTime);
            group.setGreenTime(greenTime);
        }
    }
}
