package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class InvalidDirectionException extends Exception {
    public InvalidDirectionException(Direction direction, String messageFor) {
        super("Invalid direction " + direction.toString() + " for " + messageFor);
    }
}
