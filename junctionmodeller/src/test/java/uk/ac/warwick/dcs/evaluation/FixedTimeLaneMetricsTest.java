package uk.ac.warwick.dcs.evaluation;

import org.junit.jupiter.api.*;
import uk.ac.warwick.dcs.evaluation.lanemetrics.FixedtimeLaneMetricCalculator;
import uk.ac.warwick.dcs.evaluation.lanemetrics.LaneMetrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedTimeLaneMetricsTest {
    FixedtimeLaneMetricCalculator calculator  = new FixedtimeLaneMetricCalculator(0.25);;

    /**
     * Time-Dependent Expressions for Delay, Stop Rate and Queue Length at Traffic
     * Signals, 1980
     * Table A1, column 4
     */
    public void testAkcelikAppendixValues1980() {
        calculator  = new FixedtimeLaneMetricCalculator(0.1);;
        double cycleTime = 90;
        double greenTime = 0.5 * cycleTime; // g = u * c
        double saturationRate = 3600;
        double arrivalRate = 1440;

        LaneMetrics laneMetrics = new LaneMetrics(calculator,
                arrivalRate,
                cycleTime,
                saturationRate,
                greenTime
                );

        System.out.println("Avg queue: " + laneMetrics.getAverageQueueLength());
        assertEquals(19.6, laneMetrics.getAverageDelay(),
                "average delay should match results from Akcelik");
    }


    /**
     * Akçelik - HCM 2000 Queue Model, figure A,3
     */
    @Test
    public void testAkcelikAppendixValues2000MidSat() {
        double cycleTime = 100;
        double greenTime = 40;
        double saturationRate = 1800;
        double arrivalRate = 576; // x = 0.8

        LaneMetrics laneMetrics = new LaneMetrics(calculator,
                arrivalRate,
                cycleTime,
                saturationRate,
                greenTime
        );

        System.out.println("Avg delay: " + laneMetrics.getAverageDelay());
        assertEquals(17, (int) laneMetrics.getAverageQueueLength(),
                "average back of queue match results from Akcelik");
    }

    @Test
    public void testAkcelikAppendixValues2000LowSat() {
        double cycleTime = 100;
        double greenTime = 40;
        double saturationRate = 1800;
        double arrivalRate = 288; // x = 0.4

        LaneMetrics laneMetrics = new LaneMetrics(calculator,
                arrivalRate,
                cycleTime,
                saturationRate,
                greenTime
        );

        System.out.println("Avg delay: " + laneMetrics.getAverageDelay());
        assertEquals(6, Math.round(laneMetrics.getAverageQueueLength()),
                "average back of queue match results from Akcelik");
    }
}
