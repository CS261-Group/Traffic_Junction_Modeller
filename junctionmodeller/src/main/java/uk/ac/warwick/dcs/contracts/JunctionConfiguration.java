package uk.ac.warwick.dcs.contracts;

import java.util.Arrays;
import java.util.Iterator;

public class JunctionConfiguration implements Iterable<Carriageway> {
    // kept this in a variable because technically a junction can have
    // not necessarily 4 carriageways going into it, but we are assuming
    // it
    private static final int NUM_CARRIAGEWAYS = 4;

    private final Carriageway[] carriageways;

    public JunctionConfiguration(Carriageway[] cw) {
        assert cw.length == NUM_CARRIAGEWAYS;
        carriageways = cw;
    }

    @Override
    public Iterator<Carriageway> iterator() {
        return Arrays.stream(carriageways).iterator();
    }
}
