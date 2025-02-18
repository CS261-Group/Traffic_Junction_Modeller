package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidFlowValueException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

public interface ICarriagewayBuilder {
    ICarriagewayBuilder setBusLane(boolean bus);
    ICarriagewayBuilder setPedestrianCrossing(boolean crossing);
    ICarriagewayBuilder setIncomingFlow(int incomingFlow) throws InvalidFlowValueException;
    ICarriagewayBuilder setOutgoingFlow(int outgoingFlow, Direction flowDirection) throws InvalidDirectionException, InvalidFlowValueException;
    ICarriagewayBuilder addOutgoingLane();
    ICarriagewayBuilder addIncomingLane(VehicleType type, int queuingSpace, boolean[] directions) throws InvalidPermittedDirectionsException;
    Carriageway buildCarriageway() throws IncompleteBuildSettingsException;
}
