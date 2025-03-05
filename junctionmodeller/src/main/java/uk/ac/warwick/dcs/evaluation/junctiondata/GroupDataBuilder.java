package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.NoValueExistsException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;

import java.util.ArrayList;

public class GroupDataBuilder {

    public static GroupData buildGroupData(JunctionConfiguration junctionConfiguration, Group group){
        int groupNum = group.getGroupNum();
        ArrayList<LaneData> groupLanes = new ArrayList<>();

        for (IncomingLane lane : group.getLanes()){
            //create lane Data
            LaneDataBuilder.createLaneData(lane, junctionConfiguration.getCarriageway(lane.getDirection()));
            groupLanes.add(
                    LaneDataBuilder.createLaneData(
                            lane, junctionConfiguration.getCarriageway(
                                    lane.getDirection()
                            )
                    )
            );
        }


        try {
            return new GroupData(groupNum, junctionConfiguration.getGroupTimingValue(groupNum), groupLanes);
        } catch (NoValueExistsException e) {
            // no value exists for group timings, so initialise with 0
            // (will be overwritten immediately)
            return new GroupData(groupNum, 0, groupLanes);
        }
    }
}
