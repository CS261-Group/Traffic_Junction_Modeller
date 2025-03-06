package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class IncomingLane extends Lane {
    private final VehicleType vehicleType;
    private final boolean[] availableDirections;

    public IncomingLane(Direction d, VehicleType vt, boolean[] directions) {
        super(d);
        vehicleType = vt;
        availableDirections = directions;

        assert directions.length == 4; // sanity check: one for each direction
        assert !directions[d.ordinal()]; // sanity check: going backwards can't be valid
    }

    /**
     *
     * @return The <code>VehicleType</code> of the vehicles this lane
     *         permits. If <code>CAR</code> type is permitted, <code>BUS</code>
     *         (and <code>CYCLE</code>) vehicle types are also permitted.
     */
    @Deprecated
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     *
     * @param direction The direction we check if the lane permits going.
     * @return True if the lane permits exiting from the given <code>direction</code>,
     *         false otherwise. If the specified <code>direction</code> matches
     *         the incoming direction of the carriageway this lane belongs to,
     *         false is returned.
     */
    public boolean allowsGoing(Direction direction) {
        return availableDirections[direction.ordinal()];
    }
}
