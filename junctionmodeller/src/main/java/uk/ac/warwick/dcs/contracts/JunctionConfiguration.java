package uk.ac.warwick.dcs.contracts;

import java.util.Arrays;
import java.util.Iterator;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;

/**
 * Object storing all aspects of configurations and settings taken from the user
 * or input file about a junction that is to be modeled/analysed. This object
 * contains references to its constituent:
 * - carriageways
 * - traffic lights
 * - traffic groups
 */
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

    /**
     * Getter for contained <code>TrafficLights</code> object.
     * @return The contained <code>TrafficLights</code> object.
     */
    public TrafficLight getTrafficLights() { return trafficLights; }

    /**
     * Getter for <code>Groups</code> object.
     * @return The contained <code>Groups</code> object.
     */
    public Groups getGroups() { return groups; }

    @Override
    public Iterator<Carriageway> iterator() {
        return Arrays.stream(carriageways).iterator();
    }
}
