package uk.ac.warwick.dcs.contracts.builders;

import java.util.LinkedList;
import java.util.List;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidFlowValueException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidPermittedDirectionsException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.structure.IncomingRoad;
import uk.ac.warwick.dcs.contracts.structure.OutgoingLane;
import uk.ac.warwick.dcs.contracts.structure.OutgoingRoad;

public class CarriagewayBuilder implements ICarriagewayBuilder {
    /**
     * Value used for unassigned flow values, used to check for compulsory
     * flow settings.
     */
    private static final int UNASSIGNED_FLOW = -1;

    /**
     * The incoming direction of the carriageway. (e.g., Direction.NORTH for the
     * northbound carriageway).
     */
    private final Direction direction;

    /**
     * Factory used to generate lanes easily.
     */
    private final LaneFactory laneFactory;

    // settings
    private final List<OutgoingLane> outgoingLanes;
    private final List<IncomingLane> incomingLanes;
    private boolean pedestrianCrossing;
    private int incomingFlow;
    private final int[] outgoingFlows;

    public CarriagewayBuilder(Direction dir) {
        direction = dir;
        laneFactory = new LaneFactory(dir);
        outgoingLanes = new LinkedList<>();
        incomingLanes = new LinkedList<>();

        // set default values
        incomingFlow = UNASSIGNED_FLOW;
        outgoingFlows = new int[]{UNASSIGNED_FLOW, UNASSIGNED_FLOW, UNASSIGNED_FLOW, UNASSIGNED_FLOW};
        outgoingFlows[dir.ordinal()] = 0; // originating direction should have flow = 0
    }

    @Override
    public ICarriagewayBuilder setPedestrianCrossing(boolean crossing) {
        pedestrianCrossing = crossing;
        return this;
    }

    public ICarriagewayBuilder setIncomingFlow(int newIncomingFlow) throws InvalidFlowValueException {
        if (IncomingRoad.MINIMUM_INCOMING_FLOW > newIncomingFlow) {
            throw new InvalidFlowValueException(direction, newIncomingFlow, "incoming");
        }
        incomingFlow = newIncomingFlow;
        return this;
    }

    public ICarriagewayBuilder setOutgoingFlow(int newOutgoingFlow, Direction flowDirection)
            throws InvalidDirectionException, InvalidFlowValueException {
        if (flowDirection == direction) {
            throw new InvalidDirectionException(direction, "outgoing flow of carriageway with the same incoming direction");
        }
        if (IncomingRoad.MINIMUM_OUTGOING_FLOW > newOutgoingFlow) {
            throw new InvalidFlowValueException(direction, newOutgoingFlow, "outgoing");
        }
        outgoingFlows[flowDirection.ordinal()] = newOutgoingFlow;
        return this;
    }

    @Override
    public ICarriagewayBuilder addOutgoingLane() {
        outgoingLanes.add(laneFactory.createOutgoingLane());
        return this;
    }

    @Override
    public ICarriagewayBuilder addIncomingLane(VehicleType type, boolean[] directions) throws InvalidPermittedDirectionsException {
        IncomingLane lane = laneFactory.createIncomingLane(type, directions, incomingLanes.size() + 1);
        incomingLanes.add(lane);
        return this;
    }

    public Carriageway buildCarriageway() throws IncompleteBuildSettingsException {
        if (incomingFlow == UNASSIGNED_FLOW) {
            throw new IncompleteBuildSettingsException("Incoming Flow", "CarriagewayBuilder.setIncomingFlow");
        }

        OutgoingRoad outgoingRoad = new OutgoingRoad(direction, outgoingLanes);
        IncomingRoad incomingRoad = new IncomingRoad(direction, incomingLanes, incomingFlow, outgoingFlows);
        return new Carriageway(direction, outgoingRoad, incomingRoad, pedestrianCrossing);
    }
}
