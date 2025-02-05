package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.vehicles.IVehicle;

import java.util.Deque;
import java.util.LinkedList;

public class IncomingLane extends Lane {
    private final Deque<IVehicle> queue;
    private final VehicleType vehicleType;
    private final boolean[] availableDirections;


    public IncomingLane(VehicleType vt, boolean[] directions) {
        assert directions.length == 4;
        queue = new LinkedList<>();
        vehicleType = vt;
        availableDirections = directions;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public void addVehicle(IVehicle vehicle) {

        boolean success = queue.offer(vehicle);
        assert success; // must have successfully offered vehicle
    }

    public IVehicle removeVehicle() {
        return queue.poll();
    }

    public boolean allowsGoing(Direction direction) {
        assert direction.ordinal() < availableDirections.length;
        return availableDirections[direction.ordinal()];
    }
}
