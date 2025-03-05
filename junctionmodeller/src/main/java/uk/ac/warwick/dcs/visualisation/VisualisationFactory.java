package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.visualisation.structure.JunctionPane;

class VisualisationFactory implements IVisualisationFactory {
    private JunctionPane createPaneFromConfig(JunctionConfiguration config) {
        int[] outgoingLaneCounts = new int[4];
        int[] incomingLaneCounts = new int[4];
        boolean[][][] incomingLaneDirections = new boolean[4][][];
        int[][] incomingLaneGroupNums = new int[4][];
        for (Carriageway carriageway : config) {
            // assign number of outgoing lanes
            outgoingLaneCounts[carriageway.getDirection().ordinal()] = carriageway.getNumOutgoingLanes();

            // assign number of incoming lanes
            incomingLaneCounts[carriageway.getDirection().ordinal()] = carriageway.getNumIncomingLanes();

            // assign group numbers
            incomingLaneGroupNums[carriageway.getDirection().ordinal()] = new int[carriageway.getNumIncomingLanes()];

            // assign permitted directions
            incomingLaneDirections[carriageway.getDirection().ordinal()] = new boolean[carriageway.getNumIncomingLanes()][4];

            for (int laneNum = 1; laneNum <= carriageway.getNumIncomingLanes(); laneNum++) {
                IncomingLane lane = carriageway.getIncomingLane(laneNum);
                for (Direction direction : Direction.values()) {
                    if (direction != carriageway.getDirection()) {
                        incomingLaneDirections[carriageway.getDirection().ordinal()][laneNum - 1][direction.ordinal()] =
                                lane.allowsGoing(direction);
                    }
                }

                // find the right group
                for (Group group : config.getGroups()) {
                    if (group.containsLane(lane)) {
                        incomingLaneGroupNums[carriageway.getDirection().ordinal()][laneNum - 1] = group.getGroupNum();
                        break;
                    }
                }
            }
        }
        return new JunctionPane(outgoingLaneCounts, incomingLaneCounts, incomingLaneDirections, incomingLaneGroupNums);
    }

    @Override
    public ModelVisualisation createVisualisation(String configName, JunctionConfiguration config) {
        return new ModelVisualisation(configName, createPaneFromConfig(config));
    }
}
