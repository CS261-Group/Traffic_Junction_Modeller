package uk.ac.warwick.dcs.evaluation.junctionmetrics;

public interface IJunctionMetrics {
    double getAverageDelay();
    double getMaxDelay();
    double getMaxQueue();
    double getAverageQueue();
}
