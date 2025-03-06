package uk.ac.warwick.dcs.evaluation.junctionmetrics;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.evaluation.junctiondata.ActuatedGroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.GroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctiondata.LaneData;
import uk.ac.warwick.dcs.evaluation.lanemetrics.ActuatedLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.FixedtimeLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetrics;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetricCalculator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * JunctionMetrics object is created everytime a junction is evaluated
 * Its purpose is to aggregate metrics from individual lanes
 */
public class JunctionMetrics implements IJunctionMetrics {

    private double avgDelay;  // avg of delay averages
    private double maxDelay;  // max of delay averages

    // not using maximum queue metric at the moment
    private double maxQueue;  // maximum of queue length averages
    private double avgQueue;  // avg of queue length averages

    // fixed traffic light case
    public JunctionMetrics(FixedtimeLaneMetricCalculator calculator, JunctionData junctionData){
        //create lane metrics (evaluate each lane)
        ArrayList<LaneMetrics> laneMetricsList = new ArrayList<>();

        for (Iterator<GroupData> data = junctionData.iteratorF(); data.hasNext(); ) {
            GroupData group = data.next();

            for (LaneData lane : group){
                laneMetricsList.add(new LaneMetrics(calculator,
                        lane.getSaturationFlow(),
                        junctionData.getCycleTime(),
                        lane.getArrivalFlow(),
                        group.getGreenTime()));
            }
        }

        // aggregate metrics
        updateDelayMetrics(laneMetricsList);
        updateQueueMetrics(laneMetricsList);
    }

    // actuated traffic light case
    public JunctionMetrics(ActuatedLaneMetricCalculator calculator, JunctionData junctionData){
        //create lane metrics (evaluate each lane)
        ArrayList<LaneMetrics> laneMetricsList = new ArrayList<>();

        for (Iterator<ActuatedGroupData> data = junctionData.iteratorA(); data.hasNext(); ) {
            ActuatedGroupData group = data.next();
            group.setGreenTimeAndDegOfSat(junctionData.getCycleTime());

            for (LaneData lane : group) {
                laneMetricsList.add(new LaneMetrics(calculator,
                        lane.getSaturationFlow(),
                        junctionData.getCycleTime(),
                        lane.getArrivalFlow(),
                        group.getGreenTime(),
                        group.getDegOfSaturation()));
            }
        }

        // aggregate metrics
        updateDelayMetrics(laneMetricsList);
        updateQueueMetrics(laneMetricsList);
    }

    /**
     * Takes the average of wait times for each lane, and finds the maximum wait time
     * @param laneMetrics List of lane metrics
     */
    private void updateDelayMetrics(ArrayList<LaneMetrics> laneMetrics) {
        avgDelay = 0;

        for (int i = 0; i < laneMetrics.size(); i++){
            avgDelay += laneMetrics.get(i).getAverageDelay();

            if (laneMetrics.get(i).getAverageDelay() > maxDelay){
                maxDelay = laneMetrics.get(i).getAverageDelay();
            }
        }

        avgDelay /= laneMetrics.size();
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
    private void updateQueueMetrics(ArrayList<LaneMetrics> laneMetrics) {
        avgQueue = 0;

        for (int i = 0; i < laneMetrics.size(); i++){
            avgQueue += laneMetrics.get(i).getAverageQueueLength();

            if (laneMetrics.get(i).getAverageQueueLength() > maxDelay){
                maxQueue = laneMetrics.get(i).getAverageQueueLength();
            }
        }

        avgQueue /= laneMetrics.size();
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
