package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.DiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IDiagnosticFactory;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.dataproc.validation.JunctionValidator;
import uk.ac.warwick.dcs.model.IModelContainer;

public class DataServiceBuilder {
    public static IDataService buildService(IModelContainer modelContainer) {
        IDiagnosticFactory diagnosticFactory = new DiagnosticFactory();
        IValidator<JunctionConfiguration> junctionValidator = new JunctionValidator(diagnosticFactory);
        return new DataService(junctionValidator, modelContainer);
    }
}
