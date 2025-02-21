package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

/**
 * Actuation traffic light.
 */
public class ActuationTrafficLight extends TrafficLight {
    public ActuationTrafficLight() {
        super(TrafficLightType.ACTUATION);
    }
}
