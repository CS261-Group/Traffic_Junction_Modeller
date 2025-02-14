package uk.ac.warwick.dcs.contracts.timings;


import java.util.List;

public class GroupTimings {
    private final boolean optimise;
    private final int numGroups;
    private final List<GroupTiming> timings;

    public GroupTimings(boolean optimise, int numGroups, List<GroupTiming> timings) {
        this.optimise = optimise;
        this.numGroups = numGroups;

        // sanity check: can't have timings be null if we are not optimising
        assert optimise || timings != null;

        this.timings = timings;
        // sanity check: we have a timing for each group
        assert numGroups == timings.size();
    }
}
