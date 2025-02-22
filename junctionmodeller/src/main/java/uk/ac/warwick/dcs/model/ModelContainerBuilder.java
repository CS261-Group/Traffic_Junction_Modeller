package uk.ac.warwick.dcs.model;

public class ModelContainerBuilder {
    public static IModelContainer buildModelContainer() {
        IModelFactory modelFactory = new ModelFactory();
        return new ModelContainer(modelFactory);
    }
}
