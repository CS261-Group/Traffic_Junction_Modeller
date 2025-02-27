package uk.ac.warwick.dcs.evaluation.junctionmetrics;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.evaluation.lanemetrics.ActuatedLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.FixedtimeLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetrics;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetricCalculator;

/**
 * JunctionMetrics object is created everytime a junction is evaluated
 * Its purpose is to aggregate metrics from individual lanes
 *
 */
public class JunctionMetrics implements IJunctionMetrics {

    private double avgDelay;  // avg of delay averages
    private double maxDelay;  // max of delay averages

    // not using maximum queue metric at the moment
    private double maxQueue;  // maximum of queue length averages
    private double avgQueue;  // avg of queue length averages

    // must be an instance so the class can be threadable
    private final LaneMetricCalculator calculator;

    // TODO take in junction data and create laneMetric classes from it
    public JunctionMetrics(TrafficLightType type){
        // create a calculator
        if (type == TrafficLightType.ACTUATION) {
            calculator = new ActuatedLaneMetricCalculator();
        } else {
            calculator = new FixedtimeLaneMetricCalculator();
        }

        //create lane metrics (evaluate each lane)
        LaneMetrics[] laneMetrics;

        //updateDelayMetrics(laneMetrics);
        //updateQueueMetrics(laneMetrics);
    }

    /**
     * Takes the average of wait times for each lane, and finds the maximum wait time
     * @param laneMetrics List of lane metrics
     */
    private void updateDelayMetrics(LaneMetrics[] laneMetrics) {
        avgDelay = 0;

        for (int i = 0; i <= laneMetrics.length; i++){
            avgDelay += laneMetrics[i].getAverageDelay();

            if (laneMetrics[i].getAverageDelay() > maxDelay){
                maxDelay = laneMetrics[i].getAverageDelay();
            }
        }

        avgDelay /= laneMetrics.length;
    }

    @Override
    public double getAverageDelay() {
        return avgDelay;
    }

    @Override
    public double getMaxDelay() {
        return maxDelay;
    }

    /**
     * Takes the average of queue lengths for each lane, and finds the maximum queue length
     * @param laneMetrics List of lane metrics
     */
    private void updateQueueMetrics(LaneMetrics[] laneMetrics) {
        avgQueue = 0;

        for (int i = 0; i <= laneMetrics.length; i++){
            avgQueue += laneMetrics[i].getAverageQueueLength();

            if (laneMetrics[i].getAverageQueueLength() > maxDelay){
                maxQueue = laneMetrics[i].getAverageQueueLength();
            }
        }

        avgQueue /= laneMetrics.length;
    }

    @Override
    public double getMaxQueue() {
        return maxQueue;
    }

    @Override
    public double getAverageQueue() {
        return avgQueue;
    }
}
