package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.lights.ActuationTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public class LightBuilder implements ILightBuilder {
    // settings
    private TrafficLightType lightType;
    private boolean lightTypeAssigned = false; // used to check compulsory assignment

    @Override
    public ILightBuilder setTrafficLightType(TrafficLightType type) {
        lightType = type;
        lightTypeAssigned = true;
        return this;
    }

    @Override
    public TrafficLight buildTrafficLight() throws IncompleteBuildSettingsException {
        if (!lightTypeAssigned) {
             throw new IncompleteBuildSettingsException("Traffic light type", "LightBuilder.setTrafficLightType");
        }
        return lightType == TrafficLightType.FIXEDCYCLE ? new FixedCycleTrafficLight() : new ActuationTrafficLight();
    }
}
