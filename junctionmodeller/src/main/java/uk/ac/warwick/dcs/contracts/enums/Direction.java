package uk.ac.warwick.dcs.contracts.enums;

/**
 * Enum used to specify cardinal directions of:
 * - permitted lanes
 * - carriageways
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public boolean isVertical() {
        return this == NORTH || this == SOUTH;
    }

    public boolean isHorizontal() {
        return this == EAST || this == WEST;
    }
}
