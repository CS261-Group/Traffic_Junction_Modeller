package uk.ac.warwick.dcs.evaluation.junctionmetrics;

public class GroupData {
    public int groupNum;
    public double greenTime;
    public LaneData[] lanes;

    public GroupData(int groupNum, double greenTime){
        this.groupNum = groupNum;
        this.greenTime = greenTime;



    }
}
