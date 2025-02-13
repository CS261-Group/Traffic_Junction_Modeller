package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class IncomingLane extends Lane {
    private final VehicleType vehicleType;
    private final int queueingSpace;
    private final Movement[] availableDirections;
    private final Group group;

    public IncomingLane(VehicleType vt, Movement[] directions, int qs, Group g) {
        assert directions.length <= 3;
        vehicleType = vt;
        queueingSpace = qs;
        availableDirections = directions;
        group = g;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean allowsGoing(Direction direction) {
        for (int i = 0; i < availableDirections.length; i++){
            if (availableDirections[i].turningDirection == direction){
                return true;
            }
        }
        return false;
    }
}
