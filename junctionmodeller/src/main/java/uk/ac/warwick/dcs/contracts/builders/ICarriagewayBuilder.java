package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidFlowValueException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

/**
 * Interface for builder object used to build Carriageway objects.
 */
public interface ICarriagewayBuilder {
    /**
     *
     * @param bus True if a bus lane incoming along this carriageway, false otherwise.
     *            Optional, default false.
     * @return Same instance of builder object. Useful for chaining.
     */
    ICarriagewayBuilder setBusLane(boolean bus);

    /**
     *
     * @param crossing True if there is a pedestrian crossing across this carriageway, false otherwise.
     *                 Optional setting, default false.
     * @return Same instance of builder object. Useful for chaining.
     */
    ICarriagewayBuilder setPedestrianCrossing(boolean crossing);

    /**
     *
     * @param incomingFlow The total incoming flow from this carriageway into the junction.
     *                     Compulsory setting.
     * @return Same instance of builder object. Useful for chaining.
     * @throws InvalidFlowValueException Thrown if the given <code>incomingFlow</code> parameter
     *                                   is not greater than <code>IncomingRoad.MINIMUM_INCOMING_FLOW</code>.
     */
    ICarriagewayBuilder setIncomingFlow(int incomingFlow) throws InvalidFlowValueException;

    /**
     *
     * @param outgoingFlow The value of the flow leaving the junction (in vph). Compulsory setting,
     *                     must be set in every possible outgoing direction.
     * @param flowDirection The direction the flow is leaving to.
     * @return Same instance of builder object. Useful for chaining.
     * @throws InvalidDirectionException Thrown if <code>flowDirection</code> given matches the
     *                                   incoming direction of the carriageway itself.
     * @throws InvalidFlowValueException Thrown if the given <code>incomingFlow</code> parameter
     *                                   is not greater than <code>IncomingRoad.MINIMUM_OUTGOING_FLOW</code>.
     */
    ICarriagewayBuilder setOutgoingFlow(int outgoingFlow, Direction flowDirection) throws InvalidDirectionException, InvalidFlowValueException;

    /**
     *
     * @return Same instance of builder object. Useful for chaining.
     */
    ICarriagewayBuilder addOutgoingLane();

    /**
     * At least one incoming lane must be specified for each carriageway.
     * @param type The type of the vehicle the lane accommodates.
     * @param queuingSpace The queuing space of the vehicle given TODO: is this param needed?
     * @param directions An array of the available directions. Must be of size 4 and must be false
     *                   for the incoming direction. Specified in order N, E, S, W.
     * @return Same instance of builder object. Useful for chaining.
     * @throws InvalidPermittedDirectionsException Thrown if incoming direction is set to true,
     *                                             or if no outgoing direction is set to true.
     */
    ICarriagewayBuilder addIncomingLane(VehicleType type, int queuingSpace, boolean[] directions) throws InvalidPermittedDirectionsException;

    /**
     *
     * @return Constructed <code>Carriageway</code> object from configured settings.
     * @throws IncompleteBuildSettingsException Thrown if some necessary configuration is not set.
     */
    Carriageway buildCarriageway() throws IncompleteBuildSettingsException;
}
