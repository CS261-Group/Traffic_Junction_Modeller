package uk.ac.warwick.dcs.contracts.exceptions;

/**
 * Thrown if invalid value for the flow parameter given. Can be thrown
 * for either outgoing or incoming flow parameter.
 */
public class InvalidFlowValueException extends Exception {
    public InvalidFlowValueException(int flowValue, String type) {
        super("Invalid " + type + " flow value: " + flowValue);
    }
}
