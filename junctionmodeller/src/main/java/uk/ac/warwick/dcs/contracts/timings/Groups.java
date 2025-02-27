package uk.ac.warwick.dcs.contracts.timings;

import java.util.Iterator;
import java.util.List;

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
     * TODO: test for this when building groups
     */
    public static final int MIN_NUM_GROUPS = 2;

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
            this.timings = null;
        } else {
            assert timings != null;
            // sanity check: each group should have its own timing
            assert timings.size() == numGroups;
            this.timings = timings;
        }
    }

    /**
     *
     * @return The number of traffic light groups in the configuration.
     */
    public int getNumGroups() { return numGroups; }

    /**
     *
     * @return Whether the group timings are being optimised in this configuration.
     */
    public boolean getOptimising() {
        return optimiseTimings;
    }

    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}
