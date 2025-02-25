package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.contracts.lights.ActuationTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightValidatorTest {
    private final TrafficLightValidator trafficLightValidator;

    public TrafficLightValidatorTest() {
        trafficLightValidator = new TrafficLightValidator(ValidatorFactory.getDiagnosticFactory());
    }

    @Test
    public void validateFixedCycle_ReturnsEmptyList_IfNoErrors() {
        // Act
        final List<String> actual = trafficLightValidator.validate(new FixedCycleTrafficLight());

        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }

    @Test
    public void validateActuation_ReturnsEmptyList_IfNoErrors() {
        // Act
        final List<String> actual = trafficLightValidator.validate(new ActuationTrafficLight());

        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }
}
