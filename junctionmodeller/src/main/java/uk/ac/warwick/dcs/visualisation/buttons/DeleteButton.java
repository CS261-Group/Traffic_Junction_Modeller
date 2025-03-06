package uk.ac.warwick.dcs.visualisation.buttons;

import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.Visualiser;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationChangedSubscriber;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationDeletedSubscriber;

import java.util.List;

public class DeleteButton extends UiButton implements IModelVisualisationChangedSubscriber {
    private ModelVisualisation currentVisualisation;
    private final List<IModelVisualisationDeletedSubscriber> deletedSubscribers;

    public DeleteButton(List<IModelVisualisationDeletedSubscriber> subscribers) {
        super("/delete.png");

        deletedSubscribers = subscribers;

        // initially there is no active model visualisation
        currentVisualisation = null;

        setOnAction(e -> {
            // if there is an active visualisation, we can delete it
            if (currentVisualisation != null) {
                for (IModelVisualisationDeletedSubscriber subscriber : deletedSubscribers) {
                    ModelVisualisation newVisualisation = subscriber.notifyDeleted(currentVisualisation);

                    // replace if it's the visualiser singleton's return value
                    if (subscriber == Visualiser.getInstance()) {
                        currentVisualisation = newVisualisation;
                    }
                }
            }
        });
    }

    @Override
    public void notifyChanged(ModelVisualisation visualisation) {
        currentVisualisation = visualisation;
    }
}
