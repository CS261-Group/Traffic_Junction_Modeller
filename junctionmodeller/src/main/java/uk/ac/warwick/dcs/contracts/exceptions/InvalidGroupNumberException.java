package uk.ac.warwick.dcs.contracts.exceptions;

public class InvalidGroupNumberException extends Exception {
    public InvalidGroupNumberException(int groupNum, int numGroups) {
        super("Invalid group number " + groupNum + " out of " + numGroups + " groups. Should be between 1 and " + numGroups + " inclusively.");
    }
}
