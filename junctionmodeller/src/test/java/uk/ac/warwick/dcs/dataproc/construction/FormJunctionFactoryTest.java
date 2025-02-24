package uk.ac.warwick.dcs.dataproc.construction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.ac.warwick.dcs.contracts.builders.ICarriagewayBuilder;
import uk.ac.warwick.dcs.contracts.builders.IGroupBuilder;
import uk.ac.warwick.dcs.contracts.builders.ILightBuilder;

class FormJunctionFactoryTest {

    @Mock
    private ILightBuilder lightBuilder;

    @Mock
    private IGroupBuilder groupBuilder;

    @Mock
    private ICarriagewayBuilder[] carriagewayBuilders;

    @InjectMocks
    private FormJunctionFactory formJunctionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateJunctionValid() {

    }

    @Test
    void testCreateJunctionWithInvalidGroupNumber() {
    }

    @Test
    void testReadTrafficLight() {

    }
}
