package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;

public interface IJunctionFactory<T> {
    JunctionConfiguration createJunction(T data) throws InvalidDirectionException;
}
