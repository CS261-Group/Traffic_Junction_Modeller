package uk.ac.warwick.dcs.evaluation.junctiondata;

import java.util.List;

public class ActuatedGroupData extends GroupData{
    public double extensionHeadway;
    public double maxGreenTime;
    public double targetDegOfSaturation; // for use instead of derived degOfSat


    public ActuatedGroupData(int groupNum, double greenTime, double extensionHeadway, double maxGreenTime, List<LaneData> laneData){
        super(groupNum, greenTime, laneData);
        this.extensionHeadway = extensionHeadway;
        this.maxGreenTime = maxGreenTime;
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


}
