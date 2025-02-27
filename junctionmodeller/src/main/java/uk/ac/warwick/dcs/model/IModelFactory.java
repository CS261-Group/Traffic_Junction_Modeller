package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Interface for factory used to create <code>Model</code> class
 * instances from junction configurations.
 * Mainly used by <code>ModelContainer</code> class.
 */
public interface IModelFactory {
    Model createModel(JunctionConfiguration junctionConfiguration);
}
