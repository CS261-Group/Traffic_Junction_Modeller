package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.structure.*;

import java.util.LinkedList;
import java.util.List;

public class CarriagewayBuilder implements ICarriagewayBuilder {
    private static final int UNASSIGNED_FLOW = -1;

    private final Direction direction;

    private final LaneFactory laneFactory;
    private final List<OutgoingLane> outgoingLanes;
    private final List<IncomingLane> incomingLanes;
    private boolean pedestrianCrossing;
    private boolean busLane;

    //
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
    public ICarriagewayBuilder setBusLane(boolean bus) {
        busLane = bus;
        return this;
    }

    @Override
    public ICarriagewayBuilder setPedestrianCrossing(boolean crossing) {
        pedestrianCrossing = crossing;
        return this;
    }

    public ICarriagewayBuilder setIncomingFlow(int newIncomingFlow) {
        incomingFlow = newIncomingFlow;
        return this;
    }

    public ICarriagewayBuilder setOutgoingFlow(int outgoingFlow, Direction flowDirection) throws InvalidDirectionException {
        if (flowDirection == direction) {
            throw new InvalidDirectionException(flowDirection, "outgoing flow of carriageway with the same incoming direction");
        }
        outgoingFlows[direction.ordinal()] = outgoingFlow;
        return this;
    }

    @Override
    public ICarriagewayBuilder addOutgoingLane() {
        outgoingLanes.add(laneFactory.createOutgoingLane());
        return this;
    }

    @Override
    public ICarriagewayBuilder addIncomingLane(VehicleType type, int queuingSpace, boolean[] directions) {
        IncomingLane lane = laneFactory.createIncomingLane(type, queuingSpace, directions);
        incomingLanes.add(lane);
        return this;
    }

    public Carriageway buildCarriageway() {
        // TODO: error checks on unassigned values
        OutgoingRoad outgoingRoad = new OutgoingRoad(direction, outgoingLanes);
        IncomingRoad incomingRoad = new IncomingRoad(direction, incomingLanes, incomingFlow, outgoingFlows);
        return new Carriageway(outgoingRoad, incomingRoad, busLane, pedestrianCrossing);
    }
}
