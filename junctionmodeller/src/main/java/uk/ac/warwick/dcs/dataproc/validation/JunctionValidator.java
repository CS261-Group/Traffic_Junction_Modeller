package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.LinkedList;
import java.util.List;

public class JunctionValidator extends Validator<JunctionConfiguration> {
    private final IValidator<Carriageway> carriagewayValidator;
    private final IValidator<TrafficLight> trafficLightValidator;
    private final IValidator<Groups> groupsValidator;
    private final IValidator<JunctionConfiguration> laneAssignmentValidator;

    public JunctionValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
        carriagewayValidator = new CarriagewayValidator(diagnosticFactory);
        trafficLightValidator = new TrafficLightValidator(diagnosticFactory);
        groupsValidator = new GroupsValidator(diagnosticFactory);
        laneAssignmentValidator = new LaneAssignmentValidator(diagnosticFactory);
    }

    @Override
    public List<String> validate(JunctionConfiguration config) {
        List<String> errors = new LinkedList<>();

        // validate each carriageway
        for (Carriageway carriageway : config) {
            List<String> carriagewayErrors = carriagewayValidator.validate(carriageway);
            assert carriagewayErrors != null;
            errors.addAll(carriagewayErrors);
        }

        // validate the carriageway traffic lights
        errors.addAll(trafficLightValidator.validate(config.getTrafficLights()));

        // validate groups
        errors.addAll(groupsValidator.validate(config.getGroups()));

        // validate lane assignments
        errors.addAll(laneAssignmentValidator.validate(config));

        return errors;
    }
}
