package uk.ac.warwick.dcs.evaluation.junctionmetrics;

public interface IJunctionMetrics {
    public double getAverageDelay();
    public void updateAverageDelay();

    public double getMaxDelay();
    public void updateMaxDelay();

    public int getMaxQueue();
    public void updateMaxQueue();

    public int getAvgQueue();
    public void updateAvgQueue();
}
