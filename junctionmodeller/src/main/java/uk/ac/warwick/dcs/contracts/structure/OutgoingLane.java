package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Atomic class for outgoing lanes to be logically separated
 * from incoming lanes.
 */
public class OutgoingLane extends Lane {
    public OutgoingLane(Direction direction) {
        super(direction);
    }
}
