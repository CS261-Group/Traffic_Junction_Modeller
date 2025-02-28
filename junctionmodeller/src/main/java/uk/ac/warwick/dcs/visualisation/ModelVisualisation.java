package uk.ac.warwick.dcs.visualisation;

import uk.ac.warwick.dcs.model.messaging.ModelUpdate;

import java.util.LinkedList;
import java.util.Queue;

public class ModelVisualisation implements IModelVisualisation {
    private final Queue<ModelUpdate> updateQueue;

    public ModelVisualisation() {
        updateQueue = new LinkedList<>();
    }

    @Override
    public void notify(ModelUpdate update) {
        updateQueue.add(update);
    }

    protected ModelUpdate consume() {
        return updateQueue.poll();
    }
}
