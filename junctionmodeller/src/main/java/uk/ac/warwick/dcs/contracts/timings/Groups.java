package uk.ac.warwick.dcs.contracts.timings;

import java.util.List;

public class Groups {
    private final int numGroups;
    private final List<Group> groups;
    private final boolean optimiseTimings;
    private final List<GroupTiming> timings;

    public Groups(List<Group> groups, boolean optimiseTimings, List<GroupTiming> timings) {
        this.numGroups = groups.size();
        this.groups = groups;
        this.optimiseTimings = optimiseTimings;
        this.timings = timings;

        // sanity check: each group should have its own timing
        assert timings.size() == numGroups;
    }
}
