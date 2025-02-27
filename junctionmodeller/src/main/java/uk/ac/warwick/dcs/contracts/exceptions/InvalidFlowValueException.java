package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Thrown if invalid value for the flow parameter given. Can be thrown
 * for either outgoing or incoming flow parameter.
 */
public class InvalidFlowValueException extends Exception {
    public InvalidFlowValueException(Direction direction, int flowValue, String type) {
        super("Invalid " + type + " flow value: " + flowValue + " in direction " + direction.toString());
    }
}
