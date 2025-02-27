package uk.ac.warwick.dcs.contracts.timings;

import uk.ac.warwick.dcs.contracts.structure.IncomingLane;

import java.util.List;

/**
 * Object used to store the list of incoming lanes for
 * each group, and the group number for each group object.
 */
public class Group {
    private final int groupNum;
    private final List<IncomingLane> lanes;

    public Group(int groupNum, List<IncomingLane> lanes) {
        this.groupNum = groupNum;
        this.lanes = lanes;
    }

    /**
     *
     * @return The group number of this group object.
     */
    public int getGroupNum() { return groupNum; }

    /**
     *
     * @param lane The <code>IncomingLane</code> object to check.
     * @return True if <code>lane</code> is in the list of incoming
     *         lanes stored within this group, false otherwise.
     */
    public boolean containsLane(IncomingLane lane) {
        return lanes.contains(lane);
    }
}
