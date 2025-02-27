package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.structure.OutgoingLane;

import java.util.Arrays;

public class LaneFactory {
    private final Direction direction;

    public LaneFactory(Direction direction) {
        this.direction = direction;
    }

    public OutgoingLane createOutgoingLane() {
        return new OutgoingLane(direction);
    }

    public IncomingLane createIncomingLane(VehicleType type, int queuingSpace, boolean[] availableDirections) throws InvalidPermittedDirectionsException {
        // the incoming direction must be false
        if (availableDirections[direction.ordinal()]) {
            throw new InvalidPermittedDirectionsException(direction, availableDirections);
        }

        // there must be at least one permitted outgoing lane
        if (!availableDirections[Direction.NORTH.ordinal()] &&
                !availableDirections[Direction.EAST.ordinal()] &&
                !availableDirections[Direction.SOUTH.ordinal()] &&
                !availableDirections[Direction.WEST.ordinal()]) {
            throw new InvalidPermittedDirectionsException(direction, availableDirections);
        }
        return new IncomingLane(direction, type, availableDirections, queuingSpace);
    }
}
