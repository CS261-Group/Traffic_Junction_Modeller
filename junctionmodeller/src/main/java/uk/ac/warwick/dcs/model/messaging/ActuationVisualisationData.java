package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public class ActuationVisualisationData extends VisualisationData {
    public ActuationVisualisationData(List<String> signalTimings) {
        super(EvaluationType.ACTUATION, signalTimings);
    }
}
