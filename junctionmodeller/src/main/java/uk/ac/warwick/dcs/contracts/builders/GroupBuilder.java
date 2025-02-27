package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupTimingException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupBuilder implements IGroupBuilder {
    /**
     * Value used to identify unassigned timing values.
     */
    private final static int UNASSIGNED_TIMING = -1;

    // settings
    private int numGroups;
    private boolean optimising;
    private LinkedList<IncomingLane>[] groupLanes;
    private int[] groupTimings;

    // these are used to ensure that a full configuration is configured
    // since I will not allow partial settings (we are not using defaults)
    private boolean numGroupsAssigned, optimisingAssigned;

    public GroupBuilder() {
        numGroupsAssigned = optimisingAssigned = false;
    }

    public IGroupBuilder setNumGroups(int numGroups) {
        this.numGroups = numGroups;
        numGroupsAssigned = true;

        // initialise fixed-size arrays to hold data
        groupLanes = new LinkedList[numGroups];
        groupTimings = new int[numGroups];

        // set default values
        for (int i = 0; i < numGroups; i++) {
            groupLanes[i] = new LinkedList<>();
            groupTimings[i] = UNASSIGNED_TIMING;
        }

        return this;
    }

    @Override
    public IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum) throws InvalidGroupNumberException, IncompleteBuildSettingsException {
        if (!numGroupsAssigned) {
            throw new IncompleteBuildSettingsException("number of groups", "setNumGroups");
        }
        if (0 >= groupNum || groupNum > numGroups) {
            throw new InvalidGroupNumberException(groupNum, numGroups);
        }
        groupLanes[groupNum - 1].add(lane);
        return this;
    }

    @Override
    public IGroupBuilder setGroupTiming(int timing, int groupNum) throws InvalidGroupNumberException, InvalidGroupTimingException, IncompleteBuildSettingsException {
        if (!numGroupsAssigned) {
            throw new IncompleteBuildSettingsException("number of groups", "setNumGroups");
        }

        if (0 >= groupNum || groupNum > numGroups) {
            throw new InvalidGroupNumberException(groupNum, numGroups);
        }
        if (timing < GroupTiming.MIN_GROUP_TIMING || timing > GroupTiming.MAX_GROUP_TIMING) {
            throw new InvalidGroupTimingException(groupNum, timing);
        }
        groupTimings[groupNum - 1] = timing;
        return this;
    }

    @Override
    public IGroupBuilder setOptimiseTimings(boolean optimising) {
        this.optimising = optimising;
        optimisingAssigned = true;
        return this;
    }

    @Override
    public Groups buildGroups() throws IncompleteBuildSettingsException {
        // error checks on unassigned values
        if (!numGroupsAssigned) {
            throw new IncompleteBuildSettingsException("Number of Groups", "GroupBuilder.setNumGroups");
        }
        if (!optimisingAssigned) {
            throw new IncompleteBuildSettingsException("optimising", "GroupBuilder.setOptimiseTimings");
        }
        assert numGroups == groupTimings.length && numGroups == groupLanes.length;
        for (int i = 0; i < numGroups; i++) {
            int groupNum = i + 1;
            // unassigned group timing for a certain group
            if (groupTimings[i] == UNASSIGNED_TIMING) {
                throw new IncompleteBuildSettingsException("Group timings for group " + groupNum, "GroupBuilder.setGroupTiming");
            }

            // no lanes assigned to a group
            if (groupLanes[i].isEmpty()) {
                throw new IncompleteBuildSettingsException("Empty group (no lanes) for group " + groupNum, "GroupBuilder.addLaneToGroup");
            }
        }

        // convert collected data to required DTOs and return the assembled Groups object
        List<Group> groupsList = new ArrayList<>(numGroups);
        List<GroupTiming> timings = new ArrayList<>(numGroups);
        for (int i = 0; i < groupLanes.length; i++) {
            int groupNum = i + 1;
            Group group = new Group(groupNum, groupLanes[i]);
            groupsList.add(group);

            GroupTiming timing = new GroupTiming(groupNum, groupTimings[i]);
            timings.add(timing);
        }

        return new Groups(groupsList, optimising, timings);
    }
}
