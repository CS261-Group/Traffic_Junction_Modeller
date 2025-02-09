package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.IncomingRoad;
import java.util.Set;

// a group of incoming roads,
// not necessarily from the same carriageway
public class TrafficLightGroup {
    public Set<IncomingRoad> group;
}
