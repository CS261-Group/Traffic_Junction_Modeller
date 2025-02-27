package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Exception thrown an invalid direction is passed in to a builder method.
 * Usually to do with setting an outgoing setting on an incoming direction
 * or vice versa.
 */
public class InvalidDirectionException extends Exception {
    public InvalidDirectionException(Direction direction, String messageFor) {
        super("Invalid direction " + direction.toString() + " for " + messageFor);
    }
}
