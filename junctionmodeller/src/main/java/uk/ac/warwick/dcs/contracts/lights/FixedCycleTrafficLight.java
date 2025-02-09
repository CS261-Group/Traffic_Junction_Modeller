package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

public class FixedCycleTrafficLight extends TrafficLight {
    // all traffic lights in a junction share a cycleLength
    public static float cycleLength;

    public FixedCycleTrafficLight() {
        super(TrafficLightType.FIXEDCYCLE);
    }
}
