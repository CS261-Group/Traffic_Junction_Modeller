package uk.ac.warwick.dcs.contracts.timings;

public class GroupTiming {
    /**
     * The minimum time (in secs) a traffic light can be active (green) for.
     */
    public static final int MIN_GROUP_TIMING = 15;

    /**
     * The maximum time (in secs) a traffic light can be active (green) for.
     */
    public static final int MAX_GROUP_TIMING = 180;

    private final int groupNum;
    private final int timing;

    public GroupTiming(int groupNum, int timing) {
        this.groupNum = groupNum;
        this.timing = timing;
    }
}
