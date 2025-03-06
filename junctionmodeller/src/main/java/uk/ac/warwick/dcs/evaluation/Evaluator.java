package uk.ac.warwick.dcs.evaluation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.evaluation.lanemetrics.ActuatedLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.FixedtimeLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetricCalculator;

// concrete class, that takes a junction, and gives back metrics about that junction
// instantiated to be able to evaluate a specific junction
// (with a correct number of actuated and fixed time metric calculators)
public class Evaluator {
    JunctionConfiguration junctionConfig;
    LaneMetricCalculator calculator;
    TrafficLightType type;

    public Evaluator(JunctionConfiguration junctionConfig) {
        this.junctionConfig = junctionConfig;
        this.type = junctionConfig.getTrafficLightType();
        // create a calculator
        if (type == TrafficLightType.ACTUATION) {
            calculator = new ActuatedLaneMetricCalculator();
        } else {
            calculator = new FixedtimeLaneMetricCalculator();
        }
    }

    public JunctionMetrics getEvaluation(JunctionData data) {
        if (type == TrafficLightType.FIXEDCYCLE) {
            return new JunctionMetrics((FixedtimeLaneMetricCalculator) calculator, data);
        } else{
            return new JunctionMetrics((ActuatedLaneMetricCalculator) calculator, data);
        }
    }
}
