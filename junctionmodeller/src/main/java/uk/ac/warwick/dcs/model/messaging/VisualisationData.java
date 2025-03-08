package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public abstract class VisualisationData<T> {
    private final EvaluationType type;
    private final List<T> timings;

    protected VisualisationData(EvaluationType evaluationType, List<T> signalTimings) {
        type = evaluationType;
        timings = signalTimings;
    }

    protected EvaluationType getType() { return type; }

    protected List<T> getTimings() { return timings; }
}
