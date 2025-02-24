package uk.ac.warwick.dcs.dataproc.validation;

import java.util.List;

public interface IValidator<T> {
    List<String> validate(T config);
}
