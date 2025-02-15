package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class IncomingLane extends Lane {
    private final VehicleType vehicleType;
    private final int queueingSpace;
    private final boolean[] availableDirections;

    public IncomingLane(Direction d, VehicleType vt, boolean[] directions, int qs) {
        super(d);
        vehicleType = vt;
        queueingSpace = qs;
        availableDirections = directions;

        assert directions.length == 4; // sanity check: one for each direction
        assert !directions[d.ordinal()]; // sanity check: going backwards can't be valid
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getQueueingSpace() {
        return queueingSpace;
    }

    public boolean allowsGoing(Direction direction) {
        return availableDirections[direction.ordinal()];
    }
}
