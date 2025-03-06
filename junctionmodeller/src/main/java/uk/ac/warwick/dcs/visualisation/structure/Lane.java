package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.visualisation.Constants;

abstract class Lane extends Pane {
    private static final Background OUTGOING_BACKGROUND =
            new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background INCOMING_BACKGROUND =
            new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY));

    protected Lane(Direction direction, int laneNum, boolean incoming) {
        // NOTE: if incoming is true, then incoming lane, otherwise it's an outgoing lane
        // NOTE: laneNum is 0-indexed

        if (incoming) {
            setBackground(INCOMING_BACKGROUND);
        } else {
            setBackground(OUTGOING_BACKGROUND);
        }

        double centre;
        double width, height;
        double x, y;
        int multiplier = incoming ? -1 : 1;
        int laneOffset = incoming ? -1 : 0;
        if (direction.isVertical()) { // NORTH or SOUTH
            width = Constants.LANE_WIDTH;
            height = Constants.VERTICAL_LANE_LENGTH;
            centre = Constants.VISUALISER_WIDTH / 2D;

            if (direction == Direction.NORTH) {
                x = centre - multiplier * (laneNum + 1 + laneOffset) * width; // Incoming on left, outgoing on right
                y = 0D;
            } else {
                assert direction == Direction.SOUTH;
                x = centre + multiplier * (laneNum - laneOffset) * width; // Incoming on right, outgoing on left
                y = (Constants.INTERSECTION_SIDE_LENGTH + Constants.VISUALISER_HEIGHT) / 2D;
            }
        } else { // EAST or WEST
            width = Constants.HORIZONTAL_LANE_LENGTH;
            height = Constants.LANE_WIDTH;
            centre = Constants.VISUALISER_HEIGHT / 2D;

            if (direction == Direction.WEST) {
                x = (Constants.INTERSECTION_SIDE_LENGTH + Constants.VISUALISER_HEIGHT) / 2D;
                y = centre - multiplier * (laneNum + 1 + laneOffset) * height; // Incoming on bottom, outgoing on top
            } else {
                assert direction == Direction.EAST;
                x = 0D;
                y = centre + multiplier * (laneNum - laneOffset) * height; // Incoming on top, outgoing on bottom
            }
        }

        // set width and height accordingly
        setMinWidth(width);
        setMaxWidth(width);
        setMinHeight(height);
        setMaxHeight(height);

        // set x and y accordingly
        setLayoutX(x);
        setLayoutY(y);

        // set border to make it look like lanes
        BorderStroke borderStroke = new BorderStroke(
                Color.WHITE, // Border color
                BorderStrokeStyle.DASHED, // Dashed border style
                CornerRadii.EMPTY, // No rounded corners
                new BorderWidths(1) // Border width (2px)
        );
        setBorder(new Border(borderStroke));
    }
}
