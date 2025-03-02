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

    double cycleTime;
    GroupData[] groupData;

    public JunctionData(JunctionConfiguration junctionConfiguration){
        // get cycle time
        // create groups
        groupData = new GroupData[junctionConfiguration.getNumberOfGroups()];
        for (int i = 0; i < junctionConfiguration.getNumberOfGroups()){
            groupData[i] = new GroupData();
        }
    }

}
