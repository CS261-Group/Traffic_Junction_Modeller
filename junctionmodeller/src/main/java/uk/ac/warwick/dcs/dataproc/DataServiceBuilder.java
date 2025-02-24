package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.*;
import uk.ac.warwick.dcs.model.IModelContainer;

/**
 * Static class used to initialise an <code>IDataService</code>
 * object to be used by the <code>ui</code> and <code>visualisation</code>
 * packages while separating concerns and keeping access to
 * <code>dataproc</code> library to a minimum.
 * Contains singleton instance of <code>DataService</code> object.
 */
public class DataServiceBuilder {
    /**
     *
     * @param modelContainer Model container for service to use.
     * @return Singleton instance of data service.
     */
    public static IDataService buildService(IModelContainer modelContainer) {
        return new DataService(ValidatorFactory.getJunctionValidator(), modelContainer);
    }
}
