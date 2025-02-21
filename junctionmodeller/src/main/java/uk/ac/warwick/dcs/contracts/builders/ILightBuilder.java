package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public interface ILightBuilder {
    /**
     * Compulsory setting.
     * @param type The type of the traffic light to set.
     * @return Same instance of builder object. Useful for chaining.
     */
    ILightBuilder setTrafficLightType(TrafficLightType type);

    /**
     *
     * @return Constructed <code>TrafficLight</code> object from configured settings.
     * @throws IncompleteBuildSettingsException If the compulsory settings are not configured
     *                                          before the <code>buildTrafficLight</code> call.
     */
    TrafficLight buildTrafficLight() throws IncompleteBuildSettingsException;
}
