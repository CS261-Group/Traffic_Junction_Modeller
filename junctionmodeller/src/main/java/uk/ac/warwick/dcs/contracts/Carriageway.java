package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

// traffic lights now belong to Incoming Roads
// there may now be many incoming roads in a carriageway
public class Carriageway {
    private final OutgoingRoad outgoingRoad;
    private final IncomingRoad[] incomingRoad;

    public Carriageway(OutgoingRoad or, IncomingRoad[] irs) {
        outgoingRoad = or;
        incomingRoad = irs;
    }
}
