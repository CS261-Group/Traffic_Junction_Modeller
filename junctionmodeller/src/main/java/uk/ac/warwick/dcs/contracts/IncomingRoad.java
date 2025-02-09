package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import java.util.List;

public class IncomingRoad extends Road<IncomingLane> {
    public IncomingRoad(List<IncomingLane> l) {
        super(l);
    }
}
