package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.structure.OutgoingLane;

public class LaneFactory {
    private final Direction direction;

    public LaneFactory(Direction direction) {
        this.direction = direction;
    }

    public OutgoingLane createOutgoingLane() {
        return new OutgoingLane(direction);
    }

    public IncomingLane createIncomingLane(VehicleType type, int queuingSpace, boolean[] availableDirections) throws InvalidPermittedDirectionsException {
        if (availableDirections[direction.ordinal()]) {
            throw new InvalidPermittedDirectionsException(direction, availableDirections);
        }
        return new IncomingLane(direction, type, availableDirections, queuingSpace);
    }
}
