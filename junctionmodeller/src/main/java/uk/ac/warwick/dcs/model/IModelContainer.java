package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.model.exceptions.NoSuchModelException;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

/**
 * Interface that the objects that contain models must implement.
 */
public interface IModelContainer {
    /**
     * Create and add a model instance based on some supplied junction configuration.
     * @param junctionConfiguration The configuration to start analysis for
     * @param visualisation The visualisation to send updates to.
     * @return The created model ID if no errors, -1 otherwise. Usually the only reason for an
     *         error is that we have reached the maximum number of concurrently running
     *         models.
     */
    boolean addModel(JunctionConfiguration junctionConfiguration, IModelVisualisation visualisation);

    /**
     *
     * @param modelId ID of the model to evaluate.
     * @return <code>JunctionMetrics</code> object containing mathematically
     *         evaluated metrics.
     */
    JunctionMetrics evaluateModel(long modelId) throws NoSuchModelException;
}
