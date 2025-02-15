package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightState;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.UnknownTrafficLightStateException;

public abstract class TrafficLight {
    private final TrafficLightType trafficLightType;
    private TrafficLightState state;

    public TrafficLight(TrafficLightType tlt) {
        trafficLightType = tlt;
        state = TrafficLightState.RED;
    }

    public TrafficLightType getTrafficLightType() {
        return trafficLightType;
    }

    public TrafficLightState getState() {
        return state;
    }

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
