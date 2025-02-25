package uk.ac.warwick.dcs.dataproc;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataServiceTest {
    @Mock
    private IValidator<JunctionConfiguration> validator;
    @Mock
    private IModelContainer modelContainer;
    @Mock
    private ILoaderService<ConfigurationData> formLoaderService;

    private DataService dataService;

    @BeforeEach
    public void setUp() {
        validator = (IValidator<JunctionConfiguration>)mock(IValidator.class);
        modelContainer = mock(IModelContainer.class);
        formLoaderService = (ILoaderService<ConfigurationData>)mock(ILoaderService.class);
        dataService = new DataService(validator, modelContainer, formLoaderService);
    }

    @Test
    public void submitEnteredConfiguration_ReturnsEmptyList_OnSuccess() {
        // Arrange
        ConfigurationData configData = new ConfigurationData(null, null);
        when(validator.validate(any())).thenReturn(List.of());
        when(formLoaderService.load(any())).thenReturn(new Pair<>(any(), null));

        // Act
        final List<String> actual = dataService.submitEnteredConfiguration(configData);

        // Assert
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());
    }
}
