package uk.ac.warwick.dcs.contracts.vehicles;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;

public class Car extends Vehicle {
    public Car(Direction o, Direction d) {
        super(o, d, VehicleType.CAR);
    }
}
