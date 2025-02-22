package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * Used to store instance of models under analysis. Responsible
 * for managing the parallelism between the running models.
 */
public class ModelContainer implements IModelContainer {
    private final Set<Model> models;
    private final IModelFactory modelFactory;

    public ModelContainer(IModelFactory modelFactory) {
        this.models = new HashSet<>();
        this.modelFactory = modelFactory;
    }

    @Override
    public void addModel(JunctionConfiguration junctionConfiguration) {
        Model model = modelFactory.createModel(junctionConfiguration);
        models.add(model);
    }
}
