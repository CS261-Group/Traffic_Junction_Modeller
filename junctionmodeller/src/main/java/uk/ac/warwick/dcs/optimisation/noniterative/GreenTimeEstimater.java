package uk.ac.warwick.dcs.optimisation.noniterative;

public class GreenTimeEstimater {
    /**
     * 5.4 in Chow, Ampountolas
     * L is the total lost time
     * c is the cycle time
     * y is max flow ratio for that group
     * Y is the sum of max flow ratios.
     */
    public double greenTimeForGroup(double c, double L, double y, double Y){
        return (c - L) * y / Y;
    }
}
