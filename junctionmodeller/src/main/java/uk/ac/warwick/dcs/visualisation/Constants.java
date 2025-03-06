package uk.ac.warwick.dcs.visualisation;

import javafx.scene.text.Font;

public class Constants {
    /**
     * Width of whole visualisation window.
     */
    public static final int VISUALISER_WIDTH = 768;

    /**
     * Height of whole visualisation window.
     */
    public static final int VISUALISER_HEIGHT = 768;

    /**
     * Width of side popup windows.
     */
    public static final int SIDE_POPUP_WIDTH = 200;

    /**
     * Height of side popup windows.
     */
    public static final int SIDE_POPUP_HEIGHT = 300;

    /**
     * Width of central popups. It's larger than regular popups.
     */
    public static final int CENTRE_POPUP_WIDTH = 500;

    /**
     * Height of central popups. It's larger than regular popups.
     */
    public static final int CENTRE_POPUP_HEIGHT = 500;

    /**
     * Side length of square intersection at centre of junction.
     */
    public static final int INTERSECTION_SIDE_LENGTH = 360;

    /**
     * Max lanes permitted from any direction (incoming + outgoing)
     */
    public static final int MAX_LANES = 10;

    /**
     * Derived lane width from intersection side length and max number of lanes.
     */
    public static final int LANE_WIDTH = INTERSECTION_SIDE_LENGTH / MAX_LANES;

    /**
     * Length of lanes which are vertical (North/Southbound).
     */
    public static final int VERTICAL_LANE_LENGTH =
            (Constants.VISUALISER_HEIGHT - INTERSECTION_SIDE_LENGTH) / 2;

    /**
     * Length of lanes which are horizontal (East/Westbound).
     */
    public static final int HORIZONTAL_LANE_LENGTH =
            (Constants.VISUALISER_WIDTH - INTERSECTION_SIDE_LENGTH) / 2;
}
