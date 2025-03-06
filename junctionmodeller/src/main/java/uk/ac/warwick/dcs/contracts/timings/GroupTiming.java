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
    public static final int MIN_GROUP_TIMING = 5;

    /**
     * The maximum time (in secs) a traffic light can be active (green) for.
     */
    public static final int MAX_GROUP_TIMING = 60;

    // timing field
    private int timing;

    public GroupTiming(int groupNum, int timing) {
        this.groupNum = groupNum;
        this.timing = timing;
    }

    public int getGroupNum(){
        return groupNum;
    }

    public int getTiming(){
        return timing;
    }

    /**
     * Truncates to an int
     * @param updatedTiming Takes a double
     */
    public void updateTiming(double updatedTiming){
        this.updateTiming((int) updatedTiming);
    }

    /**
     * @param updatedTiming Takes an int
     */
    public void updateTiming(int updatedTiming) {
        this.timing = updatedTiming;
    }
}
