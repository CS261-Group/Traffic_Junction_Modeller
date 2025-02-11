package uk.ac.warwick.dcs.contracts.lights;

import uk.ac.warwick.dcs.contracts.Movement;

import java.util.Set;

// a group of turning directions of incoming lanes,
// not necessarily from the same carriageway
public class TrafficLightGroup {
    public Set<Movement> group;
}