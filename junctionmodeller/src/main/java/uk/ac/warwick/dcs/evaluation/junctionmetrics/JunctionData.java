package uk.ac.warwick.dcs.evaluation.junctionmetrics;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;

public class JunctionData {

    public JunctionConfiguration junctionConfig;

    // incoming flow is the same for all carriageways
    public double getLaneArrivalFlow(Carriageway carriageway){
        return (double) carriageway.getIncomingFlow() / carriageway.getNumIncomingLanes();
    }

    /**
     * Get the outgoing flow for a direction
     * Get the number of lanes going in that direction
     * the saturation flow for a lane
     *
     * @param carriageway
     * @return
     */
    public double getLaneSaturationFlow(Carriageway carriageway){

        IncomingLane[] left;
        IncomingLane[] right;
        IncomingLane[] ahead;

        carriageway.itter
        dir2;
        dir 3
        carriageway.getNumIncomingLanes()
    }
}
