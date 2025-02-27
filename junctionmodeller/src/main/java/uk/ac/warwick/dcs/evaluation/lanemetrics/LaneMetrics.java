package uk.ac.warwick.dcs.evaluation.lanemetrics;

// per lane
public class LaneMetrics implements ILaneMetrics {
    private double averageDelay;
    private double avgQueueLength;
    private double maxQueueLength; // not currently used

    // shares this instance with Junction Metrics
    private final LaneMetricCalculator calculator;

    public LaneMetrics(LaneMetricCalculator calc,
                       double laneArrivalRate,
                       double junctionCycleTime,
                       double laneSaturatedDepartureRate,
                       double groupGreenTime){
        calculator = calc;

        computeMetrics(laneArrivalRate,
                junctionCycleTime,
                laneSaturatedDepartureRate,
                groupGreenTime);
    }

    /**
     * q - laneArrivalRate
     * c - cycle time (global for junction)
     * x - SaturatedDepartureRate
     * g - groupGreenTime (for group
     */
    private void computeMetrics(double q, double c, double s, double g){
        double C = calculator.capacity(s, g, c);
        double x = calculator.degreeOfSaturation(q, C);

        averageDelay = calculator.averageUniformDelay(c, g, x, q, s) +
                calculator.averageOverflowDelay(C, x, s, g);

        avgQueueLength = calculator.averageUniformQueue(x, c, g, q) +
                calculator.averageOverflowQueue(C, x, s, g);
    }

    @Override
    public double getAverageDelay() {
        return averageDelay;
    }

    @Override
    public double getAverageQueueLength() {
        return avgQueueLength;
    }

    @Override
    public double getMaxQueueLength(){
        return maxQueueLength;
    }
}
