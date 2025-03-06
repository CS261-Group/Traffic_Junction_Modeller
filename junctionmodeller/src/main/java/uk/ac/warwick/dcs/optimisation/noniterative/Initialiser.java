package uk.ac.warwick.dcs.optimisation.noniterative;

public abstract class Initialiser {

    CycleTimeOptimiser cycleTimeOptimiser = new CycleTimeOptimiser();
    GreenTimeEstimator greenTimeEstimator = new GreenTimeEstimator();

    double[] maxFlowRatios = junctionData.getMaxFlowRatioForEachGroup();
    double sumFlowRatios = getSumFlowRatios(maxFlowRatios);


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
