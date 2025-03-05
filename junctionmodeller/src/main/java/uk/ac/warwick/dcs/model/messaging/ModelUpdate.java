package uk.ac.warwick.dcs.model.messaging;

/**
 * Parent class of any update object sent to the visualiser.
 * @param <T> type of data to pass.
 */
public abstract class ModelUpdate<T> {
    private final ModelUpdateType type;
    private final T payload;

    public ModelUpdate(ModelUpdateType modelUpdateType, T data) {
        type = modelUpdateType;
        payload = data;
    }

    public ModelUpdateType getType() { return type; }

    protected T getPayload() { return payload; }
}
