package uk.ac.warwick.dcs.dataproc;

import org.javatuples.Pair;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

import java.util.List;

/**
 * Interface for a service encapsulation of the loader classes. Used to make
 * the <code>DataService</code> easier to mock for testing.
 * @param <T> Data type used to load the junction configuration,
 *            can be a file path or a ConfigurationData object from
 *            the <code>ui</code> package.
 */
public interface ILoaderService<T> {
    Pair<JunctionConfiguration, List<String>> load(T data);
}
