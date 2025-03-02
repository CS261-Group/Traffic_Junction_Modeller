package uk.ac.warwick.dcs.contracts.timings;

// TODO: currently only covers the fixed timing case
/**
 * Object used to logically separate the <code>Group</code> object
 * from the timings since part of the requirements allows for the
 * possibility to optimise, in which case they don't need to be set.
 */
public class GroupTiming {

    // group identifier
    private final int groupNum;

    /**
     * The minimum time (in secs) a traffic light can be active (green) for.
     */
    public static final int MIN_GROUP_TIMING = 15;

    /**
     * The maximum time (in secs) a traffic light can be active (green) for.
     */
    public static final int MAX_GROUP_TIMING = 180;

    // timing field
    private double timing;

    public GroupTiming(int groupNum, int timing) {
        this.groupNum = groupNum;
        this.timing = timing;
    }

    public int getGroupNum(){
        return groupNum;
    }

    public double getTiming(){
        return timing;
    }

    public void updateTiming(double updatedTiming){
        this.timing = updatedTiming;
    }
}
