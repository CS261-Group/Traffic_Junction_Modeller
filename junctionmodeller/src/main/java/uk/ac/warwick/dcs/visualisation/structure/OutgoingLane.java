package uk.ac.warwick.dcs.visualisation.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

class OutgoingLane extends Lane {
    public OutgoingLane(Direction direction, int laneNum) {
        super(direction, laneNum, false);
    }
}
