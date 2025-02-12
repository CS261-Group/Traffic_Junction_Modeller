package uk.ac.warwick.dcs.optimisation;

public class FixedTimingsOptimiser extends TrafficLightTimingsOptimiser{
    @Override
    public int[] getMaxGroupTimings() {
        return new int[0];
    }
}
