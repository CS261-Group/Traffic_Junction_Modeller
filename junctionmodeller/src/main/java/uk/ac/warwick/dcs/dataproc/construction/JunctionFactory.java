package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.builders.*;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.ui.formdata.*;

import java.util.ArrayList;
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

    private Groups readGroups(int numGroups, GroupTimings groupTimings, LaneGroups[] laneGroups) {
        boolean optimising = groupTimings.optimise();

        List<Group> groupList = new ArrayList<>(numGroups);
        // if we are optimising, we don't need to specify group timings
        Groups groups;
        if (optimising) {
            // TODO: fix null inputs
            groups = new Groups(null, true, null);
        } else {
            // TODO: figure out group timings
            // TODO: fix null inputs
            groups = new Groups(null, false, null);
        }

        return groups;
    }

    private TrafficLight readTrafficLight(TrafficLightType type) {
        return lightBuilder
                .setTrafficLightType(type)
                .buildTrafficLight();
    }

    // TODO: what are the required parameters
    private Carriageway readCarriageway() {
        return null;
    }

    @Override
    public JunctionConfiguration createJunction(ConfigurationData data) {
        // unpack configuration data
        TrafficLightData trafficLightData = data.trafficLightData();
        DirectionData[] directionData = data.directionData();
        assert directionData.length == 4;

        // read in carriageway (direction) data
        // TODO: fix carriageway data from UI
        Carriageway[] carriageways = new Carriageway[4];
        for (int i = 0; i < directionData.length; i++) {
            // select corresponding builder
            ICarriagewayBuilder builder = carriagewayBuilders[i];

            carriageways[i] = readCarriageway();
        }

        // read in group data
        int numGroups = trafficLightData.numGroups();
        GroupTimings groupTimings = trafficLightData.groupTimings();
        LaneGroups[] laneGroups = trafficLightData.directionalLaneGroups();
        Groups groups = readGroups(numGroups, groupTimings, laneGroups);

        // read traffic light data
        TrafficLight trafficLight = readTrafficLight(trafficLightData.type());

        // assemble constructed data into singular junction configuration
        return new JunctionConfiguration(carriageways, trafficLight, groups);
    }
}
