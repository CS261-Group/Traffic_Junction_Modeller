package uk.ac.warwick.dcs.evaluation;

// ADD MATH LIBRARY

public class LaneMetrics {

    // time dependent, fixed traffic light expression from Akcelik 1980
    // right now evaluates one movement -
    // one path that can be taken from in to out
    // variable names are taken from the paper
    // x = degreeOfSaturation
    // g = effectiveGreenTime (seconds)
    // s = saturation (veh/hour)
    // C = capacity rate ()
    public static double averageOverflowQueue(double x, double g, double S, double C){
        // x1 = significant overflow queue saturation
        // positive when overflow queue is non-negligible
        // negative when overflow queue can be ignored
        // grouping together x - x0 term
        // x - maximum Saturation for negligible overflow
        double x1 = x - (0.67 + (S * g) / 600);

        // return value, returning the average overflow queue
        // at the end of a cycle
        double q0;

        // T = 1
        if (x1 > 0){
            q0 = (C/4) * ((x-1) + Math.sqrt((x-1)*(x-1) + (12*x1)/C));
        } else {
            q0 = 0;
        }

        return q0;
    }

    // non negative x
    public static double averageDelay(double x, double g, double S, double c, double q, double C){
        // d = average uniform delay
        double d;

        if (x < 1){
            d = (c * (1-g/c)*(1-g/c)) / (2*(1-q/S));
        } else{ //x >= 1
            d = (c-g)/2;
        }

        d = d + (averageOverflowQueue(x,g,S,C) / C);

        return d;
    }
}
