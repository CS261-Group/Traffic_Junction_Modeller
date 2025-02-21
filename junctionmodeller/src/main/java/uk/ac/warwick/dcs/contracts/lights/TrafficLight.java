package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightState;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.UnknownTrafficLightStateException;

/**
 * Abstract class that contains the wrapping functionality
 * for both fixed-cycle length and actuation traffic lights.
 */
public abstract class TrafficLight {
    private final TrafficLightType trafficLightType;
    private TrafficLightState state;

    public TrafficLight(TrafficLightType tlt) {
        trafficLightType = tlt;
        state = TrafficLightState.RED;
    }

    /**
     *
     * @return The type of the traffic light.
     */
    public TrafficLightType getTrafficLightType() {
        return trafficLightType;
    }

    /**
     *
     * @return The current state of the traffic light.
     */
    public TrafficLightState getState() {
        return state;
    }

    /**
     * Move traffic light into next available state in the cycle.
     * NOTE: might need to skip <code>AMBER</code> and <code>REDAMBER</code>
     * states.
     */
    public void nextState() {
        switch (state) {
            case RED:
                state = TrafficLightState.REDAMBER;
                return;
            case REDAMBER:
                state = TrafficLightState.GREEN;
                return;
            case GREEN:
                state = TrafficLightState.AMBER;
                return;
            case AMBER:
                state = TrafficLightState.RED;
                return;
            default:
                throw new UnknownTrafficLightStateException(state);
        }
    }
}
