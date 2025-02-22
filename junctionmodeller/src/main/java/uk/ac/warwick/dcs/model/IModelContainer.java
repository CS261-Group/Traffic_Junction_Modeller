package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Interface that the objects that contain models must implement.
 */
public interface IModelContainer {
    /**
     * Create and add a model instance based on some supplied junction configuration.
     * @param junctionConfiguration The configuration to start analysis for
     */
    void addModel(JunctionConfiguration junctionConfiguration);
}
