package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.ui.formdata.GroupTimings;

import java.util.List;

public class Groups {
    private final int numGroups;
    private final List<Group> groups;
    private final GroupTimings timings;

    public Groups(List<Group> groups, GroupTimings timings) {
        this.numGroups = groups.size();
        this.groups = groups;
        this.timings = timings;
    }
}
