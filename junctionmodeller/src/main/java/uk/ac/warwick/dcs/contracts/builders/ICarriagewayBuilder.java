package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

public interface ICarriagewayBuilder {
    ICarriagewayBuilder addOutgoingLane();
    ICarriagewayBuilder addIncomingLane(VehicleType type, int queuingSpace, boolean[] directions);
    Carriageway buildCarriageway();
}
