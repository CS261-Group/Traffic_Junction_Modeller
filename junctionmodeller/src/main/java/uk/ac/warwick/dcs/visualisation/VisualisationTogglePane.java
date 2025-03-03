package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;

class VisualisationTogglePane extends StackPane implements IModelVisualisationSubscriber {
    public VisualisationTogglePane(int width, int height) {
        setMaxWidth(width);
        setMaxHeight(height);
    }

    public void changePane(Pane newPane) {
        getChildren().setAll(newPane);
    }

    @Override
    public void notify(ModelVisualisation visualisation) {
        changePane(visualisation);
    }
}
