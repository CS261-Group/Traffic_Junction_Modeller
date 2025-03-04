package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

class VisualisationFactory implements IVisualisationFactory {
    @Override
    public IModelVisualisation createVisualisation(String configName, JunctionConfiguration config) {
        // TODO: hook into Ed's work
        return new Visualiser();
    }
}
