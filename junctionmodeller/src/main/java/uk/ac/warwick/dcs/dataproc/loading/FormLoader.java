package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.construction.JunctionFactory;
import uk.ac.warwick.dcs.ui.formdata.*;

import java.util.List;

public class FormLoader implements ILoader {
    private final ConfigurationData configData;

    public FormLoader(ConfigurationData configData) {
        this.configData = configData;
    }

    @Override
    public JunctionConfiguration load() {
        // the builder we will construct the junction configuration with
        JunctionFactory builder = new JunctionFactory();

        TrafficLightData trafficLightData = configData.trafficLightData();
        int numGroups = trafficLightData.numGroups();

        // distinct lane groups
        LaneGroups[] laneGroups = trafficLightData.directionalLaneGroups();
        assert laneGroups.length == numGroups;

        // group timings
        GroupTimings groupTimings = trafficLightData.groupTimings();
        boolean optimising = groupTimings.optimise();
        List<GroupTiming> timings = groupTimings.groupTimings();
        assert timings.size() == numGroups;

        // directional data
        DirectionData[] directionData = configData.directionData();
        assert directionData.length == 4;

        // construction
        // TODO: impl
        return null;
    }
}
