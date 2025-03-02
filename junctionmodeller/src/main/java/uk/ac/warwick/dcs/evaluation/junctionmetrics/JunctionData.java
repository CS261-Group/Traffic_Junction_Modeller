package uk.ac.warwick.dcs.evaluation.junctionmetrics;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.contracts.timings.Groups;

import java.util.Iterator;

public class JunctionData {

    public JunctionConfiguration junctionConfig;

    /**
     * Splits incoming flow equally among all lanes
     * @param carriageway carriageway containing lanes
     * @return the arrival flow rate
     */
    public static double getLaneArrivalFlow(Carriageway carriageway){
        return (double) carriageway.getIncomingFlow() / carriageway.getNumIncomingLanes();
    }

    /**
     * The left direction is clockwise (the successor enum value)
     * @param dir The input direction
     * @return The direction of the left turn from the input direction
     */
    public static Direction leftOf(Direction dir) {
        return Direction.values()[(dir.ordinal() + 1) % 4];
    }

    /**
     * The right direction is anti-clockwise (the previous enum value)
     * @param dir The input direction
     * @return The direction of the right turn from the input direction
     */
    public static Direction rightOf(Direction dir){
        return Direction.values()[(dir.ordinal() - 1) % 4];
    }

    /**
     * The ahead direction is opposite (the second successor enum value)
     * @param dir The input direction
     * @return The direction of ahead from the input direction
     */
    public static Direction aheadOf(Direction dir){
        return Direction.values()[(dir.ordinal() + 2) % 4];
    }

    /**
     * Determines the saturation flow for a lane.
     * Note: Assumes the user inputs the outgoing flow for JUST ONE LANE in each direction
     *
     * @param carriageway A carriageway
     * @param lane A lane belonging to the carriageway
     * @return the saturation flow rate ascribed to the lane
     */
    public static double getLaneSaturationFlow(Carriageway carriageway, IncomingLane lane){

        Direction carriagewayDir = carriageway.getDirection();
        //null initialising instead of throwing InvalidPerm
        Direction prefferedFlowDirection;

        if (lane.allowsGoing(rightOf(carriagewayDir))){
            prefferedFlowDirection = rightOf(carriagewayDir);
        }
        else if (lane.allowsGoing(leftOf(carriagewayDir))){
            prefferedFlowDirection = leftOf(carriagewayDir);
        }
        else if (lane.allowsGoing(aheadOf(carriagewayDir))) {
            prefferedFlowDirection = aheadOf(carriagewayDir);
        } else { //instead of throwing an InvalidPermittedDirectionsError, since that shouldn't be done here
            prefferedFlowDirection = null;
        }

        try{
            return carriageway.getOutgoingFlow(prefferedFlowDirection);
        } catch (InvalidDirectionException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getLaneGreenTime(IncomingLane lane, Groups groups){

        return groups.getLaneTiming(lane);
    }

}
