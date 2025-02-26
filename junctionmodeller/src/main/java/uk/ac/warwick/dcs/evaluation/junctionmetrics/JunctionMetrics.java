package uk.ac.warwick.dcs.evaluation.junctionmetrics;

public class JunctionMetrics implements IJunctionMetrics {

    double averageDelay;  // avg of delay averages
    double MaxDelay;  //max of delay averages
    double MaxQueue;  //maximum of maximum queue lengths
    double AvgQueue;  //avg of average queue lengths

    @Override
    public double getAverageDelay() {
        return 0;
    }

    @Override
    public void updateAverageDelay() {

    }

    @Override
    public double getMaxDelay() {
        return 0;
    }

    @Override
    public void updateMaxDelay() {

    }

    @Override
    public int getMaxQueue() {
        return 0;
    }

    @Override
    public void updateMaxQueue() {

    }

    @Override
    public int getAvgQueue() {
        return 0;
    }

    @Override
    public void updateAvgQueue() {

    }
}
