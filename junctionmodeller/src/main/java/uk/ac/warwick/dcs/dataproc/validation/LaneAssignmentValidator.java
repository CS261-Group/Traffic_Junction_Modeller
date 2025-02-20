package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LaneAssignmentValidator extends Validator<JunctionConfiguration> {
    private static final int LANE_SET_SIZE = 51; // 6x4 lanes at most, roughly double and prime

    public LaneAssignmentValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    private List<String> validateLaneAssignments(JunctionConfiguration junctionConfiguration) {
        List<String> errors = new LinkedList<>();

        // unpack groups
        Set<IncomingLane> laneSet = new HashSet<>(LANE_SET_SIZE);
        for (Carriageway carriageway : junctionConfiguration) {
            int numLanes = carriageway.getNumIncomingLanes();
            for (int laneNum = 1; laneNum <= numLanes; laneNum++) {
                boolean success = laneSet.add(carriageway.getIncomingLane(laneNum));

                // sanity check: any lane should only ever be inserted once
                // otherwise multiple instances of the same lane pointer are in
                // the carriageway
                assert success;
            }
        }

        Groups groups = junctionConfiguration.getGroups();
        int count = laneSet.size();
        for (IncomingLane lane : laneSet) {
            for (Group group : groups) {
                // TODO: go through all lanes
                if (group.containsLane(lane)) {
                    count--;
                }
            }
        }

        // sanity check: single lane can't be assigned to multiple groups
        assert count >= 0;

        // not all lanes in lane set are assigned to a group
        if (count != 0) {
            errors.add(diagFactory.createInvalidLaneAssignmentMessage());
        }

        return errors;
    }

    @Override
    public List<String> validate(JunctionConfiguration config) {
        return validateLaneAssignments(config);
    }
}
