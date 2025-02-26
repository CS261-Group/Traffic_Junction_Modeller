package uk.ac.warwick.dcs.evaluation.lanemetrics;

// per lane
public class LaneMetric implements ILaneMetric {
    private double averageWaitTime;
    private double maxQueueLength;
    private double maxWaitTime;
    private final LaneMetricCalculator calculator = new FixedtimeLaneMetricCalculator(); // not really needed

    public void updateAverageWaitTime(){
    }

    @Override
    public double getAverageQueueLength() {
        return 0;
    }

    @Override
    public void updateAverageQueueLength() {

    }

    public double getAverageWaitTime(){
        return averageWaitTime;
    }
    public void updateMaxQueueLength(){

    }
    public double getMaxQueueLength(){
        return maxQueueLength;
    }
    public void updateMaxWaitTime(){

    }
    public double getMaxWaitTime(){
        return maxWaitTime;
    }
    
}
