package uk.ac.warwick.dcs.model.messaging;

import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;

public class EvaluationUpdate extends ModelUpdate<JunctionMetrics> {
    public EvaluationUpdate(JunctionMetrics evaluation) {
        super(ModelUpdateType.EVALUATION, evaluation);
    }

    public JunctionMetrics getMetrics() {
        return getPayload();
    }
}
