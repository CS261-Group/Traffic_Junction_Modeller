package uk.ac.warwick.dcs.contracts;

import java.util.List;

public class OutgoingRoad extends Road<OutgoingLane> {
    public OutgoingRoad(List<OutgoingLane> l) {
        super(l);
    }
}
