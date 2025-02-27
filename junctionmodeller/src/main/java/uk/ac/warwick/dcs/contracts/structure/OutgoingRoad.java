package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import java.util.List;

/**
 * Object used to logically distinguish between outgoing and incoming
 * roads.
 */
public class OutgoingRoad extends Road<OutgoingLane> {
    public OutgoingRoad(Direction d, List<OutgoingLane> l) {
        super(d, l);
    }
}
