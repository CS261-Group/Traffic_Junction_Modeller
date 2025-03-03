package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.Evaluation;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;

// function to minimise for Hill climb
// uses junction metrics right now
public class EvaluationFunction {
    public Evaluation evaluation; // evaluation instance to use

    public EvaluationFunction(Evaluation evaluation){
        this.evaluation = evaluation;
    }

    public double evaluationAt(JunctionData data){
        JunctionMetrics metrics = evaluation.getEvaluation(data);
        return metrics.getAverageDelay();
    }
}
