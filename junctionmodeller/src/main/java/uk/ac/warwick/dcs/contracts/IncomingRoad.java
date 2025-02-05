package uk.ac.warwick.dcs.contracts;

import java.util.List;

public class IncomingRoad extends Road<IncomingLane> {
    public IncomingRoad(List<IncomingLane> l) {
        super(l);
    }
}
