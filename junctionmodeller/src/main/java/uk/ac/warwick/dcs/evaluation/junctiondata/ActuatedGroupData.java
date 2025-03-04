package uk.ac.warwick.dcs.evaluation.junctiondata;

import java.util.List;

public class ActuatedGroupData extends GroupData{
    public double extensionHeadway;
    public double maxGreenTime;

    public ActuatedGroupData(int groupNum, double greenTime, double extensionHeadway, double maxGreenTime, List<LaneData> laneData){
        super(groupNum, greenTime, laneData);
        this.extensionHeadway = extensionHeadway;
        this.maxGreenTime = maxGreenTime;
    }
}
