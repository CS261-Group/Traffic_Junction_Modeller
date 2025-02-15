package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public interface ILightBuilder {
    ILightBuilder setTrafficLightType(TrafficLightType type);
    TrafficLight buildTrafficLight();
}
