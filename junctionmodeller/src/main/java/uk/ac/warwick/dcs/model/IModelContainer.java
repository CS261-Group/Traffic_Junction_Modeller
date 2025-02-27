package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Interface that the objects that contain models must implement.
 */
public interface IModelContainer {
    /**
     * Create and add a model instance based on some supplied junction configuration.
     * @param junctionConfiguration The configuration to start analysis for
     * @return True if no errors, false otherwise. Usually the only reason for an
     *         error is that we have reached the maximum number of concurrently running
     *         models.
     */
    boolean addModel(JunctionConfiguration junctionConfiguration);
}
