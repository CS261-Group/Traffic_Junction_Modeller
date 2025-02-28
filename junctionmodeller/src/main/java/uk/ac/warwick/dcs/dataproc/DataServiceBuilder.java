package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.dataproc.validation.*;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.visualisation.VisualisationFactoryBuilder;

/**
 * Static class used to initialise an <code>IDataService</code>
 * object to be used by the <code>ui</code> and <code>visualisation</code>
 * packages while separating concerns and keeping access to
 * <code>dataproc</code> library to a minimum.
 * Contains singleton instance of <code>DataService</code> object and an
 * instance of a <code>FormLoaderService</code> object.
 */
public class DataServiceBuilder {
    /**
     * Singleton for form loader to be injected into data service.
     */
    private static ILoaderService<ConfigurationData> formLoaderService = null;

    /**
     *
     * @param modelContainer Model container for service to use.
     * @return Singleton instance of data service.
     */
    public static IDataService buildService(IModelContainer modelContainer) {
        return new DataService(ValidatorFactory.getJunctionValidator(), modelContainer,
                getFormLoaderService(), VisualisationFactoryBuilder.getVisualisationFactory());
    }

    /**
     *
     * @return Singleton instance of form loader service.
     */
    static ILoaderService<ConfigurationData> getFormLoaderService() {
        if (formLoaderService == null) {
            formLoaderService = new FormLoaderService();
        }
        return formLoaderService;
    }
}
