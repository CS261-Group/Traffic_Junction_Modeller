package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.DiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IDiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.dataproc.validation.JunctionValidator;

public class DataServiceBuilder {
    public static IDataService buildService() {
        IDiagnosticFactory diagnosticFactory = new DiagnosticFactory();
        IValidator<JunctionConfiguration> junctionValidator = new JunctionValidator(diagnosticFactory);
        return new DataService(junctionValidator);
    }
}
