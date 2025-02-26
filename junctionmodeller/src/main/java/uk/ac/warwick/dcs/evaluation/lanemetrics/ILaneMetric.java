package uk.ac.warwick.dcs.evaluation.lanemetrics;

public interface ILaneMetric {
    public double getAverageWaitTime();
    public void updateAverageWaitTime();

    public double getAverageQueueLength();
    public void updateAverageQueueLength();

    public double getMaxQueueLength();
    public void updateMaxQueueLength();
}
