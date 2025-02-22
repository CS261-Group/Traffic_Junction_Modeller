package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ModelContainer implements IModelContainer {
    private Set<Model> models = new HashSet<>();
    private IModelFactory modelFactory;

    public ModelContainer(IModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    // Add a model to the container
    public void addModel(JunctionConfiguration junctionConfiguration) {
        Model model = modelFactory.createModel(junctionConfiguration);
        models.add(model);
    }
}
