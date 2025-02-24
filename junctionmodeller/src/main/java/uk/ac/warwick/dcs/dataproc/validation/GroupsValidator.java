package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.LinkedList;
import java.util.List;

class GroupsValidator extends Validator<Groups> {
    public GroupsValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    private List<String> validateNumGroups(Groups groups) {
        List<String> errors = new LinkedList<>();

        for (Group group : groups) {
            if (group.getGroupNum() < Groups.MIN_GROUP_NUM || group.getGroupNum() > Groups.MAX_GROUP_NUM) {
                errors.add(diagFactory.createInvalidGroupNumMessage(group.getGroupNum()));
            }
        }

        return errors;
    }

    @Override
    public List<String> validate(Groups groups) {
        return validateNumGroups(groups);
    }
}
