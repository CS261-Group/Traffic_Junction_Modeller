package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;

/**
 * A collection of data structures storing all the data
 * for the configurations of this single direction.
 * - Incoming lanes
 * - Outgoing lanes
 * - Existence of bus lane
 * - Existence of pedestrian crossing
 * - Cardinal direction of this carriageway from the junction.
 */
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

    /**
     *
     * @return The cardinal direction of this carriageway from the junction.
     */
    public Direction getDirection() { return direction; }

    /**
     * Might throw an unchecked exception if <code>laneNum</code> is not in bounds.
     * @param laneNum The number of the incoming lane we are getting. Lanes are
     *                counted 1 (leftmost) to <code>numLanes</code> (rightmost).
     * @return The incoming lane object with the corresponding number.
     * TODO: custom error for invalid lane number
     */
    public IncomingLane getIncomingLane(int laneNum) { return incomingRoad.get(laneNum); }

    /**
     *
     * @return The incoming flow from the incoming road into the junction.
     */
    public int getIncomingFlow() { return incomingRoad.getIncomingFlow(); }

    /**
     *
     * @param direction The cardinal direction we want the outgoing flow for.
     * @return The outgoing flow from this carriageway towards the <code>direction</code>
     *         parameter.
     * @throws InvalidDirectionException Thrown if the <code>direction</code> parameter
     *                                   is the same as the incoming direction of the
     *                                   carriageway.
     */
    public int getOutgoingFlow(Direction direction) throws InvalidDirectionException { return incomingRoad.getOutgoingFlow(direction); }

    /**
     *
     * @return The number of incoming lanes of this carriageway.
     */
    public int getNumIncomingLanes() { return incomingRoad.numLanes(); }

    /**
     *
     * @return The number of outgoing lanes of this carriageway.
     */
    public int getNumOutgoingLanes() { return outgoingRoad.numLanes(); }

    /**
     *
     * @param laneNum The number of the incoming lane we are getting. Lanes are
     *                counted 1 (leftmost) to <code>numLanes</code> (rightmost).
     * @param direction The cardinal direction of direction.
     * @return True if the selected lane permits exiting from the <code>direction</code>
     *         given, false otherwise. If the <code>direction</code> specified
     *         matches the incoming direction, we return false.
     * TODO: custom error for invalid lane number
     */
    public boolean getLaneAllowsDirection(int laneNum, Direction direction) {
        return incomingRoad.get(laneNum).allowsGoing(direction);
    }

    /**
     *
     * @return True if there is a bus lane configured on this carriageway,
     *         false otherwise.
     */
    public boolean isBusLane() {
        return busLane;
    }

    /**
     *
     * @return True if there is a pedestrian crossing configured across
     *         this carriageway, false otherwise.
     */
    public boolean isPedestrianCrossing() {
        return pedestrianCrossing;
    }
}
