package uk.ac.warwick.dcs.evaluation;

public class MovementToLaneMetrics {
    public static double sumArrivalRate(double[] qLs){
        double qSum = 0;

        for (int i = 0; i < qLs.length; i++){
            qSum += qLs[i];
        }

        return qSum;
    }

    public static double averageServiceRate(double[] qLs, double qSum, double[] sLs){
        double sAvg = 0;

        for (int i = 0; i < qLs.length; i++){
            sAvg += qLs[i] * sLs[i];
        }

        sAvg /= qSum;

        return sAvg;
    }
}
