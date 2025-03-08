package uk.ac.warwick.dcs.dataproc.construction;

import java.util.List;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.builders.CarriagewayBuilder;
import uk.ac.warwick.dcs.contracts.builders.GroupBuilder;
import uk.ac.warwick.dcs.contracts.builders.ICarriagewayBuilder;
import uk.ac.warwick.dcs.contracts.builders.IGroupBuilder;
import uk.ac.warwick.dcs.contracts.builders.ILightBuilder;
import uk.ac.warwick.dcs.contracts.builders.LightBuilder;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidFlowValueException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupTimingException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.FlowData;
import uk.ac.warwick.dcs.ui.formdata.GroupTimings;
import uk.ac.warwick.dcs.ui.formdata.LaneGroup;
import uk.ac.warwick.dcs.ui.formdata.LaneGroups;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;

/**
 * Concrete implementation of <code>IJunctionFactory</code> for taking
 * form inputs from <code>ui</code> package.
 */
public class FormJunctionFactory implements IJunctionFactory<ConfigurationData> {
    private final ILightBuilder lightBuilder;
    private final IGroupBuilder groupBuilder;
    private final ICarriagewayBuilder[] carriagewayBuilders;

    public FormJunctionFactory() {
        lightBuilder = new LightBuilder();
        groupBuilder = new GroupBuilder();
        carriagewayBuilders = new CarriagewayBuilder[4];

        carriagewayBuilders[Direction.NORTH.ordinal()] = new CarriagewayBuilder(Direction.NORTH);
        carriagewayBuilders[Direction.EAST.ordinal()] = new CarriagewayBuilder(Direction.EAST);
        carriagewayBuilders[Direction.SOUTH.ordinal()] = new CarriagewayBuilder(Direction.SOUTH);
        carriagewayBuilders[Direction.WEST.ordinal()] = new CarriagewayBuilder(Direction.WEST);
    }

    /**
     * We through
     * @param carriageways Array of all <code>Carriageway</code> objects used to
     *                     access all the incoming lanes in the junction.
     * @param numGroups The number of groups to configure.
     * @param groupTimingsObj Relevant <code>GroupTimings</code> object.
     * @param laneGroupsArr Array of <code>LaneGroups</code> object.
     * @return The <code>Groups</code> object.
     */
    private Groups readGroups(Carriageway[] carriageways, int numGroups, GroupTimings groupTimingsObj, LaneGroups[] laneGroupsArr) throws InvalidGroupNumberException, IncompleteBuildSettingsException, InvalidGroupTimingException {
        // optimise if chosen to and set the number of groups
        groupBuilder
                .setOptimiseTimings(groupTimingsObj.optimise())
                .setNumGroups(numGroups);

        // sanity check: as many group timings as number of groups
        assert groupTimingsObj.groupTimings().size() == numGroups;
        for (uk.ac.warwick.dcs.ui.formdata.GroupTiming timing : groupTimingsObj.groupTimings()) {
            groupBuilder.setGroupTiming(timing.time(), timing.groupNum());
        }

        for (LaneGroups laneGroups : laneGroupsArr) {
            Carriageway carriageway = carriageways[laneGroups.direction().ordinal()];
            for (LaneGroup laneGroup : laneGroups.laneGroups()) {
                groupBuilder.addLaneToGroup(carriageway.getIncomingLane(laneGroup.laneNum()), laneGroup.groupNum());
            }
        }

        return groupBuilder.buildGroups();
    }

    /**
     *
     * @param type Type of traffic light this junction uses.
     * @return The instance of the built traffic light object.
     */
    private TrafficLight readTrafficLight(TrafficLightType type) {
        try {
            return lightBuilder
                    .setTrafficLightType(type)
                    .buildTrafficLight();
        } catch (IncompleteBuildSettingsException ex) {
            // sanity check: this must never happen since the only compulsory
            // setting is passed in as a parameter
            assert false;
        }
        return null;
    }

    /**
     *
     * @param directionData The data of the lanes and flows in the direction
     *                      of the carriageway we are building.
     * @return The carriageway object built.
     */
    private Carriageway readCarriageway(DirectionData directionData) throws InvalidDirectionException,
            InvalidFlowValueException, IncompleteBuildSettingsException, InvalidPermittedDirectionsException {
        // unpack direction data
        Direction direction = directionData.direction();
        FlowData flowData = directionData.flowData();
        List<AvailableDirections> availableDirections = directionData.availableDirections();

        // unpack corresponding builder
        ICarriagewayBuilder builder = carriagewayBuilders[direction.ordinal()];

        // pedestrian crossing
        builder.setPedestrianCrossing(false);

//        builder.setBusLane(directionData.busLane());
//        // if there is a bus lane add an EXTRA
//        if (directionData.busLane()) {
//            builder.addIncomingLane(VehicleType.BUS, new boolean[]{ true,true,true,true });
//        }

        // construct outgoing road
        for (int i = 0; i < directionData.numOutgoingLanes(); i++) {
            builder.addOutgoingLane();
        }

        // construct incoming road
        for (AvailableDirections directions : availableDirections) {
            boolean[] directionBools = new boolean[4];
            directionBools[Direction.NORTH.ordinal()] = directions.getN();
            directionBools[Direction.EAST.ordinal()] = directions.getE();
            directionBools[Direction.SOUTH.ordinal()] = directions.getS();
            directionBools[Direction.WEST.ordinal()] = directions.getW();
            builder.addIncomingLane(VehicleType.CAR, directionBools);
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
    public JunctionConfiguration createJunction(ConfigurationData data) throws InvalidDirectionException,
            InvalidGroupNumberException, InvalidFlowValueException, IncompleteBuildSettingsException,
            InvalidPermittedDirectionsException, InvalidGroupTimingException {
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
