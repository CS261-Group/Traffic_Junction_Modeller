package uk.ac.warwick.dcs.contracts.exceptions;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class InvalidLaneNumberException extends RuntimeException {
    public InvalidLaneNumberException(int laneNum, int numLanes, Direction direction) {
        super("Invalid lane number: " + laneNum +
                ", lane number for " + direction +
                " carriageway must be between 1 and " + numLanes);
    }
}
