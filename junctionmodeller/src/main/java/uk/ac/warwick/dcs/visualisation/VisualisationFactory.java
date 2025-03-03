package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

class VisualisationFactory implements IVisualisationFactory {
    @Override
    public ModelVisualisation createVisualisation(String configName, JunctionConfiguration config) {
        return new ModelVisualisation(configName);
    }
}
