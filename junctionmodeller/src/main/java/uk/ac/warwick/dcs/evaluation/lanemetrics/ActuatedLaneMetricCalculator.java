package uk.ac.warwick.dcs.evaluation.lanemetrics;

public class ActuatedLaneMetricCalculator extends LaneMetricCalculator {

    // 1.1c IN akcelik 2000
    @Override
    public double getCalibrationConstant(double sg) {
        return 0.1 * Math.pow(sg, 0.6);

    }
}
