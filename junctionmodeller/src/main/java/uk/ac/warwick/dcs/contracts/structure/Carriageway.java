package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;

// traffic lights now belong to Incoming Roads
// there may now be many incoming roads in a carriageway
public class Carriageway {
    private final OutgoingRoad outgoingRoad;
    private final IncomingRoad incomingRoad;
    private final boolean busLane;
    private final boolean pedestrianCrossing;
    private final Direction direction;

    public Carriageway(Direction d, OutgoingRoad or, IncomingRoad ir, boolean bus, boolean pedestrian) {
        outgoingRoad = or;
        incomingRoad = ir;
        busLane = bus;
        pedestrianCrossing = pedestrian;
        direction = d;
    }

    public Direction getDirection() { return direction; }
    public IncomingLane getIncomingLane(int laneNum) { return incomingRoad.get(laneNum); }
    public int getIncomingFlow() { return incomingRoad.getIncomingFlow(); }
    public int getOutgoingFlow(Direction direction) throws InvalidDirectionException { return incomingRoad.getOutgoingFlow(direction); }
    public int getNumIncomingLanes() { return incomingRoad.numLanes(); }
    public int getNumOutgoingLanes() { return outgoingRoad.numLanes(); }
    public boolean getLaneAllowsDirection(int laneNum, Direction direction) {
        return incomingRoad.get(laneNum).allowsGoing(direction);
    }
}
