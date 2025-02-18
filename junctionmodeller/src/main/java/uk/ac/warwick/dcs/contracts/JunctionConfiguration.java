package uk.ac.warwick.dcs.contracts;

import java.util.Arrays;
import java.util.Iterator;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public class JunctionConfiguration implements Iterable<Carriageway> {
    private final Carriageway[] carriageways;

    // Traffic lights exists at the junction level now
    // because they can operate across carriageways
    // e.g. one traffic light turns green to let North and South Through
    private final TrafficLight trafficLights;
    private final Groups groups;

    public JunctionConfiguration(Carriageway[] cw, TrafficLight tl, Groups g) {
        assert cw.length == 4; // sanity checks: one carriageway object per direction
        carriageways = cw;
        trafficLights = tl;
        groups = g;
    }

    public TrafficLight getTrafficLights() { return trafficLights; }

    public Groups getGroups() { return groups; }

    @Override
    public Iterator<Carriageway> iterator() {
        return Arrays.stream(carriageways).iterator();
    }
}
