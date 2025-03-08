package uk.ac.warwick.dcs.ui;

/**
 * Taken from Ch44 highway traffic analysis and design
 */
public abstract class Defaults {
    /**
     * For each of the directions, N, E, S, W
     */
    public static final int[] INCOMING_FLOWS = {550, 650, 450, 500};

    /**
     * Outgoing flows for each direction in (left, through and right) will be the same
     */
    public static final int[] OUTGOING_FLOWS = {1500, 1500, 1500, 1500};


    public static final int GREEN_TIME = 30;

}
