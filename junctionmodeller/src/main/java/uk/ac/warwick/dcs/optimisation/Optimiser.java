package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

import java.util.Arrays;

public class Optimiser {
    private ActuatedTimingsOptimiser actuatedTimingsOptimiser;
    private FixedTimingsOptimiser fixedTimingsOptimiser;
    private CycleTimeOptimiser cycleTimeOptimiser;

    public Optimiser() {
        this.actuatedTimingsOptimiser = new ActuatedTimingsOptimiser();
        this.fixedTimingsOptimiser = new FixedTimingsOptimiser();
        this.cycleTimeOptimiser = new CycleTimeOptimiser();
    }

    public int[] getMaxGroupTimings(TrafficLightType trafficLightType) {
        if (trafficLightType == TrafficLightType.ACTUATION) {
            return actuatedTimingsOptimiser.getMaxGroupTimings();
        } else if (trafficLightType == TrafficLightType.FIXEDCYCLE) {
            return fixedTimingsOptimiser.getMaxGroupTimings();
        }
        return new int[0]; //if lights r invalid, shouldn't ever really get to this 
    }


    public int[] getMinGroupTimings(TrafficLightType trafficLightType) {
        if (trafficLightType == TrafficLightType.ACTUATION) {
            return actuatedTimingsOptimiser.getMinGroupTimings();
        }
        return new int[0]; 
    }

    public double getCycleTime() {
        return cycleTimeOptimiser.getCycleTime();
    }

    public void optimiseTrafficLights() {
        TrafficLightType trafficLightType = TrafficLightType.ACTUATION;
        int[] maxTimings = getMaxGroupTimings(trafficLightType);
        int[] minTimings = getMinGroupTimings(trafficLightType);
        double cycleTime = getCycleTime();

   // this is just for debugging, dont acc need this in the end  
        System.out.println("Optimising traffic lights of type: " + trafficLightType);
        System.out.println("Max Timings: " + Arrays.toString(maxTimings));
        System.out.println("Min Timings: " + Arrays.toString(minTimings));
        System.out.println("Cycle Time: " + cycleTime);
    }
}
