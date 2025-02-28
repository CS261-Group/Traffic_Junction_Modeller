package uk.ac.warwick.dcs.model;

/**
 * Static class used to initialise an <code>IModalContainer</code>
 * while separating concerns and keeping access to <code>model</code>
 * library to a minimum.
 * Contains singleton instance of <code>IModelFactory</code> and
 * <code>IIdGenerationService</code> to be used in object instantiations.
 */
public class ModelContainerBuilder {
    /**
     * Singleton instance of <code>IModelFactory</code>.
     */
    private static IModelFactory modelFactory = null;

    /**
     * Singleton instance of <code>IIdGenerationService</code>
     */
    private static IIdGenerationService idGenService = null;

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
            modelFactory = new ModelFactory(getIdGenerationService());
        }
        return modelFactory;
    }

    private static IIdGenerationService getIdGenerationService() {
        if (idGenService == null) {
            idGenService = new IdGenerationService();
        }
        return idGenService;
    }
}
