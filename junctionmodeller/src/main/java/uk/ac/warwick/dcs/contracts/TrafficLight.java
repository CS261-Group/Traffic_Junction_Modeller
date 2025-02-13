package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

public class TrafficLight {
    private final TrafficLightType trafficLightType;

    public TrafficLight(TrafficLightType lightType) {
        trafficLightType = lightType;
    }
}
