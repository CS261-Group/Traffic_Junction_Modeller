package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.vehicles.IVehicle;

import java.util.Deque;
import java.util.LinkedList;

public class IncomingLane extends Lane {
    private final Deque<IVehicle> queue;
    private final VehicleType vehicleType;
    // could also store this as a List of OutgoingLanes
    private final boolean[] availableDirections;
    private final int incomingHourlyFlowRate;
    // despite being called outflow, must be linked to the incoming lane
    private final int maximumHourlyOutFlow;

    public IncomingLane(VehicleType vt, boolean[] directions, int inFlow, int outFlow) {
        assert directions.length == 4;
        queue = new LinkedList<>();
        vehicleType = vt;
        availableDirections = directions;
        incomingHourlyFlowRate = inFlow;
        maximumHourlyOutFlow = outFlow;
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
