package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.params.provider.Arguments;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.structure.*;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

class Seeds {
    private static JunctionConfiguration goodJunctionConfiguration = null;
    private static Carriageway[] goodCarriageways = null;

    static JunctionConfiguration getGoodJunction() {
        if (goodJunctionConfiguration == null) {
            Carriageway[] carriageways = getGoodCarriageways();
            Groups groups = new Groups(
                    List.of(
                            new Group(1, List.of(
                                    carriageways[1].getIncomingLane(1),
                                    carriageways[1].getIncomingLane(2),
                                    carriageways[3].getIncomingLane(1),
                                    carriageways[3].getIncomingLane(2)
                            )),
                            new Group(2, List.of(
                                    carriageways[0].getIncomingLane(1),
                                    carriageways[0].getIncomingLane(2),
                                    carriageways[2].getIncomingLane(1),
                                    carriageways[2].getIncomingLane(2)
                            ))
                    ), true, null);
            goodJunctionConfiguration = new JunctionConfiguration(carriageways, new FixedCycleTrafficLight(), groups);
        }
        return goodJunctionConfiguration;
    }

    static Groups goodGroups = null;

    static Groups getGoodGroups() {
        if (goodGroups == null) {
            List<Group> groups = List.of(
                    new Group(1, any(List.class)),
                    new Group(2, any(List.class))
            );
            goodGroups = new Groups(groups, true, null);
        }
        return goodGroups;
    }

    static Carriageway[] getGoodCarriageways() {
        if (goodCarriageways == null) {
            goodCarriageways = new Carriageway[4];
            int[] flows = new int[]{ 50, 50, 50, 50 };
            boolean[] directions = new boolean[]{ true, true, true, true };
            for (Direction direction : Direction.values()) {
                int[] specificFlows = flows.clone();
                boolean[] specificDirections = directions.clone();
                specificFlows[direction.ordinal()] = 0;
                specificDirections[direction.ordinal()] = false;
                IncomingRoad incomingRoad = new IncomingRoad(direction, List.of(
                        new IncomingLane(direction, VehicleType.CAR, specificDirections),
                        new IncomingLane(direction, VehicleType.CAR, specificDirections)
                ), 150, specificFlows);
                OutgoingRoad outgoingRoad = new OutgoingRoad(direction, List.of(new OutgoingLane(direction)));
                goodCarriageways[direction.ordinal()] = new Carriageway(direction, outgoingRoad, incomingRoad, false, false);
            }
        }
        return goodCarriageways;
    }

    static boolean[] allDirectionsN = new boolean[] { false, true, true, true };
    static boolean[] allDirectionsE = new boolean[] { true, false, true, true };
    static boolean[] allDirectionsS = new boolean[] { true, true, false, true };
    static boolean[] allDirectionsW = new boolean[] { true, true, true, false };
}
