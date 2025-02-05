package uk.ac.warwick.dcs.contracts.vehicles;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class Cycle extends Vehicle {
    public Cycle(Direction o, Direction d) {
        super(o, d, VehicleType.CYCLE);
    }
}
