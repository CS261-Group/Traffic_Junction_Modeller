package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.lights.ActuationTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public class LightBuilder implements ILightBuilder {
    private TrafficLightType lightType;
    private boolean lightTypeAssigned = false;

    @Override
    public ILightBuilder setTrafficLightType(TrafficLightType type) {
        lightType = type;
        lightTypeAssigned = true;
        return this;
    }

    @Override
    public TrafficLight buildTrafficLight() {
        // TODO: throw exception if not assigned
        return lightType == TrafficLightType.FIXEDCYCLE ? new FixedCycleTrafficLight() : new ActuationTrafficLight();
    }
}
