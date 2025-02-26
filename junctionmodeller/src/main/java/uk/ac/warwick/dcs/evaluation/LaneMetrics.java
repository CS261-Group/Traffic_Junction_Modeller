package uk.ac.warwick.dcs.evaluation;

import java.lang.Math;

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
            q0 = (C/4) * ((x-1) + Math.sqrt((x-1)*(x-1) + 12*x1/C));
        } else {
            q0 = 0;
        }

        return q0;
    }

    // c = cycle length
    // q = arrival rate
    public static double averageDelay(double x, double g, double S, double c, double q, double C){
        // d = average uniform delay
        double d;

        if (x < 1){
            d = (c * (1-g/c)*(1-g/c)) / (2*(1-q/S));
        } else{ //x >= 1
            d = (c-g)/2; //0.5r
        }

        d = d + (LaneMetrics.averageOverflowQueue(x,g,S,C) / C);

        return d;
    }

    public static double degreeOfSaturation(double q, double c, double s, double g){

        return (q * c) / (s * g);
    }

    public static int maximumQueueLength(){
        return  0;
    }

    // based on Research Report 67
    //https://www.eastleigh.gov.uk/media/8592/cd58.pdf
    // input and output in seconds
    // may vary for actuated lights
    public static double greenTimeToEffectiveGreenTime(double tg){
        return tg - 1.2;
    }

    public static int maximumQueue(){ return 0;}

    public static int averageGreenTimeStartQueue(){ return 0;}

}
