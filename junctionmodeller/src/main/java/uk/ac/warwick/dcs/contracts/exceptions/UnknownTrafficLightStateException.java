package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightState;

public class UnknownTrafficLightStateException extends RuntimeException {
    public UnknownTrafficLightStateException(TrafficLightState state) {
        super("Unknown traffic light state: " + state.toString());
    }
}
