package uk.ac.warwick.dcs.visualisation.structure;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.ac.warwick.dcs.visualisation.Constants;

/**
 * Central part of junction.
 */
class Intersection extends Rectangle {
    public Intersection() {
        setWidth(Constants.INTERSECTION_SIDE_LENGTH);
        setHeight(Constants.INTERSECTION_SIDE_LENGTH);
        setFill(Color.GREY);

        setX((double)(Constants.VISUALISER_WIDTH - Constants.INTERSECTION_SIDE_LENGTH) / 2D);
        setY((double)(Constants.VISUALISER_WIDTH - Constants.INTERSECTION_SIDE_LENGTH) / 2D);
    }
}
