package uk.ac.warwick.dcs.contracts.enums;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<Direction> valueOf(int value) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.ordinal() == value)
                .findFirst();
    }
}

// https://stackoverflow.com/questions/11047756/getting-enum-associated-with-int-value
