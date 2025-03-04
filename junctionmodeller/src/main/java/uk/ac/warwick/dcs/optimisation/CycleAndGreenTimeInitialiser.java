package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.junctiondata.GroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

// take junction data (fixed traffic light case)
// and initilaise cycle and green time values (assume we are optimising)
public class CycleAndGreenTimeInitialiser {

    public CycleAndGreenTimeInitialiser(JunctionData junctionData, double cycleLostTime){
        CycleTimeOptimiser cycleTimeOptimiser = new CycleTimeOptimiser();
        GreenTimeEstimater greenTimeEstimator = new GreenTimeEstimater();

        double[] maxFlowRatios = junctionData.getMaxFlowRatioForEachGroup();
        double sumFlowRatios = getSumFlowRatios(maxFlowRatios);

        double optimalCycleTime = cycleTimeOptimiser.cycleTime(cycleLostTime, sumFlowRatios);
        junctionData.setCycleTime(optimalCycleTime);

        for (GroupData group : junctionData){
            double flowRatio = group.getMaxFlowRatio();
            double greenTime = greenTimeEstimator.greenTimeForGroup(optimalCycleTime, cycleLostTime, flowRatio, sumFlowRatios);
            group.setGreenTime(greenTime);
        }
    }

    /**
     * y is an array of flow ratios, one for each group
     */
    public double getSumFlowRatios(double[] y){
        double Y = 0;
        for (int i = 0; i < y.length; i++){
            Y += y[i];
        }
        return Y;
    }


}
