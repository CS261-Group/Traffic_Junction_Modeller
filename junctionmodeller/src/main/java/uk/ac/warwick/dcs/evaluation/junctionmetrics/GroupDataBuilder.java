package uk.ac.warwick.dcs.evaluation.junctionmetrics;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public class GroupDataBuilder {

    public GroupData buildGroupData(Carriageway carriageway){
        ///GroupData gd = new GroupData();
        return null;
    }

    public static int getLaneGreenTime(IncomingLane lane, Groups groups){
        return groups.getLaneTiming(lane);
    }
}
