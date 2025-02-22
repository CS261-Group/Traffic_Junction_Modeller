package uk.ac.warwick.dcs.evaluation.metrics;

public interface IMetric {
    double getAverageWaitTime();
    void updateAverageWaitTime();
    double getMaxQueueLength();
    void updateMaxQueueLength();
    double getMaxWaitTime();
    void updateMaxWaitTime();
}
