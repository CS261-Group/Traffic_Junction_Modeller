package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class Movement {
    public final Direction turningDirection;
    public int incomingFlow;
    public double outgoingClearanceRate;

    public Movement(Direction d){
        turningDirection = d;
    }
}
