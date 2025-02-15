package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.LinkedList;

public class GroupBuilder implements IGroupBuilder {
    private final static int UNASSIGNED_TIMING = -1;

    private final int numGroups;
    private boolean optimising;
    private final LinkedList<IncomingLane>[] groupLanes;
    private final int[] groupTimings;

    public GroupBuilder(int numGroups) {
        this.numGroups = numGroups;

        optimising = true; // we will assume optimising by default
        groupLanes = new LinkedList[numGroups];
        groupTimings = new int[numGroups];

        // set default values
        for (int i = 0; i < numGroups; i++) {
            groupLanes[i] = new LinkedList<>();
            groupTimings[i] = UNASSIGNED_TIMING;
        }
    }

    @Override
    public IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum) {
        // TODO: impl w/ exceptions for !(0<groupNum<lane.size)
        return this;
    }

    @Override
    public IGroupBuilder setGroupTiming(int timing, int groupNum) {
        // TODO: impl w/ exceptions for !(0<groupNum<lane.size)

        return this;
    }

    @Override
    public IGroupBuilder setOptimiseTimings(boolean optimising) {
        this.optimising = optimising;
        return this;
    }

    @Override
    public Groups buildGroups() {
        // TODO: impl w/ exception if a group is empty
        return null;
    }
}
