package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;

public interface IJunctionFactory<T> {
    JunctionConfiguration createJunction(T data) throws InvalidDirectionException, InvalidGroupNumberException;
}
