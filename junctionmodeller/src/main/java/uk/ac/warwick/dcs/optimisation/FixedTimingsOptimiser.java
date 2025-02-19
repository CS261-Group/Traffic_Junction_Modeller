package uk.ac.warwick.dcs.optimisation;


public class FixedTimingsOptimiser implements ITrafficLightTimingsOptimiser {


    private static final int[] CAR_FLOW_VPH = {};  // flow in each direction

    @Override
    public int[] getMaxGroupTimings() {
     
        float[] initialTimings = {};  // needs initial timing here
        
        // Create the gradient function with the known car flow for each direction
        TrafficLightObjectiveFunction gradientFunction = new TrafficLightObjectiveFunction(CAR_FLOW_VPH);
        
        // Use descent to optimise the timings
        GradientDescent<TrafficLightObjectiveFunction> optimiser = 
            new GradientDescent<>(initialTimings, gradientFunction);
        
        // run the optimisation process
        optimiser.stepThrough();
        
        // Get the optimised timings (in seconds)
        float[] optimalTimings = optimiser.getStateValues();
        
        // convert the timings to integers and return
        int[] optimalIntTimings = new int[optimalTimings.length];
        for (int i = 0; i < optimalTimings.length; i++) {
            optimalIntTimings[i] = Math.round(optimalTimings[i]);
        }
        
        return optimalIntTimings;
    }
}
