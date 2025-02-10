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
    // c = capacity rate ()
    public static double averageOverflowQueue(float x, float g, float s, float c){
        // x1 = significant overflow queue saturation
        // positive when overflow queue is non-negligible
        // negative when overflow queue can be ignored
        // grouping together x - x0 term
        // x - maximum Saturation for negligible overflow
        float x1 = x - (0.67f + (s * g) / 600);

        // return value, returning the average overflow queue
        // at the end of a cycle
        double q0;

        // T = 1
        if (x1 > 0){
            q0 = (c/4) * ((x-1) + Math.sqrt((x-1)*(x-1) + (12*x1)/c));
        } else {
            q0 = 0;
        }

        return q0;
    }
}
