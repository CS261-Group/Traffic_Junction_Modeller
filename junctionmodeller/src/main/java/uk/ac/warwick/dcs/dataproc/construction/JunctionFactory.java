package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.builders.*;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.*;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.ui.formdata.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JunctionFactory implements IJunctionFactory<ConfigurationData> {
    private final ILightBuilder lightBuilder;
    private final IGroupBuilder groupBuilder;
    private final ICarriagewayBuilder[] carriagewayBuilders;
    private Groups groups;

    public JunctionFactory() {
        lightBuilder = new LightBuilder();
        groupBuilder = new GroupBuilder();
        carriagewayBuilders = new CarriagewayBuilder[4];

        carriagewayBuilders[Direction.NORTH.ordinal()] = new CarriagewayBuilder(Direction.NORTH);
        carriagewayBuilders[Direction.EAST.ordinal()] = new CarriagewayBuilder(Direction.EAST);
        carriagewayBuilders[Direction.SOUTH.ordinal()] = new CarriagewayBuilder(Direction.SOUTH);
        carriagewayBuilders[Direction.WEST.ordinal()] = new CarriagewayBuilder(Direction.WEST);
    }

    private Groups readGroups(Carriageway[] carriageways, int numGroups, GroupTimings groupTimings, LaneGroups[] laneGroups) {
        boolean optimising = groupTimings.optimise();

        // create array of lists and fill with empty lists
        List<IncomingLane>[] groupArr = new List[numGroups];
        for (int i = 0; i < numGroups; i++) {
            groupArr[i] = new LinkedList<>();
        }
        for (LaneGroups lGroups : laneGroups) {
            // we can use the direction to get the group of lane objects
            // from that direction and assign them as required
            Direction direction = lGroups.direction();
            for (LaneGroup laneGroup : lGroups.laneGroups()) {
                IncomingLane incomingLane = null; // TODO: get from carriageway
                groupArr[laneGroup.groupNum()].add(incomingLane);
            }
        }

        List<Group> groupList = new ArrayList<>(numGroups);
        for (int i = 0; i < numGroups; i++) {
            int groupNum = i + 1;
            groupList.add(new Group(groupNum, groupArr[i]));
        }

        List<GroupTiming> timings = null;
        // only specify group timings when not optimising
        if (!optimising) {
            timings = new ArrayList<>(numGroups);
            List<uk.ac.warwick.dcs.ui.formdata.GroupTiming> uiGroupTimings = groupTimings.groupTimings();
            for (uk.ac.warwick.dcs.ui.formdata.GroupTiming timing : uiGroupTimings) {
                timings.add(new GroupTiming(timing.groupNum(), timing.time()));
            }
        }

        return new Groups(groupList, true, timings);
    }

    private TrafficLight readTrafficLight(TrafficLightType type) {
        return lightBuilder
                .setTrafficLightType(type)
                .buildTrafficLight();
    }

    // TODO: what are the required parameters
    private Carriageway readCarriageway(DirectionData directionData) throws InvalidDirectionException {
        // unpack direction data
        Direction direction = directionData.direction();
        FlowData flowData = directionData.flowData();
        List<AvailableDirections> availableDirections = directionData.availableDirections();

        // unpack corresponding builder
        ICarriagewayBuilder builder = carriagewayBuilders[direction.ordinal()];

        // construct outgoing road
        // TODO: how do we determine the number of outgoing roads
        builder.addOutgoingLane();

        // construct incoming road
        // extract incoming flow and reset the corresponding value to 0
        int[] flows = flowData.flows(); // removed from record for ease of use
        int incomingFlow = flows[direction.ordinal()];
        flows[direction.ordinal()] = 0;
        builder.setIncomingFlow(incomingFlow);

        // add the outgoing flows in directions
        // which are not the incoming direction
        if (direction != Direction.NORTH) {
            builder.setOutgoingFlow(flows[Direction.NORTH.ordinal()], Direction.NORTH);
        }
        if (direction != Direction.EAST) {
            builder.setOutgoingFlow(flows[Direction.EAST.ordinal()], Direction.EAST);
        }
        if (direction != Direction.SOUTH) {
            builder.setOutgoingFlow(flows[Direction.SOUTH.ordinal()], Direction.SOUTH);
        }
        if (direction != Direction.WEST) {
            builder.setOutgoingFlow(flows[Direction.WEST.ordinal()], Direction.WEST);
        }

        // assemble carriageway
        return builder.buildCarriageway();
    }

    @Override
    public JunctionConfiguration createJunction(ConfigurationData data) throws InvalidDirectionException {
        // unpack configuration data
        TrafficLightData trafficLightData = data.trafficLightData();
        DirectionData[] directionData = data.directionData();
        assert directionData.length == 4;

        // read in carriageway (direction) data
        // TODO: fix carriageway data from UI
        Carriageway[] carriageways = new Carriageway[4];
        for (int i = 0; i < directionData.length; i++) {
            carriageways[i] = readCarriageway(directionData[i]);
        }

        // read in group data
        int numGroups = trafficLightData.numGroups();
        GroupTimings groupTimings = trafficLightData.groupTimings();
        LaneGroups[] laneGroups = trafficLightData.directionalLaneGroups();
        Groups groups = readGroups(carriageways, numGroups, groupTimings, laneGroups);

        // read traffic light data
        TrafficLight trafficLight = readTrafficLight(trafficLightData.type());

        // assemble constructed data into singular junction configuration
        return new JunctionConfiguration(carriageways, trafficLight, groups);
    }
}
