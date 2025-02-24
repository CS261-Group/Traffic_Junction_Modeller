package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

import java.util.List;

/**
 * Validator for traffic lights.
 */
class TrafficLightValidator extends Validator<TrafficLight> {
    public TrafficLightValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    @Override
    public List<String> validate(TrafficLight trafficLight) {
        // nothing to validate, so we leave it with no errors
        return List.of();
    }
}
