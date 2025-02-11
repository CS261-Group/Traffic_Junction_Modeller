package uk.ac.warwick.dcs.optimisation;

// could be a static class
public class CycleTimeOptimiser {

    // source: tfl traffic modelling guidelines
    private final int MIN_CYCLE_TIME = 32;

    private int lostTime;
    private int numTrafficLightGroups;
    private int[][] groupIncomingFlows;
    private int[][] groupMaxOutgoingFlow; //saturation


    public CycleTimeOptimiser(){}

    // https://www.sciencedirect.com/science/article/pii/B9780128153024000030
    public double getCycleTime(){
        return 0;
    }
}
