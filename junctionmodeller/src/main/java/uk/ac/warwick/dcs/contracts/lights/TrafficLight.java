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
    //private TrafficLightState state;

    public TrafficLight(TrafficLightType tlt) {
        trafficLightType = tlt;
    }

    /**
     *
     * @return The type of the traffic light.
     */
    public TrafficLightType getTrafficLightType() {
        return trafficLightType;
    }
}
