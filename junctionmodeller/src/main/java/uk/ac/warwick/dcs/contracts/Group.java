package uk.ac.warwick.dcs.contracts;

import java.util.List;

public class Group {
    private final int groupNum;
    private final List<IncomingLane> lanes;

    public Group(int groupNum, List<IncomingLane> lanes) {
        this.groupNum = groupNum;
        this.lanes = lanes;
    }
}
