package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public class ModelFactory implements IModelFactory {

    public Model createModel(JunctionConfiguration junctionConfiguration) {
        // Create and return a new instance of the Model
        return new Model();
    }
}
