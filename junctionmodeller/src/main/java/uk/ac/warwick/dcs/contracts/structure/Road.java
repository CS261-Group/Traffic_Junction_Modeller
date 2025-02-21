package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import java.util.Iterator;
import java.util.List;

abstract class Road<TLane extends Lane> implements Iterable<TLane> {
    protected final Direction direction;
    protected final List<TLane> lanes;

    public Road(Direction d, List<TLane> l) {
        direction = d;
        lanes = l;
    }

    public Iterator<TLane> iterator() {
        return lanes.iterator();
    }

    /**
     *
     * @return The number of lanes of this road.
     */
    public int numLanes() {
        return lanes.size();
    }
}
