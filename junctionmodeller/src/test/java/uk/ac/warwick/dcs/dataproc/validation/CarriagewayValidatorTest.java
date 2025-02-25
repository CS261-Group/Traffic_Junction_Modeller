package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    // TODO: DON'T TEST num lanes being positive
    // TODO: TEST sums of outgoing flows != incoming flow
    // TODO: TEST lane order
    // TODO: TEST existence of outgoing flows FAILING
    // TODO: TEST existence of outgoing flows even if 0 (no available directions) SUCCEEDING

}
