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

    /**
     * Uses equation 1 in Akcelik, Chung and Besley, Recent research... 2001
     * Modified to find g, from g = y * c/x
     * eh is the extension headway time gap
     */
    public double actuatedGreenTimeForGroup(double y, double eh, double c){
        return c / (1.5 * Math.pow(y, -0.5) * Math.pow(eh, -0.1));
    }
}
