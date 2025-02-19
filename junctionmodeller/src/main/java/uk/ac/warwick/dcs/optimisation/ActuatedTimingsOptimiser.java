package uk.ac.warwick.dcs.optimisation;

public class ActuatedTimingsOptimiser implements ITrafficLightTimingsOptimiser {

    // Assuming car flow data (vehicles per hour) for each direction
    private static final int[] CAR_FLOW_VPH = {};  //  values for each direction

    @Override
    public int[] getMaxGroupTimings() {
 
        return new int[] {};  //max duration for each direction
    }

    public int[] getMinGroupTimings() {
        // Assume min timings for actuated traffic light groups
        return new int[] {};  //min duration for each direction
    }

    public int[] getOptimisedTimings() {
        // Set initial timings (guess values for each traffic light's green time)
        float[] initialTimings = {};  // Example initial green times in seconds

        // Create the gradient function with the known car flow for each direction
        ActuatedTrafficLightObjectiveFunction gradientFunction = new ActuatedTrafficLightObjectiveFunction(CAR_FLOW_VPH);

        // Use descent to optimise the timings
        GradientDescent<ActuatedTrafficLightObjectiveFunction> optimiser = 
            new GradientDescent<>(initialTimings, gradientFunction);

        // Run the optimisation process
        optimiser.stepThrough();

        // Get the optimised timings (in seconds)
        float[] optimalTimings = optimiser.getStateValues();

        // Convert the timings to integers and return
        int[] optimalIntTimings = new int[optimalTimings.length];
        for (int i = 0; i < optimalTimings.length; i++) {
            optimalIntTimings[i] = Math.round(optimalTimings[i]);
        }

        return optimalIntTimings;
    }
}
