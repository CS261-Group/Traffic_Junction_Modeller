package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;
import uk.ac.warwick.dcs.optimisation.Optimiser;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

/**
 * Class used to hold settings and configurations for a running
 * model instance being analysed.
 */
class Model implements Runnable {
    private final long id;
    private final Evaluation evaluation;
    private final Optimiser optimiser;
    private final IModelVisualisation visualisation;
    private final JunctionConfiguration junctionConfig;

    public Model(long id, JunctionConfiguration junctionConfig, IModelVisualisation visualisation) {
        this.id = id;
        this.junctionConfig = junctionConfig;

        // optimiser initialises values
        if (junctionConfig.getOptimising()) {
            this.optimiser = new Optimiser(junctionConfig);
        } else {
            this.optimiser = null;
        }

        this.evaluation = new Evaluation(junctionConfig);
        this.visualisation = visualisation;
    }

    /**
     *
     * @return The ID of this model instance.
     */
    public long getId() { return id; }

    /**
     * Evaluate the model using its own junction configuration.
     * @return JunctionMetrics for the junction configuration.
     */
    public JunctionMetrics evaluateModel() {
        return evaluation.getEvaluation(new JunctionData(junctionConfig));
    }

    /**
     * Optimise the model using a specific junction configuration.
     * @param junctionConfiguration The configuration for the junction to be evaluated.
     */
    public void optimiseModel(JunctionConfiguration junctionConfiguration) {
        // TODO: implement this
    }

    @Override
    public int hashCode() { return (int)id; }

    @Override
    public void run() {
        // TODO: SGD updates
    }
}
