package uk.ac.warwick.dcs.contracts.vehicles;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

abstract class Vehicle implements IVehicle {
    private final Direction origin;
    private final Direction destination;
    private final VehicleType vehicleType;

    public Vehicle(Direction o, Direction d, VehicleType vt) {
        origin = o;
        destination = d;
        vehicleType = vt;
    }

    @Override
    public Direction getOrigin() {
        return origin;
    }

    @Override
    public Direction getDestination() {
        return destination;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }
}
