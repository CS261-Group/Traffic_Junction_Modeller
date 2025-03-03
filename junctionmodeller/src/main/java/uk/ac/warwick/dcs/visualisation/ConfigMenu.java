package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.VBox;

import java.util.List;

public class ConfigMenu extends VBox {
    private final List<ModelVisualisation> modelVisualisations;

    public ConfigMenu(List<ModelVisualisation> visualisations) {
        modelVisualisations = visualisations;
    }
}
