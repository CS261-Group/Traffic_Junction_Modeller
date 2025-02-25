package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DiagnosticFactoryTest {
    private final DiagnosticFactory df;

    public DiagnosticFactoryTest() {
        df = new DiagnosticFactory();
    }

    private static Stream<Arguments> fieldErrorInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                        "somefield",
                        "some error occurred with this field",
                        "somefield: some error occurred with this field"
                ),
                Arguments.of(
                        "direction",
                        "must be one of N, E, S, W",
                        "direction: must be one of N, E, S, W"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("fieldErrorInputsAndOutputs")
    public void createFieldErrorMessage_ShouldReturnCorrectString(String field, String error, String expected) {
        // Arrange
        // Act
        final String actual = df.createFieldErrorMessage(field, error);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> invalidFlowSumMessageInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                        Direction.NORTH,
                        150,
                        new int[]{0, 50, 50, 45},
                        "Incoming flow [150] does not equal sum of outgoing flows [E: 50; S: 50; W: 45]=145 in direction: NORTH"
                ),
                Arguments.of(
                        Direction.EAST,
                        20,
                        new int[]{0, 0, 10, 9},
                        "Incoming flow [20] does not equal sum of outgoing flows [N: 0; S: 10; W: 9]=19 in direction: EAST"
                ),
                Arguments.of(
                        Direction.SOUTH,
                        350,
                        new int[]{100, 20, 0, 250},
                        "Incoming flow [350] does not equal sum of outgoing flows [N: 100; E: 20; W: 250]=370 in direction: SOUTH"
                ),
                Arguments.of(
                        Direction.WEST,
                        195,
                        new int[]{10, 10, 10, 0},
                        "Incoming flow [195] does not equal sum of outgoing flows [N: 10; E: 10; S: 10]=30 in direction: WEST"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFlowSumMessageInputsAndOutputs")
    public void createInvalidFlowSumMessage_ShouldReturnCorrectString(Direction direction, int incomingFlow, int[] outgoingFlows, String expected) {
        // Arrange
        // Act
        final String actual = df.createInvalidFlowSumMessage(direction, incomingFlow, outgoingFlows);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> invalidNumLanesInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                        Direction.NORTH,
                        "incoming",
                        "Invalid number of incoming lanes in direction: NORTH"
                ),
                Arguments.of(
                        Direction.WEST,
                        "outgoing",
                        "Invalid number of outgoing lanes in direction: WEST"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidNumLanesInputsAndOutputs")
    public void createInvalidNumLanesMessage(Direction direction, String type, String expected) {
        // Arrange
        // Act
        final String actual = df.createInvalidNumLanesMessage(direction, type);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> outgoingFlowErrorInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                        Direction.NORTH,
                        new boolean[]{false, true, false, false},
                        "No lane permits direction(s) {NSW} from direction: NORTH"
                ),
                Arguments.of(
                        Direction.EAST,
                        new boolean[]{true, false, false, false},
                        "No lane permits direction(s) {ESW} from direction: EAST"
                ),
                Arguments.of(
                        Direction.SOUTH,
                        new boolean[]{true, true, true, false},
                        "No lane permits direction(s) {W} from direction: SOUTH"
                ),
                Arguments.of(
                        Direction.WEST,
                        new boolean[]{false, false, false, false},
                        "No lane permits direction(s) {NESW} from direction: WEST"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("outgoingFlowErrorInputsAndOutputs")
    public void createOutgoingFlowErrorMessage_ShouldReturnCorrectString(Direction direction, boolean[] validDirections, String expected) {
        // Arrange
        // Act
        final String actual = df.createOutgoingFlowErrorMessage(direction, validDirections);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> invalidPermittedDirectionsInputsAndOutputs() {
        return Stream.of(
                Arguments.of(Direction.NORTH, 1, "The order of permitted lanes is invalid for lane #1 for direction: NORTH"),
                Arguments.of(Direction.EAST, 2, "The order of permitted lanes is invalid for lane #2 for direction: EAST")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPermittedDirectionsInputsAndOutputs")
    public void createInvalidPermittedDirectionsMessage_ShouldReturnCorrectString(Direction direction, int laneNum, String expected) {
        // Arrange
        // Act
        final String actual = df.createInvalidPermittedDirectionsMessage(direction, laneNum);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> invalidGroupNumInputsAndOutputs() {
        return Stream.of(
                Arguments.of(0, "Invalid group number: 0"),
                Arguments.of(-1, "Invalid group number: -1"),
                Arguments.of(5, "Invalid group number: 5")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidGroupNumInputsAndOutputs")
    public void createInvalidGroupNumMessage_ShouldReturnCorrectString(int groupNum, String expected) {
        // Arrange
        // Act
        final String actual = df.createInvalidGroupNumMessage(groupNum);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void createInvalidLaneAssignmentMessage_ShouldReturnCorrectString() {
        // Arrange
        final String expected = "Not all of the junction's incoming lanes are assigned to a group.";
        // Act
        final String actual = df.createInvalidLaneAssignmentMessage();
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> invalidNumGroupsInputsAndOutputs() {
        return Stream.of(
                Arguments.of(0, "Invalid number of groups: 0. Must be between " + Groups.MIN_NUM_GROUPS + " and " + Groups.MAX_GROUP_NUM + "."),
                Arguments.of(-2, "Invalid number of groups: -2. Must be between " + Groups.MIN_NUM_GROUPS + " and " + Groups.MAX_GROUP_NUM + "."),
                Arguments.of(10, "Invalid number of groups: 10. Must be between " + Groups.MIN_NUM_GROUPS + " and " + Groups.MAX_GROUP_NUM + ".")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidNumGroupsInputsAndOutputs")
    public void createInvalidNumGroupsMessage_ShouldReturnCorrectString(int numGroups, String expected) {
        // Arrange
        // Act
        final String actual = df.createInvalidNumGroupsMessage(numGroups);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> groupNumbersNotSeparateInputsAndOutputs() {
        return Stream.of(
                Arguments.of(1, "Multiple groups have group number: 1"),
                Arguments.of(3, "Multiple groups have group number: 3"),
                Arguments.of(7, "Multiple groups have group number: 7")
        );
    }

    @ParameterizedTest
    @MethodSource("groupNumbersNotSeparateInputsAndOutputs")
    public void createGroupNumbersNotSeparateMessage_ShouldReturnCorrectString(int groupNum, String expected) {
        // Arrange
        // Act
        final String actual = df.createGroupNumbersNotSeparateMessage(groupNum);
        // Assert
        assertEquals(expected, actual);
    }

}
