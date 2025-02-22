package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

/**
 * Interface for any class which is supposed to load a
 * <code>JunctionConfiguration</code> DTO from any source.
 */
public interface ILoader {
    /**
     * This method should use some data taken from the constructor.
     * @return Loaded <code>JunctionConfiguration</code> object.
     */
    JunctionConfiguration load();

    /**
     * There might be multiple errors while loading the data.
     * We get only get the load error from the first.
     * @return The first error that occurred while loading the data.
     */
    String getLoadErrors();
}
