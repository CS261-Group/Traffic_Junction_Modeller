package uk.ac.warwick.dcs.evaluation.lanemetrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaneMetricCalculatorTest {

    /**
     * Testing term Q2 (called second term), using data from Ackelik 2000, Appendix Figure 2
     */
    @Test
    void averageOverflowQueueHighX() {
        double Capacity = 720;
        double x = 1;
        double saturation = 1800;
        double green = 40;
        FixedtimeLaneMetricCalculator calculator = new FixedtimeLaneMetricCalculator(0.25);
        double queue = calculator.averageOverflowQueue(Capacity, x, saturation, green);
        assertEquals(9, (int) queue,
                "high deg of sat overflow queue should match example data");
    }

    @Test
    void averageOverflowQueueLowX() {
        double Capacity = 720;
        double x = 0.6;
        double saturation = 1800;
        double green = 40;
        FixedtimeLaneMetricCalculator calculator = new FixedtimeLaneMetricCalculator(0.25);
        double queue = calculator.averageOverflowQueue(Capacity, x, saturation, green);
        assertEquals(1, (int) queue,
                "low deg of sat overflow queue should match example data");
    }

    /**
     * Testing first term back of queue, sing data from Ackelik 2000, Appendix Figure 3
     */
    @Test
    void averageUniformQueueHighX() {
        double x = 1;
        double cycle = 100;
        double green = 40;
        double arrival = x * 720; //x * capacity
        FixedtimeLaneMetricCalculator calculator = new FixedtimeLaneMetricCalculator(0.25);
        double queue = calculator.averageUniformQueue(x, cycle, green, arrival);
        assertEquals(20, (int) queue,
                "high deg of sat uniform queue should match example data");
    }

    @Test
    void averageUniformQueueLowX() {
        double x = 0.4;
        double cycle = 100;
        double green = 40;
        double arrival = x * 720; //x * capacity
        FixedtimeLaneMetricCalculator calculator = new FixedtimeLaneMetricCalculator(0.25);
        double queue = calculator.averageUniformQueue(x, cycle, green, arrival);
        assertEquals(5, (int) queue,
                "low deg of sat uniform queue should match example data");
    }
}