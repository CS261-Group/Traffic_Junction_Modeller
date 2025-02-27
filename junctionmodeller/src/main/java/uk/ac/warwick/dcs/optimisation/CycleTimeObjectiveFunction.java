package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;

public class CycleTimeObjectiveFunction implements IGradientFunction {
    
    private final int[][] groupIncomingFlows;  // Incoming vehicle flow for each group (vph)
    private final int[][] groupMaxOutgoingFlow; // Saturation (maximum possible flow) for each group (vph)

    // Constructor
    public CycleTimeObjectiveFunction(int[][] groupIncomingFlows, int[][] groupMaxOutgoingFlow) {
        this.groupIncomingFlows = groupIncomingFlows;
        this.groupMaxOutgoingFlow = groupMaxOutgoingFlow;
    }


    private int convertFlowToVps(int flowVph) {
        return flowVph / 3600;  // Convert from vehicles per hour to vehicles per second 
    }

    // Evaluate the objective function (total waiting time) for a given set of cycle times
    @Override
    public SimpleMatrix evaluateAt(SimpleMatrix state) {
//        int totalWaitTime = 0;
//
//        // Iterate over each traffic light group to compute waiting time
//        for (int i = 0; i < state.getNumElements(); i++) {
//            int cycleTime = (int) state.get(i);  // Cycle time allocated to group i (in seconds)
//            int[] incomingFlow = groupIncomingFlows[i];  // Incoming flow for this group
//            int[] maxOutgoingFlow = groupMaxOutgoingFlow[i];  // Max outgoing flow (saturation) for this group
//
//            // Calculate the total incoming flow (converted to vehicles per second)
//            int totalIncomingFlow = 0;
//            for (int j = 0; j < incomingFlow.length; j++) {
//                totalIncomingFlow += convertFlowToVps(incomingFlow[j]);
//            }
//
//            // Calculate the total outgoing flow (converted to vehicles per second)
//            int totalOutgoingFlow = 0;
//            for (int j = 0; j < maxOutgoingFlow.length; j++) {
//                totalOutgoingFlow += convertFlowToVps(maxOutgoingFlow[j]);
//            }
//
//            // Compute waiting time for vehicles in this group (simplified)
//            int waitTime = (totalIncomingFlow - totalOutgoingFlow) * cycleTime;  // Ensure no overflow
//            totalWaitTime += waitTime;
//        }
//
//        return totalWaitTime;  // Return the total waiting time for all groups
        return SimpleMatrix.identity(2); // TODO: removed
    }

    // Compute the gradient (partial derivatives) with respect to each cycle time
    public SimpleMatrix evaluateGradientAt(SimpleMatrix state) {
        SimpleMatrix gradient = new SimpleMatrix(1, state.getNumElements());

        // Iterate over each group to compute the gradient
        for (int i = 0; i < state.getNumElements(); i++) {
            int cycleTime = (int) state.get(i);  // Cycle time for this group
            int[] incomingFlow = groupIncomingFlows[i];
            int[] maxOutgoingFlow = groupMaxOutgoingFlow[i];

            // Calculate incoming and outgoing flow in vehicles per second
            int totalIncomingFlow = 0;
            for (int j = 0; j < incomingFlow.length; j++) {
                totalIncomingFlow += convertFlowToVps(incomingFlow[j]);
            }

            int totalOutgoingFlow = 0;
            for (int j = 0; j < maxOutgoingFlow.length; j++) {
                totalOutgoingFlow += convertFlowToVps(maxOutgoingFlow[j]);
            }

            // Gradient calculation based on the difference in incoming and outgoing flow
            int gradientValue = (totalIncomingFlow - totalOutgoingFlow);  // Simple gradient (as an integer)
            gradient.set(i, 0, gradientValue);
        }

        return gradient;
    }
}
