package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class GroupsValidatorTest {
    private final GroupsValidator groupsValidator;

    public GroupsValidatorTest() {
        groupsValidator = new GroupsValidator(ValidatorFactory.getDiagnosticFactory());
    }

    @Test
    public void validate_ShouldReturnEmptyList_IfNoErrors() {
        // Act
        final List<String> actual = groupsValidator.validate(Seeds.getGoodGroups());

        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }

    private static Stream<Arguments> invalidGroupNumsSeed() {
        return Stream.of(
                Arguments.of(new Groups(List.of(
                        new Group(0, null),
                        new Group(5, null)
                ), true, null), List.of("Invalid group number: 0", "Invalid group number: 5"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidGroupNumsSeed")
    public void validateGroupNums_ShouldReturnListOfErrors_IfInvalidGroupNumber(Groups groups, List<String> expectedErrors) {
        // Act
        List<String> actual = groupsValidator.validate(groups);

        // Assert
        assertArrayEquals(expectedErrors.toArray(), actual.toArray());
    }

    private static Stream<Arguments> validNumGroupsSeed() {
        return Stream.of(
                Arguments.of(new Groups(List.of(
                        new Group(1, null),
                        new Group(2, null)
                ), true, null))
        );
    }

    @ParameterizedTest
    @MethodSource("validNumGroupsSeed")
    public void validateNumGroups_ShouldReturnEmptyList_IfNoErrors(Groups groups) {
        // Act
        List<String> actual = groupsValidator.validate(groups);

        // Assert
        assertTrue(actual.isEmpty());
    }

    private static Stream<Arguments> invalidNumGroupsSeed() {
        return Stream.of(
                Arguments.of(
                        new Groups(List.of(), true, null),
                        List.of("Invalid number of groups: 0. Must be between 2 and 6.")
                ),
                Arguments.of(
                        new Groups(List.of(
                                new Group(1, null),
                                new Group(2, null),
                                new Group(3, null),
                                new Group(4, null),
                                new Group(5, null),
                                new Group(6, null),
                                new Group(7, null)
                        ), true, null),
                        List.of("Invalid number of groups: 7. Must be between 2 and 6."))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidNumGroupsSeed")
    public void validateNumGroups_ShouldReturnListOfErrors_IfInvalidNumberOfGroups(Groups groups, List<String> expectedErrors) {
        // Act
        List<String> actual = groupsValidator.validate(groups);

        // Assert
        assertArrayEquals(expectedErrors.toArray(), actual.toArray());
    }

    private static Stream<Arguments> validateGroupNumsErrorsSeed() {
        List<IncomingLane> someListOfLanes = (List<IncomingLane>)mock(List.class);

        return Stream.of(
                Arguments.of(
                        new Groups(List.of(
                                new Group(1, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes)
                        ), true, null),
                        List.of(
                                "Multiple groups have group number: 2"
                        )
                ),
                Arguments.of(
                        new Groups(List.of(
                                new Group(1, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(3, someListOfLanes),
                                new Group(3, someListOfLanes)
                                ), true, null),
                        List.of(
                                "Multiple groups have group number: 2",
                                "Multiple groups have group number: 3"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validateGroupNumsErrorsSeed")
    public void validateGroupNums_ShouldReturnListOfErrors_IfMultipleGroupsWithSameNumber(Groups groups, List<String> expectedErrors) {
        // Arrange
        // Act
        final List<String> actual = groupsValidator.validate(groups);
        // Assert
        assertArrayEquals(expectedErrors.toArray(), actual.toArray());
    }

    private static Stream<Arguments> validateGroupNumsMultipleErrorsSeed() {
        List<IncomingLane> someListOfLanes = (List<IncomingLane>)mock(List.class);

        return Stream.of(
                Arguments.of(
                        new Groups(List.of(
                                new Group(1, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes)
                        ), true, null),
                        List.of(
                                "Multiple groups have group number: 2",
                                "Multiple groups have group number: 2"
                        )
                ),
                Arguments.of(
                        new Groups(List.of(
                                new Group(1, someListOfLanes),
                                new Group(1, someListOfLanes),
                                new Group(1, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes),
                                new Group(2, someListOfLanes)
                                ), true, null),
                        List.of(
                                "Multiple groups have group number: 1",
                                "Multiple groups have group number: 1",
                                "Multiple groups have group number: 2",
                                "Multiple groups have group number: 2"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validateGroupNumsMultipleErrorsSeed")
    public void validateGroupNums_ShouldReturnListOfErrorsWithRepeats_IfMultipleGroupsWithSameNumber(Groups groups, List<String> expectedErrors) {
        // Arrange
        // Act
        final List<String> actual = groupsValidator.validate(groups);
        // Assert
        assertArrayEquals(expectedErrors.toArray(), actual.toArray());
    }
}
