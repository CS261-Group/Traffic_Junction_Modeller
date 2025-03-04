package uk.ac.warwick.dcs.optimisation;

/**
 * Static (Does not do any kind of iterative optimisation)
 * optimiser used to initialise green times later
 * See <a href="https://www.sciencedirect.com/science/article/pii/B9780128153024000030">Chow, Ampountolas</a>
 */
public class CycleTimeOptimiser {
    public CycleTimeOptimiser(){};

    /**
     * y is an array of flow ratios, one for each group
     */
    private double getSumFlowRatios(double[] y){
        double Y = 0;
            for (int i = 0; i < y.length; i++){
                Y += y[i];
            }
        return Y;
    }

    /**
     * 5.3 in Chow, Ampountolas
     * L is the total lost time
     */
    public double getCycleTime(double L, double[] y){
        return (1.5*L + 5) / (1 - getSumFlowRatios(y));
    }
}
