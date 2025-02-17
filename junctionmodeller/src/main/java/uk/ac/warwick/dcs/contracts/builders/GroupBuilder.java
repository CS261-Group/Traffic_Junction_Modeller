package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupBuilder implements IGroupBuilder {
    private final static int UNASSIGNED_TIMING = -1;

    private int numGroups;
    private boolean optimising;
    private LinkedList<IncomingLane>[] groupLanes;
    private int[] groupTimings;

    public GroupBuilder() {
        optimising = true; // we will assume optimising by default
        groupLanes = new LinkedList[numGroups];
        groupTimings = new int[numGroups];

        // set default values
        for (int i = 0; i < numGroups; i++) {
            groupLanes[i] = new LinkedList<>();
            groupTimings[i] = UNASSIGNED_TIMING;
        }
    }

    public IGroupBuilder setNumGroups(int numGroups) {
        this.numGroups = numGroups;

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
    public IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum) throws InvalidGroupNumberException {
        if (0 >= groupNum || groupNum >= numGroups) {
            throw new InvalidGroupNumberException(groupNum, numGroups);
        }
        groupLanes[groupNum - 1].add(lane);
        return this;
    }

    @Override
    public IGroupBuilder setGroupTiming(int timing, int groupNum) throws InvalidGroupNumberException {
        if (0 >= groupNum || groupNum >= numGroups) {
            throw new InvalidGroupNumberException(groupNum, numGroups);
        }
        groupTimings[groupNum - 1] = timing;
        return this;
    }

    @Override
    public IGroupBuilder setOptimiseTimings(boolean optimising) {
        this.optimising = optimising;
        return this;
    }

    @Override
    public Groups buildGroups() {
        // TODO: error checks on unassigned values
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
