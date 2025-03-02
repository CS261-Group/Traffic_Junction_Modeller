package uk.ac.warwick.dcs.contracts.timings;

import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Object used to store lanes that are part of each traffic
 * group (i.e., the set of lanes that turn green together).
 * This is our solution for filters.
 */
public class Groups implements Iterable<Group> {
    // global constants for UI and validation
    /**
     * Largest possible group number.
     */
    public static final int MAX_GROUP_NUM = 6;

    /**
     * Smallest possible group number.
     */
    public static final int MIN_GROUP_NUM = 1;

    /**
     * Smallest number of groups possible.
     */
    public static final int MIN_NUM_GROUPS = 2;

    // time between amber in one group to green in another
    // TODO: source???
    private static final double TRANSITION_TIME = 2.5;

    // time to go through all groups
    private double CycleTime;

    private final int numGroups;
    private final List<Group> groups;
    private final boolean optimiseTimings;

    // separate from Group because it might be optimised
    private final List<GroupTiming> timings;

    public Groups(List<Group> groups, boolean optimiseTimings, List<GroupTiming> timings) {
        this.numGroups = groups.size();
        this.groups = groups;
        this.optimiseTimings = optimiseTimings;

        if (optimiseTimings) {
            this.timings = null; // not good, should create a default timings list instead
        } else {
            assert timings != null;
            // sanity check: each group should have its own timing
            assert timings.size() == numGroups;
            this.timings = timings;
            this.setCycleTime();
        }

    }


    private void setCycleTime(){
        double sumTimings = 0;

        for (GroupTiming timing : timings) {
            sumTimings += timing.getTiming();
        }

        CycleTime = sumTimings + (TRANSITION_TIME * numGroups);
    }

    /**
     * @return The number of traffic light groups in the configuration.
     */
    public int getNumGroups() { return numGroups; }

    /**
     * @return Whether the group timings are being optimised in this configuration.
     */
    public boolean getOptimising() {
        return optimiseTimings;
    }

    // Not sure if I should throw an exception the input or just return 0
    public double getLaneTiming(IncomingLane lane){
        Iterator<Group> groupIterator = this.iterator();

        while (groupIterator.hasNext()) {
            Group group = groupIterator.next();
            if (group.containsLane(lane)){

                return getGroupTiming(group.getGroupNum()).getTiming();
            }
        }

        return 0;
    }

    // Not sure if I should throw an exception the input or just return null
    public GroupTiming getGroupTiming(int groupNum){
        Iterator<GroupTiming> timingsIterator = timings.iterator();

        while (timingsIterator.hasNext()) {
            GroupTiming groupTiming = timingsIterator.next();

            if (groupTiming.getGroupNum() == groupNum) {
                return groupTiming;
            }
        }

        //throw new InvalidGroupNumberException(groupNum, numGroups);
        return null;
    }

    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}
