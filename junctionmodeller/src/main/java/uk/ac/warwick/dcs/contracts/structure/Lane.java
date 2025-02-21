package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Atomic lane object that stores only the direction of the
 * road/carriageway it belongs to.
 */
public abstract class Lane {
    private final Direction direction;

    public Lane(Direction direction) {
        this.direction = direction;
    }
}
