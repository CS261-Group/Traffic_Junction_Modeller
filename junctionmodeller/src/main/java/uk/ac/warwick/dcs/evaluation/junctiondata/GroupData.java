package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Iterator;
import java.util.List;

public class GroupData implements Iterable<LaneData>{
    public int groupNum;
    public double greenTime; //gets optimised, same as group timing
    public List<LaneData> lanes;

    public double extensionHeadway;
    public double maxGreenTime;
    public double targetDegOfSaturation; // for use instead of derived degOfSat

    public double maxFlowRatio;

    // fixed case
    public GroupData(int groupNum, double greenTime, List<LaneData> laneData){
        this.groupNum = groupNum;
        this.greenTime = greenTime;
        this.lanes = laneData;
        this.maxFlowRatio = getMaxFlowRatio();
    }

    // actuated case
    public GroupData(int groupNum, double greenTime, double extensionHeadway, double maxGreenTime, List<LaneData> laneData){
        this(groupNum, greenTime, laneData);
        this.extensionHeadway = extensionHeadway;
        this.maxGreenTime = maxGreenTime;
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

    // Equation 2 in Akcelik 2000
    // assumes green time has been initiliased previously
    public void setGreenTimeAndDegOfSat(double cycleTime){
        targetDegOfSaturation = 0.78 * Math.pow(maxFlowRatio, 0.5) *
                Math.pow(extensionHeadway, -0.1) *
                Math.pow((cycleTime-this.greenTime), 0.18);

        assert targetDegOfSaturation != 0; // should never be 0;
        this.greenTime = (cycleTime * maxFlowRatio) / targetDegOfSaturation;
    }

    public double getTargetDegOfSaturation(){
        return targetDegOfSaturation;
    }

    public void setGreenTime(double greenTime){
        this.greenTime = greenTime;
    }
}
