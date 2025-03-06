package uk.ac.warwick.dcs.visualisation.interfaces;

import uk.ac.warwick.dcs.visualisation.ModelVisualisation;

public interface IModelVisualisationChangedSubscriber {
    void notifyChanged(ModelVisualisation visualisation);
}
