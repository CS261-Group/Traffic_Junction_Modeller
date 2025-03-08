package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public class ActuationVisualisationData extends VisualisationData<ActuatedTiming> {
    public ActuationVisualisationData(List<ActuatedTiming> signalTimings) {
        super(EvaluationType.ACTUATION, signalTimings);
    }

    public List<ActuatedTiming> getActuatedTimings() {
        return getTimings();
    }
}
