package uk.ac.warwick.dcs.contracts.exceptions;

public class IncompleteJunctionException extends Exception {
    public IncompleteJunctionException(String fieldName) {
        super("The field " + fieldName + " is empty (it might have a null value).");
    }
}
