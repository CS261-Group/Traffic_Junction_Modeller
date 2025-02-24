package uk.ac.warwick.dcs.dataproc.validation;

import org.junit.jupiter.api.Test;

public class LanesAssignmentValidatorTest {
    private final LaneAssignmentValidator lav;

    public LanesAssignmentValidatorTest() {
        lav = new LaneAssignmentValidator(ValidatorFactory.getDiagnosticFactory());
    }
}
