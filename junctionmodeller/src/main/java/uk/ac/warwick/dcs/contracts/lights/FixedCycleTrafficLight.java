package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

/**
 * Fixed-cycle traffic light.
 */
public class FixedCycleTrafficLight extends TrafficLight {
    public FixedCycleTrafficLight() {
        super(TrafficLightType.FIXEDCYCLE);
    }
}
