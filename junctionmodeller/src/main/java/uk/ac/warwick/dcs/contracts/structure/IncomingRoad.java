package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;

import java.util.List;

public class IncomingRoad extends Road<IncomingLane> {
    public static final int MINIMUM_INCOMING_FLOW = 20;
    public static final int MINIMUM_OUTGOING_FLOW = 0;
    private final int incomingFlow;
    private final int[] outgoingFlows;

    public IncomingRoad(Direction d, List<IncomingLane> l, int ifl, int[] ofl) {
        super(d, l);
        incomingFlow = ifl;
        outgoingFlows = ofl;
        assert outgoingFlows.length == 4; // sanity check: one for each direction
        assert outgoingFlows[d.ordinal()] == 0; // sanity check: no outgoing flow to the same direction
    }

    /**
     *
     * @param laneNum The number of the incoming lane we are getting. Lanes are
     *                counted 1 (leftmost) to <code>numLanes</code> (rightmost).
     * @return The incoming lane object with the corresponding number.
     */
    public IncomingLane get(int laneNum) {
        return lanes.get(laneNum - 1); // -1 corrected for index
    }

    /**
     *
     * @return The incoming flow from the incoming road into the junction.
     */
    public int getIncomingFlow() { return incomingFlow; }

    /**
     *
     * @param direction The cardinal direction we want the outgoing flow for.
     * @return The outgoing flow from this road towards the <code>direction</code>
     *         parameter.
     * @throws InvalidDirectionException Thrown if the <code>direction</code> parameter
     *                                   is the same as the incoming direction of the
     *                                   incoming road.
     */
    public int getOutgoingFlow(Direction direction) throws InvalidDirectionException {
        if (direction == this.direction) {
            throw new InvalidDirectionException(direction, "outgoing flow direction");
        }
        return outgoingFlows[direction.ordinal()];
    }
}
