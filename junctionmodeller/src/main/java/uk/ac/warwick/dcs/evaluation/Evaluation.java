package uk.ac.warwick.dcs.evaluation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;

// concrete class, that takes a junction, and gives back metrics about that junction
// instantiated to be able to evaluate a specific junction
// (with a correct number of actuated and fixed time metric calculators)
public class Evaluation {
    JunctionConfiguration junctionConfig;


    public Evaluation(JunctionConfiguration junctionConfig) {
        this.junctionConfig = junctionConfig;
    }

    public JunctionMetrics getEvaluation(JunctionData data) {
        return new JunctionMetrics(junctionConfig.getTrafficLightType(), data);
    }
}
