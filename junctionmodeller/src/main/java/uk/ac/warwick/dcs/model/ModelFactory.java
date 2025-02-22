package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Concrete implementation of <code>IModelFactory</code> interface.
 */
public class ModelFactory implements IModelFactory {
    @Override
    public Model createModel(JunctionConfiguration junctionConfiguration) {
        // Create and return a new instance of the Model
        return new Model();
    }
}
