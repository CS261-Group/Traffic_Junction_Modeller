package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.Arrays;

public class Optimiser {
    private final JunctionConfiguration junctionConfig;
    private final JunctionData junctionData;
    private ActuatedTimingsOptimiser actuatedTimingsOptimiser;
    private FixedTimingsOptimiser fixedTimingsOptimiser;

    public Optimiser(JunctionConfiguration junctionConfig, JunctionData junctionData) {
        this.junctionConfig = junctionConfig;
        this.junctionData = junctionData;

        this.actuatedTimingsOptimiser = new ActuatedTimingsOptimiser();
        this.fixedTimingsOptimiser = new FixedTimingsOptimiser();

        // junctions being optimised need initial data
        new CycleAndGreenTimeInitialiser(junctionData, junctionConfig.getCycleLostTime());
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
//      return cycleTimeOptimiser.getCycleTime();
        return 0.0D;
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
