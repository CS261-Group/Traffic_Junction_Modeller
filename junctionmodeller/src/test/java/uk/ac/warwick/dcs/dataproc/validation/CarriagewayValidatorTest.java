package uk.ac.warwick.dcs.dataproc.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.structure.IncomingRoad;
import uk.ac.warwick.dcs.contracts.structure.OutgoingLane;
import uk.ac.warwick.dcs.contracts.structure.OutgoingRoad;

public class CarriagewayValidatorTest {
    private final CarriagewayValidator carriagewayValidator;

    public CarriagewayValidatorTest() {
        carriagewayValidator = new CarriagewayValidator(ValidatorFactory.getDiagnosticFactory());
    }

    private static Stream<Arguments> noErrorsInputs() {
        // create arguments of good carriageways in Seeds class
        return Arrays.stream(Seeds.getGoodCarriageways()).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("noErrorsInputs")
    public void validate_ShouldReturnEmptyList_IfNoErrors(Carriageway carriageway) {
        // Act
        final List<String> actual = carriagewayValidator.validate(carriageway);
        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }

    private static Stream<Arguments> flowsDoNotAddInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                        new Carriageway(
                                Direction.NORTH,
                                new OutgoingRoad(
                                        Direction.NORTH,
                                        List.of(
                                                new OutgoingLane(Direction.NORTH)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.NORTH,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.NORTH,
                                                        VehicleType.CAR,
                                                        Seeds.allDirectionsN,
                                                        1
                                                )
                                        ),
                                        150,
                                        new int[] { 0, 20, 30, 75 }
                                ),
                                false
                        ),
                        List.of(
                                "Incoming flow [150] does not equal sum of outgoing flows [E: 20; S: 30; W: 75]=125 in direction: NORTH"
                        )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.EAST,
                                new OutgoingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new OutgoingLane(Direction.EAST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.EAST,
                                                        VehicleType.CAR,
                                                        Seeds.allDirectionsE,
                                                        1
                                        )),
                                        150,
                                        new int[] { 50, 0, 30, 75 }
                                ),
                                false
                        ),
                        List.of(
                                "Incoming flow [150] does not equal sum of outgoing flows [N: 50; S: 30; W: 75]=155 in direction: EAST"
                        )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.SOUTH,
                                new OutgoingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new OutgoingLane(Direction.SOUTH)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        Seeds.allDirectionsS,
                                                        1
                                                )
                                        ),
                                        150,
                                        new int[] { 150, 150, 0, 150 }
                                ),
                                false
                        ),
                        List.of(
                                "Incoming flow [150] does not equal sum of outgoing flows [N: 150; E: 150; W: 150]=450 in direction: SOUTH"
                        )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.WEST,
                                new OutgoingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new OutgoingLane(Direction.WEST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        Seeds.allDirectionsW,
                                                        1
                                                )
                                        ),
                                        90,
                                        new int[] { 20, 30, 30, 0 }
                                ),
                                false
                        ),
                        List.of(
                                "Incoming flow [90] does not equal sum of outgoing flows [N: 20; E: 30; S: 30]=80 in direction: WEST"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("flowsDoNotAddInputsAndOutputs")
    public void validate_ShouldReturnNonEmptyList_IfFlowsDoNotAddUp(Carriageway carriageway, List<String> expected) {
        // Act
        final List<String> actual = carriagewayValidator.validate(carriageway);
        // Assert
        assertEquals(expected.size(), actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private static Stream<Arguments> laneOrderInputsAndOutputs() {
        return Stream.of(
                Arguments.of(
                    new Carriageway(
                            Direction.NORTH,
                            new OutgoingRoad(
                                    Direction.NORTH,
                                    List.of(
                                            new OutgoingLane(Direction.NORTH),
                                            new OutgoingLane(Direction.NORTH)
                                    )
                            ),
                            new IncomingRoad(
                                    Direction.NORTH,
                                    List.of(
                                            new IncomingLane(
                                                    Direction.NORTH,
                                                    VehicleType.CAR,
                                                    // right-only
                                                    new boolean[] {false, false, false, true},
                                                    1
                                            ),
                                            new IncomingLane(
                                                    Direction.NORTH,
                                                    VehicleType.CAR,
                                                    // left-forward
                                                    new boolean[] {false, true, true, false},
                                                    2
                                            )
                                    ),
                                    90,
                                    new int[] { 0, 30, 30, 30 }
                            ),
                            false
                    ),
                    List.of(
                            "The order of permitted lanes is invalid for lane #2 for direction: NORTH"
                    )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.EAST,
                                new OutgoingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new OutgoingLane(Direction.EAST),
                                                new OutgoingLane(Direction.EAST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.EAST,
                                                        VehicleType.CAR,
                                                        Seeds.allDirectionsE,
                                                        1
                                                ),
                                                new IncomingLane(
                                                        Direction.EAST,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {false, false, true, false},
                                                        2
                                                )
                                        ),
                                        90,
                                        new int[] { 30, 0, 30, 30 }
                                ),
                                false
                        ),
                        List.of(
                                "The order of permitted lanes is invalid for lane #2 for direction: EAST"
                        )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.SOUTH,
                                new OutgoingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new OutgoingLane(Direction.SOUTH),
                                                new OutgoingLane(Direction.SOUTH)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // left-forward
                                                        new boolean[] {true, false, false, true},
                                                        1
                                                ),
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // all-directions
                                                        Seeds.allDirectionsS,
                                                        2
                                                ),
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // left-forward
                                                        new boolean[] {true, false, false, true},
                                                        3
                                                )
                                        ),
                                        90,
                                        new int[] { 30, 30, 0, 30 }
                                ),
                                false
                        ),
                        List.of(
                                "The order of permitted lanes is invalid for lane #3 for direction: SOUTH"
                        )
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.WEST,
                                new OutgoingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new OutgoingLane(Direction.WEST),
                                                new OutgoingLane(Direction.WEST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // left-forward
                                                        new boolean[] {true, true, false, false},
                                                        1
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // forward-only
                                                        new boolean[] {false, true, false, false},
                                                        2
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // all-directions
                                                        Seeds.allDirectionsW,
                                                        3
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // all-directions
                                                        Seeds.allDirectionsW,
                                                        4
                                                )
                                        ),
                                        90,
                                        new int[] { 30, 30, 30, 0 }
                                ),
                                false
                        ),
                        List.of(
                                "The order of permitted lanes is invalid for lane #3 for direction: WEST"
                        )
                ),
                // succeeding case
                Arguments.of(
                        new Carriageway(
                                Direction.WEST,
                                new OutgoingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new OutgoingLane(Direction.WEST),
                                                new OutgoingLane(Direction.WEST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.WEST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {true, false, false, false},
                                                        1
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {true, false, false, false},
                                                        2
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // forward-only
                                                        new boolean[] {false, true, false, false},
                                                        3
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // right-forward
                                                        new boolean[] {false, true, true, false},
                                                        4
                                                ),
                                                new IncomingLane(
                                                        Direction.WEST,
                                                        VehicleType.CAR,
                                                        // right-only
                                                        new boolean[] {false, false, true, false},
                                                        5
                                                )
                                        ),
                                        90,
                                        new int[] { 30, 30, 30, 0 }
                                ),
                                false
                        ),
                        List.of() // no errors
                )
        );
    }

    @ParameterizedTest
    @MethodSource("laneOrderInputsAndOutputs")
    public void validate_ShouldReturnNonEmptyList_IfInvalidLaneOrder(Carriageway carriageway, List<String> expected) {
        // Act
        final List<String> actual = carriagewayValidator.validate(carriageway);
        // Assert
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private static Stream<Arguments> outgoingFlowExistence() {
        return Stream.of(
                Arguments.of(
                        new Carriageway(
                                Direction.NORTH,
                                new OutgoingRoad(
                                        Direction.NORTH,
                                        List.of(
                                                new OutgoingLane(Direction.NORTH)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.NORTH,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.NORTH,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {false, true, false, false},
                                                        1
                                                )
                                        ),
                                        90,
                                        new int[] { 0, 30, 30, 30 }
                                ),
                                false
                        ),
                        List.of(
                                "No lane permits direction(s) {SW} from direction: NORTH"
                        )
                ),
                // succeeding case
                Arguments.of(
                        new Carriageway(
                                Direction.EAST,
                                new OutgoingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new OutgoingLane(Direction.EAST)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.EAST,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.EAST,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {false, false, true, true},
                                                        2
                                                )
                                        ),
                                        60,
                                        new int[] { 0, 0, 30, 30 }
                                ),
                                false
                        ),
                        List.of()
                ),
                Arguments.of(
                        new Carriageway(
                                Direction.SOUTH,
                                new OutgoingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new OutgoingLane(Direction.SOUTH)
                                        )
                                ),
                                new IncomingRoad(
                                        Direction.SOUTH,
                                        List.of(
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // left-only
                                                        new boolean[] {false, false, false, true},
                                                        1
                                                ),
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // forward-only
                                                        new boolean[] {true, false, false, false},
                                                        2
                                                ),
                                                new IncomingLane(
                                                        Direction.SOUTH,
                                                        VehicleType.CAR,
                                                        // forward-only
                                                        new boolean[] {true, false, false, false},
                                                        3
                                                )
                                        ),
                                        150,
                                        new int[] { 50, 50, 0, 50 }
                                ),
                                false
                        ),
                        List.of(
                                "No lane permits direction(s) {E} from direction: SOUTH"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("outgoingFlowExistence")
    public void validate_ShouldReturnNonEmptyList_IfNoOutgoingFlowExists(Carriageway carriageway, List<String> expected) {
        // Act
        final List<String> actual = carriagewayValidator.validate(carriageway);
        // Assert
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
