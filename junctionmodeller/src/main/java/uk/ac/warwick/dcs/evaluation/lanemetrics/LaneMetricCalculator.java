package uk.ac.warwick.dcs.evaluation.lanemetrics;

// Sources
// Akcelik 2000
// https://www.sidrasolutions.com/learn/publications/hcm-2000-back-queue-model-signalised-intersections
// Akcelik 1997
// https://www.sidrasolutions.com/learn/publications/recent-research-actuated-signal-timing-and-performance-evaluation-and-its

//lane basis
// may need converting to groups for actuated
// see Akçelik - HCM 2000 Queue Model

//index of terms

// C = Capacity (veh/hour)
// x = degree of saturation (%)
// s = saturation (veh/hour)
// g = effective green time (seconds)
// c = cycle time
// r = red time
public abstract class LaneMetricCalculator {

    double T = 1;

    public LaneMetricCalculator() {

    }

    public static double greenTimeToEffectiveGreenTime(double tg){
        return tg - 1.2;
    }

    /**
     * Returns the calibration constant kB as specified in Akçelik 2000
     *
     * @param sg capacity per cycle (saturation in veh/sec * green time in sec) in veh
     * @return
     */
    public abstract double getCalibrationConstant(double sg);

    public double averageOverflowQueue(double C, double x, double s, double g){
        double z = (x - 1);
        double sg = s/3600 * g;
        double kB = this.getCalibrationConstant(sg);

        return (C*T/4) * (z + Math.sqrt(z*z + 8 * kB * x / (C * T)));
    }

    //1.1a in Akcelik 2000
    // x = sat
    // c = cycle time
    // TODO remove cached u variable
    public double averageUniformQueue(double x, double c, double g, double q){
        double u = g / c; // green time ratio

        if (x > 1){
            return q * c;
        }else{
            return q * x * (1 - u) / (1 - x * u);
        }
    }

    // Eq 24 in Akcelik 1980
    // qr = number of vehicles that arrive in red time (veh)
    // from q in (veh/s), r in (sec)
    // Qr is the capacity
    public double averageStationaryUniformQueue(double qr, double x, double Qr){
        return 0;
    }

    /**
     * Modified formula from Akcelik 1997
     *
     * @param C Capacity (veh/hour)
     * @param x degree of saturation (%)
     * @param s saturation (veh/hour)
     * @param g effective green time (seconds)
     * @return the average overflow delay in sec
     */
    public double averageOverflowDelay(double C, double x, double s, double g){
        return this.averageOverflowQueue(C, x, s, g) / C * 3600;
    }

    /**
     * Modified formula from Akcelik 1997
     *
     * @param x degree of saturation (%)
     * @param s saturation (veh/hour)
     * @param g effective green time (seconds)
     * @return
     */
    //TODO get rid of cached variables
    public double averageUniformDelay(double c, double g, double x, double q, double s){
        double r = c - g; //red time
        double y = q / s; // flow ratio
        double u = g / c; // green time ratio

        if (x <= 1) {
            return r * (1 - u) / ((1 - y) * 2);
        }
        else {
            return r / 2;
        }
    }

    public static double degreeOfSaturation(double q, double c, double s, double g){

        return (q * c) / (s * g);
    }
}
