package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.DiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IDiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.dataproc.validation.JunctionValidator;
import uk.ac.warwick.dcs.model.IModelContainer;

/**
 * Static class used to initialise an <code>IDataService</code>
 * object to be used by the <code>ui</code> and <code>visualisation</code>
 * packages while separating concerns and keeping access to
 * <code>dataproc</code> library to a minimum.
 * Contains singleton instances of <code>IDiagnosticFactory</code> and
 * <code>IValidator&lt;JunctionConfiguration&gt;</code>
 */
public class DataServiceBuilder {
    /**
     * Singleton instance of <code>IDiagnosticFactory</code>
     */
    private static IDiagnosticFactory diagnosticFactory = null;

    /**
     * Singleton instance of <code>IValidator&lt;JunctionConfiguration&gt;</code>
     */
    private static IValidator<JunctionConfiguration> junctionValidator = null;
    /**
     *
     * @param modelContainer Model container for service to use.
     * @return New instance of data service using singleton instances of
     *         <code>IDiagnosticFactory</code> and <code>
     *             IValidator&lt;JunctionConfiguration&gt;
     *         </code>
     */
    public static IDataService buildService(IModelContainer modelContainer) {
        return new DataService(getJunctionValidator(), modelContainer);
    }

    /**
     *
     * @return Singleton instance of <code>IDiagnosticFactory</code>.
     */
    private static IDiagnosticFactory getDiagnosticFactory() {
        if (diagnosticFactory == null) {
            diagnosticFactory = new DiagnosticFactory();
        }
        return diagnosticFactory;
    }

    /**
     *
     * @return Singleton instance of <code>IValidator&lt;JunctionConfiguration&gt;</code>.
     */
    private static IValidator<JunctionConfiguration> getJunctionValidator() {
        if (junctionValidator == null) {
            junctionValidator = new JunctionValidator(getDiagnosticFactory());
        }
        return junctionValidator;
    }
}
