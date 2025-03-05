package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.NoValueExistsException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;

import java.util.ArrayList;

public class GroupDataBuilder {
    public static final double EXTENSION_HEADWAY_DEFAULT = 2.5;
    public JunctionConfiguration junctionConfiguration;

    public GroupDataBuilder(JunctionConfiguration junctionConfiguration){
        this.junctionConfiguration = junctionConfiguration;
    }

    public ActuatedGroupData buildActuatedGroupData(Group group){
        int groupNum = group.getGroupNum();
        ArrayList<LaneData> groupLanes = new ArrayList<>();

        for (IncomingLane lane : group.getLanes()){
            //create lane Data
            groupLanes.add(LaneDataBuilder.createLaneData(lane,
                    junctionConfiguration.getCarriageway(lane.getDirection())));
        }

        // as below, maxGreenTime will be overwritten
        return new ActuatedGroupData(groupNum, 0, EXTENSION_HEADWAY_DEFAULT, 0, groupLanes);
    }


    public GroupData buildGroupData(Group group){
        int groupNum = group.getGroupNum();
        ArrayList<LaneData> groupLanes = new ArrayList<>();

        for (IncomingLane lane : group.getLanes()){
            //create lane Data
            groupLanes.add(LaneDataBuilder.createLaneData(lane,
                    junctionConfiguration.getCarriageway(lane.getDirection())));
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
