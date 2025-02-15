package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.structure.*;

import java.util.LinkedList;
import java.util.List;

public class CarriagewayBuilder implements ICarriagewayBuilder {
    private static final int UNASSIGNED_FLOW = -1;

    private final Direction direction;

    private final LaneFactory laneFactory;
    private final List<OutgoingLane> outgoingLanes;
    private final List<IncomingLane> incomingLanes;

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
    }

    public ICarriagewayBuilder setIncomingFlow(int incomingFlow) {
        this.incomingFlow = incomingFlow;
        return this;
    }

    public ICarriagewayBuilder setOutgoingFlow(int outgoingFlow, Direction direction) {
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
        // TODO: integrate with factory
        IncomingLane lane = laneFactory.createIncomingLane(type, queuingSpace, directions);
        incomingLanes.add(lane);
        return this;
    }

    public Carriageway buildCarriageway() {
        OutgoingRoad outgoingRoad = new OutgoingRoad(direction, outgoingLanes);
        IncomingRoad incomingRoad = new IncomingRoad(direction, incomingLanes, incomingFlow, outgoingFlows);
        return new Carriageway(outgoingRoad, incomingRoad);
    }
}
