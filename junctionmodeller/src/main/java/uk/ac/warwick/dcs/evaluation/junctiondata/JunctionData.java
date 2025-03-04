package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class JunctionData implements Iterable<GroupData>{

    double cycleTime;
    ArrayList<GroupData> groupData;

    public JunctionData(JunctionConfiguration junctionConfiguration) {
        cycleTime = junctionConfiguration.getCycleTime();

        groupData = new ArrayList<>(junctionConfiguration.getNumberOfGroups());

        for (Group group : junctionConfiguration.getGroups()) {
            //create group data
            groupData.add(GroupDataBuilder.buildGroupData(junctionConfiguration, group));
        }
    }

    @Override
    public Iterator<GroupData> iterator() {
        return groupData.iterator();
    }

    public int getNumGroups(){
        return groupData.size();
    }

    // index does not have to == group num
    public double getGroupGreenTime(int groupIndex){
        return groupData.get(groupIndex).greenTime;
    }

    public void modifyGroupTiming(int groupIndex, double changeBy){
        groupData.get(groupIndex).modifyTiming(changeBy);
    }

    public double getCycleTime(){
        return cycleTime;
    }
}
