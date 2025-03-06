package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationChangedSubscriber;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationDeletedSubscriber;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;

class VisualisationTogglePane extends StackPane implements IModelVisualisationSubscriber, IModelVisualisationChangedSubscriber, IModelVisualisationDeletedSubscriber {
    public VisualisationTogglePane(int width, int height) {
        setMaxWidth(width);
        setMaxHeight(height);
    }

    public void changePane(Pane newPane) {
        if (newPane != null) {
            getChildren().setAll(newPane);
        } else {
            getChildren().clear();
        }
    }

    @Override
    public void notifyAdd(ModelVisualisation visualisation) {
        changePane(visualisation);
    }

    @Override
    public void notifyChanged(ModelVisualisation visualisation) {
        changePane(visualisation);
    }

    @Override
    public ModelVisualisation notifyDeleted(ModelVisualisation deletedVisualisation) {
        changePane(null);
        return null;
    }
}
