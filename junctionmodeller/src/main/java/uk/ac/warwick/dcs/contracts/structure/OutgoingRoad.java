package uk.ac.warwick.dcs.contracts.structure;

import java.util.List;

public class OutgoingRoad extends Road<OutgoingLane> {
    public OutgoingRoad(List<OutgoingLane> l) {
        super(l);
    }
}
