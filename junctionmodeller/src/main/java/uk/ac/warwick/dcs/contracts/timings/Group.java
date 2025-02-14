package uk.ac.warwick.dcs.contracts.timings;

import uk.ac.warwick.dcs.contracts.structure.IncomingLane;

import java.util.List;

public class Group {
    private final int groupNum;
    private final List<IncomingLane> lanes;

    public Group(int groupNum, List<IncomingLane> lanes) {
        this.groupNum = groupNum;
        this.lanes = lanes;
    }
}
