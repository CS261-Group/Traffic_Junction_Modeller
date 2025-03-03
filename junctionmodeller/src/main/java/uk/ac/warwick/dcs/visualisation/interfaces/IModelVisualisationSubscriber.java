package uk.ac.warwick.dcs.visualisation.interfaces;

import uk.ac.warwick.dcs.visualisation.ModelVisualisation;

/**
 * Interface for subscribers to event that new Model added to visualisation.
 */
public interface IModelVisualisationSubscriber {
    void notify(ModelVisualisation visualisation);
}
