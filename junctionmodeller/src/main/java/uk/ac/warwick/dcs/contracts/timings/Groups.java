package uk.ac.warwick.dcs.contracts.timings;

import java.util.Iterator;
import java.util.List;

public class Groups implements Iterable<Group> {
    // global constants for UI and validation
    public static final int MAX_GROUP_NUM = 6;
    public static final int MIN_GROUP_NUM = 1;

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

    public int getNumGroups() { return numGroups; }

    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}
