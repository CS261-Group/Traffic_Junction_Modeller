package uk.ac.warwick.dcs.dataproc.construction;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.*;

/**
 * Interface for factory used to create <code>JunctionConfiguration</code>
 * DTO using local data types. Chiefly used by <code>ui</code> library
 * for generating <code>JunctionConfiguration</code> from file paths
 * for configuration files and collected form data.
 * @param <T> Data type of local data type to convert from.
 */
public interface IJunctionFactory<T> {
    /**
     * Creates a <code>JunctionConfiguration</code> object and forwards any exceptions
     * thrown from <code>ILoader</code> or any <code>IValidator</code> instances used.
     * @param data Local data structure to convert to <code>JunctionConfiguration</code>
     *             object.
     * @return Created instance of <code>JunctionConfiguration</code> object.
     */
    JunctionConfiguration createJunction(T data) throws InvalidDirectionException,
            InvalidGroupNumberException, InvalidFlowValueException, IncompleteBuildSettingsException,
            InvalidPermittedDirectionsException, InvalidGroupTimingException;
}
