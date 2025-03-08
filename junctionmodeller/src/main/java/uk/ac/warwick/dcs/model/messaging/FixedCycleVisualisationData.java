package uk.ac.warwick.dcs.model.messaging;

import java.util.List;

public class FixedCycleVisualisationData extends VisualisationData<FixedTiming> {
    public FixedCycleVisualisationData(List<FixedTiming> signalTimings) {
        super(EvaluationType.FIXEDCYCLE, signalTimings);
    }

    public List<FixedTiming> getFixedCycleTimings() {
        return getTimings();
    }
}
