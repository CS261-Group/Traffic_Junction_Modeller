package uk.ac.warwick.dcs.dataproc.saving;

import uk.ac.warwick.dcs.dataproc.validation.IValidator;

public abstract class Saver {
    protected final IValidator validator;

    public Saver(IValidator validator) {
        this.validator = validator;
    }
}
