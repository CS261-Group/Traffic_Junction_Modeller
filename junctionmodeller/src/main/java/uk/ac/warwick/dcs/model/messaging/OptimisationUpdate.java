package uk.ac.warwick.dcs.model.messaging;

public class OptimisationUpdate<T> extends ModelUpdate<VisualisationData<T>> {
    public OptimisationUpdate(VisualisationData<T> metrics) {
        super(ModelUpdateType.OPTIMISATION, metrics);
    }

    protected VisualisationData<T> getOptimisation() {
        return getPayload();
    }
}
