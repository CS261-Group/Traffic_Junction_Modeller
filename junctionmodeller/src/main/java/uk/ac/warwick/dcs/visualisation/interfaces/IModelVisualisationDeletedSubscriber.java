package uk.ac.warwick.dcs.visualisation.interfaces;

import uk.ac.warwick.dcs.visualisation.ModelVisualisation;

public interface IModelVisualisationDeletedSubscriber {
    ModelVisualisation notifyDeleted(ModelVisualisation deletedVisualisation);
}
