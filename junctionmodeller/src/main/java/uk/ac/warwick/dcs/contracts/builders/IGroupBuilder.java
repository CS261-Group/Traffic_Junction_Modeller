package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public interface IGroupBuilder {
    IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum);
    IGroupBuilder setGroupTiming(int timing, int groupNum);
    IGroupBuilder setOptimiseTimings(boolean optimising);
    Groups buildGroups();
}
