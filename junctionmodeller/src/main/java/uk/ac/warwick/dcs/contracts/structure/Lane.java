package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public abstract class Lane {
    private final Direction direction;

    public Lane(Direction direction) {
        this.direction = direction;
    }
}
