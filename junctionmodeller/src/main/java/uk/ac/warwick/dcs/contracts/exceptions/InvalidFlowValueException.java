package uk.ac.warwick.dcs.contracts.exceptions;

public class InvalidFlowValueException extends Exception {
    public InvalidFlowValueException(int flowValue, String type) {
        super("Invalid " + type + " flow value: " + flowValue);
    }
}
