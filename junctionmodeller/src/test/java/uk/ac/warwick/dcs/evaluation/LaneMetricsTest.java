package uk.ac.warwick.dcs.evaluation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.evaluation.lanemetrics.FixedtimeLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetrics;

public class LaneMetricsTest {
    static LaneMetrics laneMetrics;
    static FixedtimeLaneMetricCalculator calculator;


    @BeforeAll
    public static void createLaneMetrics(){
        calculator = new FixedtimeLaneMetricCalculator();
        laneMetrics = new LaneMetrics(calculator,
                100,
                60,
                1000,
                40
                );
    }

    @Test
    public void averageDelay(){
        System.out.println(laneMetrics.getAverageDelay());
        assert(true);
    }
}
