package uk.ac.warwick.dcs.dataproc.validation;

/**
 * Abstract class implementing <code>IValidator</code> interface
 * to make storing the <code>IDiagnosticFactory</code> interface
 * as a field simpler for each derived validator class.
 * @param <T> Data type to validate.
 */
public abstract class Validator<T> implements IValidator<T> {
    protected final IDiagnosticFactory diagFactory;

    public Validator(IDiagnosticFactory diagnosticFactory) {
        diagFactory = diagnosticFactory;
    }
}
