package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.timings.GroupTiming;

/**
 * Thrown if a group is assigned an invalid group timing.
 */
public class InvalidGroupTimingException extends Exception {
    public InvalidGroupTimingException(int groupNum, int timing) {
        super("Invalid timing " + timing + "sec(s) for group " + groupNum + ". Should be between "
                + GroupTiming.MAX_GROUP_TIMING + " and " + GroupTiming.MAX_GROUP_TIMING + " secs.");
    }
}
