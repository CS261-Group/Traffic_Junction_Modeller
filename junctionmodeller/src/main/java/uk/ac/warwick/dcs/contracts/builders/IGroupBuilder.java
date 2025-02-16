package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public interface IGroupBuilder {
    IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum) throws InvalidGroupNumberException;
    IGroupBuilder setGroupTiming(int timing, int groupNum) throws InvalidGroupNumberException;
    IGroupBuilder setOptimiseTimings(boolean optimising);
    Groups buildGroups();
}
