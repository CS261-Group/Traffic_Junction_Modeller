package uk.ac.warwick.dcs.contracts.vehicles;


import uk.ac.warwick.dcs.contracts.enums.Direction;

public class VehicleFactory {
    public Car createCar(Direction origin, Direction destination) {
        return new Car(origin, destination);
    }

    public Bus createBus(Direction origin, Direction destination) {
        return new Bus(origin, destination);
    }

    public Cycle createCycle(Direction origin, Direction destination) {
        return new Cycle(origin, destination);
    }
}
