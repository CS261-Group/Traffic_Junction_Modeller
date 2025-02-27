package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JunctionValidatorTest {
    @Test
    public void validate_ReturnsEmptyList_IfNoErrors() {
        // Arrange
        CarriagewayValidator carriagewayValidator = mock(CarriagewayValidator.class);
        TrafficLightValidator trafficLightValidator = mock(TrafficLightValidator.class);
        GroupsValidator groupsValidator = mock(GroupsValidator.class);
        LaneAssignmentValidator laneAssignmentValidator = mock(LaneAssignmentValidator.class);
        when(carriagewayValidator.validate(any(Carriageway.class))).thenReturn(List.of());
        when(trafficLightValidator.validate(any(TrafficLight.class))).thenReturn(List.of());
        when(groupsValidator.validate(any(Groups.class))).thenReturn(List.of());
        when(laneAssignmentValidator.validate(any(JunctionConfiguration.class))).thenReturn(List.of());

        JunctionValidator junctionValidator = new JunctionValidator(
                ValidatorFactory.getDiagnosticFactory(),
                carriagewayValidator,
                trafficLightValidator,
                groupsValidator,
                laneAssignmentValidator);

        // Act
        final List<String> actual = junctionValidator.validate(Seeds.getGoodJunction());

        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }
}
