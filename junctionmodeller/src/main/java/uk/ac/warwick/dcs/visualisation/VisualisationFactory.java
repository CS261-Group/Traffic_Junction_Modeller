package uk.ac.warwick.dcs.visualisation;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

class VisualisationFactory implements IVisualisationFactory {
    @Override
    public ModelVisualisation createVisualisation(String configName, JunctionConfiguration config) {
        // TODO: set up properly
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.LIGHTGRAY, // Background color
                new CornerRadii(10), // Rounded corners
                Insets.EMPTY // No padding
        );
        ModelVisualisation visualisation = new ModelVisualisation(configName);
        visualisation.setBackground(new Background(backgroundFill));

        return visualisation;
    }
}
