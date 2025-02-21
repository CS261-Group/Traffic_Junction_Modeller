package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.*;

public interface IJunctionFactory<T> {
    JunctionConfiguration createJunction(T data) throws InvalidDirectionException, InvalidGroupNumberException, InvalidFlowValueException, IncompleteBuildSettingsException, InvalidPermittedDirectionsException, InvalidGroupTimingException;
}
