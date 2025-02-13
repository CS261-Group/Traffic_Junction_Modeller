package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

import java.util.LinkedList;
import java.util.List;

public class JunctionValidator implements IValidator {
    private final IDiagnosticFactory diagFactory;

    public JunctionValidator() {
        diagFactory = new DiagnosticFactory();
    }

    @Override
    public List<String> validate(JunctionConfiguration config) {
        List<String> errors = new LinkedList<>();
        return errors;
    }
}
