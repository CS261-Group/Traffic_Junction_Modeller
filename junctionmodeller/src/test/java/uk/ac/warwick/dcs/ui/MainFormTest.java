package uk.ac.warwick.dcs.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.dataproc.IDataService;

import java.awt.Container;
import java.awt.Component;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MainFormTest {
    private static MainForm mainForm;

    @BeforeEach
    public void setUp() {
        IDataService dataService = mock(IDataService.class);
        mainForm = new MainForm(dataService);
        mainForm.setVisible(false); // don't need to see the UI
    }

    private static void fontSizeMustBeGreaterThan14PtHelper(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                fontSizeMustBeGreaterThan14PtHelper((Container)component);
            }

            try {
                // we use reflection to try to get a getText() method
                Method getTextMethod = component.getClass().getMethod("getText");
                String text = (String)getTextMethod.invoke(component);

                int fontSize = component.getFont().getSize();
                assertTrue(text == null || text.isEmpty() || fontSize >= 14);
            } catch (Exception ex) {
                // The component doesn't have a getText() method, so we ignore it
            }
        }
    }

    @Test
    public void fontSizeMustBeGreaterThan14Pt() {
        // Assert
        fontSizeMustBeGreaterThan14PtHelper(mainForm);
    }

    @Test
    public void fontSizeMustBeGreaterThan14PtWithErrors() {
        // Arrange
        mainForm.setErrors(List.of("Error 1", "Error 2"));

        // Assert
        fontSizeMustBeGreaterThan14PtHelper(mainForm);
    }


}
