package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Interface for factory for generating visualisations.
 */
public interface IVisualisationFactory {
    /**
     *
     * @param config The junction configuration to generate.
     * @return A model visualisation that can be notified by the model for updates.
     */
    IModelVisualisation createVisualisation(String configName, JunctionConfiguration config);
}
