package uk.ac.warwick.dcs.model;

import java.util.HashSet;
import java.util.Set;

public class ModelContainer {
    private Set<Model> models = new HashSet<>();
    private ModelFactory modelFactory;

    public ModelContainer(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    // Add a model to the container
    public void addModel() {
        Model model = modelFactory.createModel();
        models.add(model);
    }
}
