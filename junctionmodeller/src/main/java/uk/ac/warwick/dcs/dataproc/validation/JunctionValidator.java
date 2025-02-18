package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.LinkedList;
import java.util.List;

public class JunctionValidator implements IValidator<JunctionConfiguration> {
    private final IDiagnosticFactory diagFactory;
    private final CarriagewayValidator carriagewayValidator;

    public JunctionValidator() {
        diagFactory = new DiagnosticFactory();
        carriagewayValidator = new CarriagewayValidator(diagFactory);
    }

    @Override
    public List<String> validate(JunctionConfiguration config) {
        List<String> errors = new LinkedList<>();

        for (Carriageway carriageway : config) {
            List<String> carriagewayErrors = carriagewayValidator.validate(carriageway);
            assert carriagewayErrors != null;
            errors.addAll(carriagewayErrors);
        }

        return errors;
    }
}
