package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

/**
 * Concrete implementation of <code>IModelFactory</code> interface.
 */
class ModelFactory implements IModelFactory {
    private final IIdGenerationService idGenService;

    public ModelFactory(IIdGenerationService idGenerationService) {
        idGenService = idGenerationService;
    }

    @Override
    public Model createModel(JunctionConfiguration junctionConfiguration, IModelVisualisation visualisation) {
        // Create and return a new instance of the Model
        return new Model(idGenService.generateId(), visualisation);
    }
}
