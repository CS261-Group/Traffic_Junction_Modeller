package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.structure.*;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LanesAssignmentValidatorTest {
    private final LaneAssignmentValidator validator;

    public LanesAssignmentValidatorTest() {
        validator = new LaneAssignmentValidator(ValidatorFactory.getDiagnosticFactory());
    }

    @Test
    public void validate_ReturnsEmptyList_IfNoErrors() {
        // Act
        final List<String> actual = validator.validate(Seeds.getGoodJunction());
        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }

    // TODO: failing cases
    @Test
    public void validate_ReturnsNonEmptyList_IfErrors() {
        // Arrange

        // NOTE: adapted from Seeds class' good junction configuration
        Carriageway[] carriageways = new Carriageway[4];
        int[] flows = new int[]{ 50, 50, 50, 50 };
        boolean[] directions = new boolean[]{ true, true, true, true };
        for (Direction direction : Direction.values()) {
            int[] specificFlows = flows.clone();
            boolean[] specificDirections = directions.clone();
            specificFlows[direction.ordinal()] = 0;
            specificDirections[direction.ordinal()] = false;
            IncomingRoad incomingRoad = new IncomingRoad(direction, List.of(
                    new IncomingLane(direction, VehicleType.CAR, specificDirections, 5),
                    new IncomingLane(direction, VehicleType.CAR, specificDirections, 5)
            ), 150, specificFlows);
            OutgoingRoad outgoingRoad = new OutgoingRoad(direction, List.of(new OutgoingLane(direction)));
            carriageways[direction.ordinal()] = new Carriageway(direction, outgoingRoad, incomingRoad, false, false);
        }
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
//                                carriageways[2].getIncomingLane(1),
                                carriageways[2].getIncomingLane(2)
                        ))
                ), true, null);
        JunctionConfiguration badJunctionConfig = new JunctionConfiguration(carriageways, new FixedCycleTrafficLight(), groups);

        // Act
        final List<String> actual = validator.validate(badJunctionConfig);

        // Assert
        assertNotEquals(0, actual.size());
        assertEquals(1, actual.size()); // it should only add a single error
        assertFalse(actual.isEmpty());
        assertArrayEquals(new String[]{"Not all of the junction's incoming lanes are assigned to a group."}, actual.toArray());
    }
}
