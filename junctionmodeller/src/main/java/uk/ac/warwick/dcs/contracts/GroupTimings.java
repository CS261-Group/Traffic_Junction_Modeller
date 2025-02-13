package uk.ac.warwick.dcs.contracts;


import java.util.List;

public class GroupTimings {
    private final int numGroups;
    private final List<GroupTiming> timings;

    public GroupTimings(int numGroups, List<GroupTiming> timings) {
        this.numGroups = numGroups;
        this.timings = timings;
        assert numGroups == timings.size(); // sanity check
    }
}
