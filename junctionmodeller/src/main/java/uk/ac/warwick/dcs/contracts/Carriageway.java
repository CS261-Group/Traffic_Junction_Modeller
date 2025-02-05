package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public class Carriageway {
    private final OutgoingRoad outgoingRoad;
    private final IncomingRoad incomingRoad;
    private final TrafficLight trafficLight;

    public Carriageway(OutgoingRoad ir, IncomingRoad or, TrafficLight tl) {
        outgoingRoad = ir;
        incomingRoad = or;
        trafficLight = tl;
    }
}
