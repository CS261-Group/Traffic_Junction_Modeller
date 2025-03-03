package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;

import java.util.Arrays;
import java.util.Iterator;

public class JunctionData implements Iterable<GroupData>{

    double cycleTime;
    GroupData[] groupData;

    public JunctionData(JunctionConfiguration junctionConfiguration){
        cycleTime = junctionConfiguration.getCycleTime();

        // create groups
        groupData = new GroupData[junctionConfiguration.getNumberOfGroups()];
        for (int i = 0; i < junctionConfiguration.getNumberOfGroups()){
            groupData[i] = new GroupData();
        }

        for (Group group : junctionConfiguration.getGroups()){
            groups.getLane
        }
    }

    @Override
    public Iterator<GroupData> iterator() {
        return Arrays.stream(groupData).iterator();
    }

    public double getCycleTime(){
        return cycleTime;
    }
}
