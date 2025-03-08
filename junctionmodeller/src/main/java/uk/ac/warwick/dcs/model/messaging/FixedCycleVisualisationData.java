package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public class FixedCycleVisualisationData extends VisualisationData {
    public FixedCycleVisualisationData(List<String> signalTimings) {
        super(EvaluationType.FIXEDCYCLE, signalTimings);
    }
}
