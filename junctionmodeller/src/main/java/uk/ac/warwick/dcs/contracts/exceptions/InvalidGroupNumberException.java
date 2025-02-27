package uk.ac.warwick.dcs.contracts.exceptions;

/**
 * Thrown if the set group number does not correspond to any group
 * number determine from the configuration.
 */
public class InvalidGroupNumberException extends Exception {
    public InvalidGroupNumberException(int groupNum, int numGroups) {
        super("Invalid group number " + groupNum + " out of " + numGroups + " groups. Should be between 1 and " + numGroups + " inclusively.");
    }
}
