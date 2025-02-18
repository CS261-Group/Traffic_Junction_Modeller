package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.LinkedList;
import java.util.List;

public class GroupsValidator extends Validator<Groups> {
    public GroupsValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    @Override
    public List<String> validate(Groups groups) {
        List<String> errors = new LinkedList<>();

        // TODO: validate groups

        return errors;
    }
}
