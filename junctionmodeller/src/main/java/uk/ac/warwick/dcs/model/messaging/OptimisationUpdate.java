package uk.ac.warwick.dcs.model.messaging;

import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

public class OptimisationUpdate extends ModelUpdate<JunctionData> {
    public OptimisationUpdate(JunctionData container) {
        super(ModelUpdateType.OPTIMISATION, container);
    }

    public JunctionData getOptimisation() {
        return getPayload();
    }
}
