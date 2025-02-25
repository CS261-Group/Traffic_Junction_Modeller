package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Validator for <code>Groups</code> objects. Ensure that:
 * - All group numbers are between 1 and the number of groups available.
 * - Number of groups available is no more than the MAX number of groups
 */
class GroupsValidator extends Validator<Groups> {
    public GroupsValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    /**
     * Ensure all group numbers are between 1 and number of groups.
     * @param groups Groups object to validate.
     * @return List of errors.
     */
    private List<String> validateGroupNums(Groups groups) {
        List<String> errors = new LinkedList<>();

        for (Group group : groups) {
            if (group.getGroupNum() < Groups.MIN_GROUP_NUM || group.getGroupNum() > groups.getNumGroups()) {
                errors.add(diagFactory.createInvalidGroupNumMessage(group.getGroupNum()));
            }
        }

        return errors;
    }

    /**
     * Ensure number of groups is no more than the maximum number
     * of possible groups: <code>Groups.MAX_GROUP_NUM</code>.
     * @param groups Groups object to validate.
     * @return List of errors.
     */
    private List<String> validateNumGroups(Groups groups) {
        List<String> errors = new LinkedList<>();
        int numGroups = groups.getNumGroups();
        if (Groups.MIN_NUM_GROUPS > numGroups || numGroups > Groups.MAX_GROUP_NUM) {
            errors.add(diagFactory.createInvalidNumGroupsMessage(numGroups));
        }
        return errors;
    }

    /**
     * Ensure that group numbers are unique among all groups.
     * @param groups Groups object to be validated.
     * @return List of errors.
     */
    private List<String> validateDisjointGroups(Groups groups) {
        List<String> errors = new LinkedList<>();

        Set<Integer> seenGroups = new HashSet<>(Groups.MAX_GROUP_NUM);
        for (Group group : groups) {
            if (!seenGroups.add(group.getGroupNum())) {
                errors.add(diagFactory.createGroupNumbersNotSeparateMessage(group.getGroupNum()));
            }
        }

        return errors;
    }

    @Override
    public List<String> validate(Groups groups) {
        List<String> errors = validateNumGroups(groups);
        errors.addAll(validateGroupNums(groups));
        errors.addAll(validateDisjointGroups(groups));
        return errors;
    }
}
