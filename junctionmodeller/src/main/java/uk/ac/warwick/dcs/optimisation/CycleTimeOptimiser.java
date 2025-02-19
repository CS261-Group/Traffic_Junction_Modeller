package uk.ac.warwick.dcs.optimisation;

public class CycleTimeOptimiser {
//    private final int MIN_CYCLE_TIME = 32;
//    private final int LOST_TIME = 2;
//
//    private int numTrafficLightGroups;
//    private int[][] groupIncomingFlows;  // Incoming traffic flow for each group (vph)
//    private int[][] groupMaxOutgoingFlow;  // Maximum possible flow (saturation) for each group (vph)
//
//    // Constructor
//    public CycleTimeOptimiser(int numTrafficLightGroups, int[][] groupIncomingFlows, int[][] groupMaxOutgoingFlow) {
//        this.numTrafficLightGroups = numTrafficLightGroups;
//        this.groupIncomingFlows = groupIncomingFlows;
//        this.groupMaxOutgoingFlow = groupMaxOutgoingFlow;
//    }
//
//    // Get optimized cycle times
//    public int[] getOptimizedCycleTimes() {
//        // Initial cycle times (can be set to equal distribution across groups initially)
//        int[] initialTimings = new int[numTrafficLightGroups];
//        for (int i = 0; i < numTrafficLightGroups; i++) {
//            initialTimings[i] = MIN_CYCLE_TIME + LOST_TIME;  // Minimum cycle time + some lost time
//        }
//
//        // Create the gradient function for optimizing cycle times
//        CycleTimeObjectiveFunction gradientFunction = new CycleTimeObjectiveFunction(groupIncomingFlows, groupMaxOutgoingFlow);
//
//        // Use Gradient Descent to optimize cycle times
//        GradientDescent<CycleTimeObjectiveFunction> optimizer = new GradientDescent<>(initialTimings, gradientFunction);
//
//        // Run the optimization process
//        optimizer.stepThrough();
//
//        // Get the optimized cycle times
//        int[] optimalTimings = optimizer.getStateValues();
//
//        // Convert the timings to integers and return them
//        return optimalTimings;
//    }
}
