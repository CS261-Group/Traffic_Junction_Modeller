package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import java.util.List;

// IncomingRoad class is a now a subset of the whole incoming road,
// to account for a road being able to have multiple traffic lights
// e.g. seperate left turn and right turn lights
public class IncomingRoad extends Road<IncomingLane> {
    private final TrafficLight trafficLight;

    public IncomingRoad(List<IncomingLane> l, TrafficLight tl) {
        super(l);
        trafficLight = tl;
    }
}
