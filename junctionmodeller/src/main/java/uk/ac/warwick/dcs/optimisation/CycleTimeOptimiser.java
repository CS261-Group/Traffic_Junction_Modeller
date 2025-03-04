package uk.ac.warwick.dcs.optimisation;

/**
 * Static (Does not do any kind of iterative optimisation)
 * optimiser used to initialise green times later
 * See <a href="https://www.sciencedirect.com/science/article/pii/B9780128153024000030">Chow, Ampountolas</a>
 */
public class CycleTimeOptimiser {
    /**
     * See 5.3 in Chow, Ampountolas
     * L is the total lost time for the cycle
     * Y is the sum of max group flow ratios
     */
    public double cycleTime(double L, double Y){
        return (1.5*L + 5) / (1 - Y);
    }
}
