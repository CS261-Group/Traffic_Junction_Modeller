package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;

public class ActuatedTrafficLightObjectiveFunction implements IGradientFunction {
    private final int[] carFlowVph;  // Car flow in vehicles per hour for each direction

    // Constructor accepts car flow for each direction (north, south, east, west)
    public ActuatedTrafficLightObjectiveFunction(int[] carFlowVph) {
        this.carFlowVph = carFlowVph;
    }

    // Convert flow from vehicles per hour (vph) to vehicles per second (vps)
    private int convertFlowToVps(int flowVph) {
        return flowVph / 3600;  // Convert from vehicles per hour to vehicles per second (integer division)
    }

    // Evaluate the objective function (total wait time) at the current state (timings)
    @Override
    public int evaluateAt(SimpleMatrix state) {
        int totalWaitTime = 0;

        // Iterate over each direction's traffic light to compute the waiting time
        for (int i = 0; i < state.numElements(); i++) {
            int greenTime = (int) state.get(i);  // Green light duration for light i (in seconds)
            int flowVps = convertFlowToVps(carFlowVph[i]);  // Convert flow to vehicles per second
            int vehiclesArriving = flowVps * greenTime;  // Vehicles arriving during green light duration

            // Compute the waiting time per vehicle during the green light (simplified)
            int waitTimePerVehicle = greenTime / flowVps;

            // Accumulate the total waiting time for all vehicles at this light
            totalWaitTime += vehiclesArriving * waitTimePerVehicle;
        }

        return totalWaitTime;  // Return total waiting time for all vehicles
    }

    // Compute the gradient of the objective function (partial derivatives)
    @Override
    public SimpleMatrix evaluateGradientAt(SimpleMatrix state) {
        SimpleMatrix gradient = new SimpleMatrix(1, state.numElements());

        // Iterate over each direction
        for (int i = 0; i < state.numElements(); i++) {
            int greenTime = (int) state.get(i);  // Green light duration for this direction
            int flowVps = convertFlowToVps(carFlowVph[i]);  // Convert to vehicles per second
            int vehiclesArriving = flowVps * greenTime;

            // Simplified gradient (derivative) of the total waiting time with respect to green light duration
            int gradientValue = vehiclesArriving / flowVps;
            gradient.set(i, 0, gradientValue);  // Store the gradient for each light
        }

        return gradient;
    }
}
