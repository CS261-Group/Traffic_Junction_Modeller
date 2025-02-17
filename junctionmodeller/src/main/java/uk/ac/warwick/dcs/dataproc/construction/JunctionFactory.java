package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.builders.*;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidFlowValueException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.*;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.ui.formdata.*;

import java.util.List;

public class JunctionFactory implements IJunctionFactory<ConfigurationData> {
    private final ILightBuilder lightBuilder;
    private final IGroupBuilder groupBuilder;
    private final ICarriagewayBuilder[] carriagewayBuilders;

    public JunctionFactory() {
        lightBuilder = new LightBuilder();
        groupBuilder = new GroupBuilder();
        carriagewayBuilders = new CarriagewayBuilder[4];

        carriagewayBuilders[Direction.NORTH.ordinal()] = new CarriagewayBuilder(Direction.NORTH);
        carriagewayBuilders[Direction.EAST.ordinal()] = new CarriagewayBuilder(Direction.EAST);
        carriagewayBuilders[Direction.SOUTH.ordinal()] = new CarriagewayBuilder(Direction.SOUTH);
        carriagewayBuilders[Direction.WEST.ordinal()] = new CarriagewayBuilder(Direction.WEST);
    }

    private Groups readGroups(Carriageway[] carriageways, int numGroups, GroupTimings groupTimingsObj, LaneGroups[] laneGroupsArr) throws InvalidGroupNumberException, IncompleteBuildSettingsException {
        // optimise if chosen to and set the number of groups
        groupBuilder
                .setOptimiseTimings(groupTimingsObj.optimise())
                .setNumGroups(numGroups);

        assert groupTimingsObj.groupTimings().size() == numGroups; // sanity check: as many group timings as
        for (uk.ac.warwick.dcs.ui.formdata.GroupTiming timing : groupTimingsObj.groupTimings()) {
            groupBuilder.setGroupTiming(timing.time(), timing.groupNum());
        }

        for (LaneGroups laneGroups : laneGroupsArr) {
            Carriageway carriageway = carriageways[laneGroups.direction().ordinal()];
            IncomingRoad incomingRoad = carriageway.getIncoming();
            for (LaneGroup laneGroup : laneGroups.laneGroups()) {
                groupBuilder.addLaneToGroup(incomingRoad.get(laneGroup.laneNum()), laneGroup.groupNum());
            }
        }

        return groupBuilder.buildGroups();
    }

    private TrafficLight readTrafficLight(TrafficLightType type) throws IncompleteBuildSettingsException {
        return lightBuilder
                .setTrafficLightType(type)
                .buildTrafficLight();
    }

    private Carriageway readCarriageway(DirectionData directionData) throws InvalidDirectionException, InvalidFlowValueException, IncompleteBuildSettingsException {
        // unpack direction data
        Direction direction = directionData.direction();
        FlowData flowData = directionData.flowData();
        List<AvailableDirections> availableDirections = directionData.availableDirections();

        // unpack corresponding builder
        ICarriagewayBuilder builder = carriagewayBuilders[direction.ordinal()];

        // pedestrian crossing and bus lane
        builder.setBusLane(directionData.busLane());
        builder.setPedestrianCrossing(directionData.pedestrianCrossing());

        // if there is a bus lane add an EXTRA
        // TODO: determining queuing space
        if (directionData.busLane()) {
            builder.addIncomingLane(VehicleType.BUS, 15, new boolean[]{ true,true,true,true });
        }

        // construct outgoing road
        // TODO: how do we determine the number of outgoing roads
        builder.addOutgoingLane();

        // construct incoming road
        for (AvailableDirections directions : availableDirections) {
            boolean[] directionBools = new boolean[4];
            directionBools[Direction.NORTH.ordinal()] = directions.getN();
            directionBools[Direction.EAST.ordinal()] = directions.getE();
            directionBools[Direction.SOUTH.ordinal()] = directions.getS();
            directionBools[Direction.WEST.ordinal()] = directions.getW();
            // TODO: determining queuing space?
            builder.addIncomingLane(VehicleType.CAR, 5, directionBools);
        }

        // extract incoming flow and reset the corresponding value to 0
        int[] flows = flowData.flows(); // removed from record for ease of use
        int incomingFlow = flows[direction.ordinal()];
        flows[direction.ordinal()] = 0;
        builder.setIncomingFlow(incomingFlow);

        // add the outgoing flows in directions
        // which are not the incoming direction
        for (Direction dir : Direction.values()) {
            if (direction != dir) { // can't set outgoing flow for incoming direction
                builder.setOutgoingFlow(flows[dir.ordinal()], dir);
            }
        }

        // assemble carriageway
        return builder.buildCarriageway();
    }

    @Override
    public JunctionConfiguration createJunction(ConfigurationData data) throws InvalidDirectionException, InvalidGroupNumberException, InvalidFlowValueException, IncompleteBuildSettingsException {
        // unpack configuration data
        TrafficLightData trafficLightData = data.trafficLightData();
        DirectionData[] directionData = data.directionData();
        assert directionData.length == 4;

        // read in carriageway (direction) data
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
