package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Thrown if the available directions set for an incoming lane are not
 * in a valid format.
 */
public class InvalidPermittedDirectionsException extends Exception {
    public InvalidPermittedDirectionsException(Direction direction, boolean[] directions) {
        super("Invalid configuration of directions " +
                (directions[0] ? "N" : "") +
                (directions[1] ? "E" : "") +
                (directions[2] ? "S" : "") +
                (directions[3] ? "W" : "") +
                " for incoming lane with direction: " + direction.toString());
        assert directions.length == 4;
    }
}
