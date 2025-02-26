package uk.ac.warwick.dcs.evaluation.lanemetrics;

public class FixedtimeLaneMetricCalculator extends LaneMetricCalculator {

    @Override
    public double getCalibrationConstant(double sg) {
        return 0;
    }
}
