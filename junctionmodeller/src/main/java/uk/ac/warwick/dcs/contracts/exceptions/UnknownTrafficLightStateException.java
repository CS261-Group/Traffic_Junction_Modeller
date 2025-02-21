package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightState;

/**
 * Exception thrown if a <code>TrafficLight</code> is set to an invalid
 * <code>TrafficLightState</code>.
 */
public class UnknownTrafficLightStateException extends RuntimeException {
    public UnknownTrafficLightStateException(TrafficLightState state) {
        super("Unknown traffic light state: " + state.toString());
    }
}
