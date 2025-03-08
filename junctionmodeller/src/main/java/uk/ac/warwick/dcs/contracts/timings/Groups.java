package uk.ac.warwick.dcs.contracts.timings;

import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.exceptions.NoValueExistsException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.ui.formdata.GroupTimings;

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

    /**
     * The delay between green in one group to green in another, assumed to be the same for all junctions.
     * See:<a href="https://www.sciencedirect.com/science/article/pii/B9780128153024000030">Chow and Ampountolas</a>
     */
    private static final double TRANSITION_TIME = 4;

    // 0 when being optimised
    private int cycleTime;

    private final int numGroups;
    private final List<Group> groups;
    private final boolean optimiseTimings;

    // null when being optimised
    private final List<GroupTiming> timings;

    public Groups(List<Group> groups, boolean optimiseTimings, List<GroupTiming> timings) {
        this.numGroups = groups.size();
        this.groups = groups;
        this.optimiseTimings = optimiseTimings;

        if (optimiseTimings) {
            this.timings = null;
            this.cycleTime = 0;
        } else {
            assert timings != null;
            // sanity check: each group should have its own timing
            assert timings.size() == numGroups;
            this.timings = timings;
            this.setCycleTime();
        }

    }

    public int getMaxGroupTiming(){
        return GroupTiming.MAX_GROUP_TIMING;
    }

    private void setCycleTime(){
        int sumTimings = 0;

        for (GroupTiming timing : timings) {
            sumTimings += timing.getTiming();
        }

        cycleTime = (int) (sumTimings + this.cycleLostTime());
    }

    public double getCycleTime(){
        return cycleTime;
    }

    public double cycleLostTime(){
        return TRANSITION_TIME * numGroups;
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

    /**
     * @param lane Input lane
     * @return The timing of a lane.
     * @throws NoValueExistsException No such timing exists
     */
    public int getLaneTiming(IncomingLane lane) throws NoValueExistsException{
        for (Group group : this) {
            if (group.containsLane(lane)) {
                return getGroupTiming(group.getGroupNum()).getTiming();
            }
        }

        throw new NoValueExistsException("GroupTimings");
    }

    /**
     * @param groupNum Group number
     * @return The GroupTiming object corresponding to the group number.
     * @throws NoValueExistsException No such timing exists
     */
    public GroupTiming getGroupTiming(int groupNum) throws NoValueExistsException{
        for(GroupTiming groupTiming : this.timings){
            if (groupTiming.getGroupNum() == groupNum) {
                return groupTiming;
            }
        }
        throw new NoValueExistsException("GroupTimings");
    }

    /**
     * @param groupNum Group number
     * @return The GroupTiming timing value corresponding to the group number.
     * Or zero if none exists
     * @throws NoValueExistsException No such timing exists
     */
    public int getGroupTimingValue(int groupNum) throws NoValueExistsException, InvalidGroupNumberException {
        try {
            for (GroupTiming groupTiming : this.timings) {
                if (groupTiming.getGroupNum() == groupNum) {
                    return groupTiming.getTiming();
                }
            }
        } catch(NullPointerException e) {
            throw new NoValueExistsException("GroupTimings");
        }
        throw new InvalidGroupNumberException(groupNum, numGroups);
    }


    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}
