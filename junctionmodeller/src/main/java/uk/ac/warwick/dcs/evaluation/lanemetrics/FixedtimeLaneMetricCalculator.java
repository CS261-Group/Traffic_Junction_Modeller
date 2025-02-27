package uk.ac.warwick.dcs.evaluation.lanemetrics;

public class FixedtimeLaneMetricCalculator extends LaneMetricCalculator {

    // 1.1d IN akcelik 2000
    @Override
    public double getCalibrationConstant(double sg) {
        return 0.12 * Math.pow(sg, 0.7);
    }
}
