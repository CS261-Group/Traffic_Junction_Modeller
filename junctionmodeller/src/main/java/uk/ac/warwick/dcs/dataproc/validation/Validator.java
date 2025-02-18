package uk.ac.warwick.dcs.dataproc.validation;

public abstract class Validator<T> implements IValidator<T> {
    protected final IDiagnosticFactory diagFactory;

    public Validator(IDiagnosticFactory diagnosticFactory) {
        diagFactory = diagnosticFactory;
    }
}
