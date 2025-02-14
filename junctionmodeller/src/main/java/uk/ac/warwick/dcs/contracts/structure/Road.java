package uk.ac.warwick.dcs.contracts.structure;

import java.util.Iterator;
import java.util.List;

abstract class Road<TLane extends Lane> implements Iterable<TLane> {
    private final List<TLane> lanes;

    public Road(List<TLane> l) {
        lanes = l;
    }

    public Iterator<TLane> iterator() {
        return lanes.iterator();
    }
}
