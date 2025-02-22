package uk.ac.warwick.dcs.model;

/**
 * Static class used to initialise an <code>IModalContainer</code>
 * while separating concerns and keeping access to <code>model</code>
 * library to a minimum.
 * Contains singleton instance of <code>IModelFactory</code>.
 */
public class ModelContainerBuilder {
    /**
     * Singleton instance of <code>IModelFactory</code>.
     */
    private static IModelFactory modelFactory = null;

    /**
     *
     * @return New instance of <code>IModelContainer</code> which uses
     *         the singleton instance of <code>IModelFactory</code>.
     */
    public static IModelContainer buildModelContainer() {
        return new ModelContainer(getModelFactory());
    }

    /**
     *
     * @return Singleton instance of <code>IModelFactory</code>
     */
    private static IModelFactory getModelFactory() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
}
