package uk.ac.warwick.dcs.optimisation;

public class ActuatedTimingsOptimiser extends TrafficLightTimingsOptimiser{

    @Override
    public int[] getMaxGroupTimings() {
        return new int[0];
    }

    public int[] getMinGroupTimings() {
        return new int[0];
    }
}
