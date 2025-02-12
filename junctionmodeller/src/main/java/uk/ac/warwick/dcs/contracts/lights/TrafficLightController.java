package uk.ac.warwick.dcs.contracts.lights;

import java.util.LinkedList;

public class TrafficLightController {
    public float cycleLength;
    private static final int CHANGEOVER_TIME = 4;
    public LinkedList<TrafficLightGroup> trafficLightOrder;
}
