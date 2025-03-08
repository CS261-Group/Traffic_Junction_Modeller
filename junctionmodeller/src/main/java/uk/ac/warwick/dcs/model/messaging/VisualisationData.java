package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public abstract class VisualisationData {
    private final EvaluationType type;
    private final List<String> timings;

    protected VisualisationData(EvaluationType evaluationType, List<String> signalTimings) {
        type = evaluationType;
        timings = signalTimings;
    }

    protected EvaluationType getType() { return type; }

    public List<String> getTimings() { return timings; }
}
