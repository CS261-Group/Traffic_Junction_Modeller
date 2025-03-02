package uk.ac.warwick.dcs.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.dataproc.IDataService;

import java.awt.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MainFormTest {
    private static MainForm mainForm;

    @BeforeAll
    public static void setUp() {
        IDataService dataService = mock(IDataService.class);
        mainForm = new MainForm(dataService);
        mainForm.setVisible(false); // not required to be seen
    }

    private static void fontSizeMustBeGreaterThan14PtHelper(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                fontSizeMustBeGreaterThan14PtHelper((Container)component);
            } else {
                try {
                    // we use reflection to try to get a getText() method
                    Method getTextMethod = component.getClass().getMethod("getText");
                    String text = (String)getTextMethod.invoke(component);

                    assertTrue(text == null || text.isEmpty() || component.getFont().getSize() >= 14);
                } catch (Exception ex) {
                    // The component doesn't have a getText() method, so we ignore it
                }
            }
        }
    }

    /**
     * Testing for font sizes being above 14pt.
     */
    @Test
    public void fontSizeMustBeGreaterThan14Pt() {
        // Assert
        fontSizeMustBeGreaterThan14PtHelper(mainForm);
    }
}
