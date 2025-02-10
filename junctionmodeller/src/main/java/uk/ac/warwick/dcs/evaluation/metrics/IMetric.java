package uk.ac.warwick.dcs.evaluation.metrics;

public interface IMetric {
    public void getAverageWaitTime();
    public void updateAverageWaitTime();
    public void getMaxQueueLength();
    public void updateMaxQueueLength();
    public void getMaxWaitTime();
    public void updateMaxWaitTime();
}
