package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.vehicles.IVehicle;

public class OutgoingLane extends Lane {
    @Override
    public void addVehicle(IVehicle vehicle) {
        // this should just do nothing, since a car just
        // leaves the junction
    }
}
