package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

class VisualisationTogglePane extends StackPane {
    public VisualisationTogglePane(int width, int height) {
        setMaxWidth(width);
        setMaxHeight(height);
    }

    public void changePane(Pane newPane) {
        getChildren().setAll(newPane);
    }
}
