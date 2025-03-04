package uk.ac.warwick.dcs.contracts.exceptions;

/**
 * Exception for when there is no value to get.
 * Used when fields have been left blank for optimising
 */
public class NoValueExistsException extends Exception {
    public NoValueExistsException(String object) {
        super("Value for " + object + " Has not been initilaised");
    }
}
