package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.LinkedList;
import java.util.List;

public class JunctionValidator implements IValidator {
    private final IDiagnosticFactory diagFactory;

    public JunctionValidator() {
        diagFactory = new DiagnosticFactory();
    }

    private List<String> validateCarriageway(Carriageway carriageway) {
        return null;
    }

    @Override
    public List<String> validate(JunctionConfiguration config) {
        List<String> errors = new LinkedList<>();

        for (Carriageway carriageway : config) {
            List<String> carriagewayErrors = validateCarriageway(carriageway);
            assert carriagewayErrors != null;
            errors.addAll(carriagewayErrors);
        }

        return errors;
    }
}
