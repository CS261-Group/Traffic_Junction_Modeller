package uk.ac.warwick.dcs.contracts;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class CarriagewayBuilder {
    private final LaneFactory laneFactory;


    public CarriagewayBuilder() {
        laneFactory = new LaneFactory();
    }

    public void addIncomingLane(Direction direction, boolean[] directions) {
        laneFactory.createIncomingLane();
    }
}
