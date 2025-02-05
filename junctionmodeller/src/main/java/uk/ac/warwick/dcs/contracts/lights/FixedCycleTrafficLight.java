package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

public class FixedCycleTrafficLight extends TrafficLight {

    public FixedCycleTrafficLight() {
        super(TrafficLightType.FIXEDCYCLE);
    }
}
