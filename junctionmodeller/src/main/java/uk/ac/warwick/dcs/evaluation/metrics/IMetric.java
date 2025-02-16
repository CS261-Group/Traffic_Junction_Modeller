package uk.ac.warwick.dcs.evaluation.metrics;

public interface IMetric {
    public double getAverageWaitTime();
    public void updateAverageWaitTime();
    public double getMaxQueueLength();
    public void updateMaxQueueLength();
    public double getMaxWaitTime();
    public void updateMaxWaitTime();
}
