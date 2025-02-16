package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public interface IJunctionFactory<T> {
    JunctionConfiguration createJunction(T data);
}
