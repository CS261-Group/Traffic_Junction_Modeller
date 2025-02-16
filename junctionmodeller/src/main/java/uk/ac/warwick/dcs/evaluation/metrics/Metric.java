package uk.ac.warwick.dcs.evaluation.metrics;

public class Metric implements IMetric {
    private double averageWaitTime;
    private double maxQueueLength;
    private double maxWaitTime;
    private final MetricsCalculator calculator = new MetricsCalculator();
    //made junction modeller static so that the correct junction model is updated not an instance.

    public void updateAverageWaitTime(){
        averageWaitTime = calculator.AverageWaitTime();
    }
    public double getAverageWaitTime(){
        return averageWaitTime;
    }
    public void updateMaxQueueLength(){
        averageWaitTime = calculator.MaxQueueLength();
    }
    public double getMaxQueueLength(){
        return maxQueueLength;
    }
    public void updateMaxWaitTime(){
        averageWaitTime = calculator.MaxWaitTime();
    }
    public double getMaxWaitTime(){
        return maxWaitTime;
    }
    
}
