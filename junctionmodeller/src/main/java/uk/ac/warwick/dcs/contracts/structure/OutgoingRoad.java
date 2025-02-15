package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import java.util.List;

public class OutgoingRoad extends Road<OutgoingLane> {
    public OutgoingRoad(Direction d, List<OutgoingLane> l) {
        super(d, l);
    }
}
