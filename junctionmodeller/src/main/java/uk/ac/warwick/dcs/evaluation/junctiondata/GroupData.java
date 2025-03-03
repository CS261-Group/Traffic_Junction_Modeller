package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.Iterator;
import java.util.List;

public class GroupData implements Iterable<LaneData>{
    public int groupNum;
    public double greenTime;
    public List<LaneData> lanes;

    public GroupData(int groupNum, double greenTime){
        this.groupNum = groupNum;
        this.greenTime = greenTime;
    }

    @Override
    public Iterator<LaneData> iterator() {
        return lanes.iterator();
    }
}
