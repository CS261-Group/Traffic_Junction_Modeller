package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Iterator;
import java.util.List;

public class GroupData implements Iterable<LaneData>{
    public int groupNum;
    public double greenTime; //gets optimised
    public List<LaneData> lanes;

    public GroupData(int groupNum, double greenTime, List<LaneData> laneData){
        this.groupNum = groupNum;
        this.greenTime = greenTime;
        this.lanes = laneData;
    }

    @Override
    public Iterator<LaneData> iterator() {
        return lanes.iterator();
    }
}
