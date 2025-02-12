package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.dataproc.validation.IValidator;

public abstract class Loader {
    protected final IValidator validator;

    protected Loader(IValidator validator) {
        this.validator = validator;
    }
}
