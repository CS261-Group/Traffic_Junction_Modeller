package uk.ac.warwick.dcs.contracts.structure;

// traffic lights now belong to Incoming Roads
// there may now be many incoming roads in a carriageway
public class Carriageway {
    private final OutgoingRoad outgoingRoad;
    private final IncomingRoad incomingRoad;
    private final boolean busLane;
    private final boolean pedestrianCrossing;

    public Carriageway(OutgoingRoad or, IncomingRoad ir, boolean bus, boolean pedestrian) {
        outgoingRoad = or;
        incomingRoad = ir;
        busLane = bus;
        pedestrianCrossing = pedestrian;
    }

    public OutgoingRoad getOutgoing() {
        return outgoingRoad;
    }

    public IncomingRoad getIncoming() {
        return incomingRoad;
    }
}
