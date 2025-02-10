package uk.ac.warwick.dcs.contracts.vehicles;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class Bus extends Vehicle {
    public Bus(Direction o, Direction d) {
        super(o, d, VehicleType.BUS);
    }
}
//just data storage