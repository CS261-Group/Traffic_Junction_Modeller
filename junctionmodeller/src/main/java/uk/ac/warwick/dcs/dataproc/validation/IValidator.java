package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

import java.util.List;

public interface IValidator<T> {
    List<String> validate(T config);
}
