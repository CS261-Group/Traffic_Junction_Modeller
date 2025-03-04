package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Iterator;
import java.util.List;

public class GroupData implements Iterable<LaneData>{
    public int groupNum;
    public double greenTime; //gets optimised, same as group timing
    public List<LaneData> lanes;

    public GroupData(int groupNum, double greenTime, List<LaneData> laneData){
        this.groupNum = groupNum;
        this.greenTime = greenTime;
        this.lanes = laneData;
    }

    public void modifyTiming(double changeBy){
        this.greenTime += changeBy;
    }

    @Override
    public Iterator<LaneData> iterator() {
        return lanes.iterator();
    }

    public double getMaxFlowRatio(){
        double maxFlowRatio = 0;
        for (LaneData lane : lanes){
            if (lane.getFlowRatio() > maxFlowRatio){
                maxFlowRatio = lane.getFlowRatio();
            }
        }
        return maxFlowRatio;
    }

    public void setGreenTime(double greenTime){
        this.greenTime = greenTime;
    }
}
