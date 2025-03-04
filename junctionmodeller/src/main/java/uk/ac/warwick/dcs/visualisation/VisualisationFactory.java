package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.visualisation.structure.JunctionPane;

class VisualisationFactory implements IVisualisationFactory {
    private JunctionPane createPaneFromConfig(JunctionConfiguration config) {
        return new JunctionPane();
    }

    @Override
    public ModelVisualisation createVisualisation(String configName, JunctionConfiguration config) {
        return new ModelVisualisation(configName, createPaneFromConfig(config));
    }
}
