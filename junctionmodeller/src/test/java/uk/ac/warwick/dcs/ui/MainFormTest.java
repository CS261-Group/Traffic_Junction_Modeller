package uk.ac.warwick.dcs.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.ui.panels.DirectionInputsPanel;
import uk.ac.warwick.dcs.ui.panels.DirectionPanel;

import javax.swing.*;
import java.awt.Container;
import java.awt.Component;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void trafficLightDefaultSelection() {
        // Arrange - BeforeEach setUp function resets the main form to default
        // Act
        ConfigurationData configData = mainForm.getConfigDataFromForm();
        TrafficLightType lightType = configData.trafficLightData().type();

        // Assert
        assertEquals(lightType, TrafficLightType.FIXEDCYCLE);
    }

    private static void pedestrianCrossingHelper(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof DirectionInputsPanel directionInputsPanel) {
                boolean havePedestrianCrossing = false;
                for (Component subComponent : directionInputsPanel.getComponents()) {
                    if (subComponent instanceof JCheckBox checkBox) {
                        if (checkBox.getText().equalsIgnoreCase("pedestrian crossing")) {
                            havePedestrianCrossing = true;
                            break;
                        }
                    }
                }
                assertTrue(havePedestrianCrossing);
                break;
            }

            if (component instanceof Container nestedContainer) {
                pedestrianCrossingHelper(nestedContainer);
            }
        }
    }

    @Test
    public void eachDirectionMustHavePedestrianCrossingCheckBox() {
        // Assert
        pedestrianCrossingHelper(mainForm);
    }

    private static int mustBe4DirectionPanelsHelper(Container container) {
        int count = 0;
        for (Component component : container.getComponents()) {
            if (component instanceof DirectionPanel) {
                count += 1;
            } else if (component instanceof Container nestedContainer) {
                count += mustBe4DirectionPanelsHelper(nestedContainer);
            }
        }
        return count;
    }

    @Test
    public void mustBe4DirectionPanels() {
        // Act
        final int numDirectionPanels = mustBe4DirectionPanelsHelper(mainForm);
        // Assert
        assertEquals(4, numDirectionPanels);
    }
}
