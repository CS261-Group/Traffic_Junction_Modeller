package uk.ac.warwick.dcs.contracts;

// traffic lights now belong to Incoming Roads
// there may now be many incoming roads in a carriageway
public class Carriageway {
    private final OutgoingRoad outgoingRoad;
    private final IncomingRoad incomingRoad;

    public Carriageway(OutgoingRoad or, IncomingRoad ir) {
        outgoingRoad = or;
        incomingRoad = ir;
    }
}
