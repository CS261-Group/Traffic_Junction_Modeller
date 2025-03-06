package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Iterator;
import java.util.List;

public class GroupData implements Iterable<LaneData>{
    protected int groupNum;
    protected double greenTime; //gets optimised, same as group timing
    protected final List<LaneData> lanes;
    protected final double maxFlowRatio;

    public GroupData(int groupNum, double greenTime, List<LaneData> laneData){
        this.groupNum = groupNum;
        this.greenTime = greenTime;
        this.lanes = laneData;
        this.maxFlowRatio = maxFlowRatio();
    }

    public void modifyTiming(double changeBy){
        this.greenTime += changeBy;
    }

    @Override
    public Iterator<LaneData> iterator() {
        return lanes.iterator();
    }

    private double maxFlowRatio(){
        double maxFlowRatio = 0;
        for (LaneData lane : lanes){
            if (lane.getFlowRatio() > maxFlowRatio){
                maxFlowRatio = lane.getFlowRatio();
            }
        }
        return maxFlowRatio;
    }

    public double getMaxFlowRatio(){
        return maxFlowRatio;
    }

    public double getGreenTime(){
        return greenTime;
    }

    public void setGreenTime(double greenTime){
        this.greenTime = greenTime;
    }
}
