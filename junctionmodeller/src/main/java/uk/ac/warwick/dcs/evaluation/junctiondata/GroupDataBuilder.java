package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public class GroupDataBuilder {

    public GroupData buildGroupData(Carriageway carriageway){
        ///GroupData gd = new GroupData();

        GroupData()
        return null;
    }

    public static int getLaneGreenTime(IncomingLane lane, Groups groups){
        return groups.getLaneTiming(lane);
    }
}
