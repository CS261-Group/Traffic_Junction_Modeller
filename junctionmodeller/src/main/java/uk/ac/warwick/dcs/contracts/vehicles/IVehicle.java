package uk.ac.warwick.dcs.contracts.vehicles;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public interface IVehicle {
    Direction getOrigin();
    Direction getDestination();
    VehicleType getType();
}
