package uk.ac.warwick.dcs.model.messaging;

public class OptimisationUpdate extends ModelUpdate<VisualisationData> {
    public OptimisationUpdate(VisualisationData metrics) {
        super(ModelUpdateType.OPTIMISATION, metrics);
    }

    public VisualisationData getOptimisation() {
        return getPayload();
    }
}
