package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.visualisation.Constants;

/**
 * This class is largely ChatGPT'd because I don't know how to use JavaFX.
 */
class IncomingLane extends Lane {
    private final Pane contentPane;

    protected IncomingLane(Direction direction, int laneNum, boolean[] availableDirections, int groupNum) {
        super(direction, laneNum, true);
        assert availableDirections.length == 4;

        // initialise content pane
        if (direction.isVertical()) {
            contentPane = new VBox();
            contentPane.setMinHeight(getMinHeight());

            if (direction == Direction.NORTH) {
                ((VBox)contentPane).setAlignment(Pos.BOTTOM_CENTER);
            } else {
                ((VBox)contentPane).setAlignment(Pos.TOP_CENTER);
            }
        } else {
            assert direction.isHorizontal();
            contentPane = new HBox();
            contentPane.setMinWidth(getMinWidth());

            if (direction == Direction.EAST) {
                ((HBox)contentPane).setAlignment(Pos.CENTER_RIGHT);
            } else {
                ((HBox)contentPane).setAlignment(Pos.CENTER_LEFT);
            }
        }
        // Create an arrow inside an HBox (for horizontal lanes) or VBox (for vertical lanes)
        Pane arrowContainer;
        if (direction.isVertical()) {
            arrowContainer = createVerticalArrow(direction);
        } else {
            arrowContainer = createHorizontalArrow(direction);
        }

        // group number
        Text groupNumText = new Text(Integer.toString(groupNum));
        groupNumText.setTextAlignment(TextAlignment.CENTER);
        groupNumText.setFill(Color.WHITE);
        groupNumText.setWrappingWidth(20); // some padding

        // add arrow and group number
        if (direction == Direction.SOUTH || direction == Direction.WEST) {
            contentPane.getChildren().setAll(arrowContainer, groupNumText);
        } else {
            contentPane.getChildren().setAll(groupNumText, arrowContainer);
        }

        // add content to lane
        getChildren().setAll(contentPane);
    }

    private VBox createVerticalArrow(Direction direction) {
        Line shaft = new Line(0, 0, 0, 20);
        shaft.setStroke(Color.WHITE);
        shaft.setStrokeWidth(3);

        Polygon arrowhead = new Polygon(
                -5.0, 20.0,  // Bottom left
                5.0, 20.0,   // Bottom right
                0.0, 30.0    // Tip of arrow
        );
        arrowhead.setFill(Color.WHITE);
        // Fix direction: NORTH should point downward, SOUTH should point upward
        if (direction == Direction.SOUTH) {
            arrowhead.setRotate(180);
        }

        VBox arrow = new VBox();
        arrow.setAlignment(Pos.CENTER);
        arrow.setMinWidth(Constants.LANE_WIDTH);

        if (direction == Direction.NORTH) {
            // North should have shaft first, then arrowhead (downward)
            arrow.getChildren().addAll(shaft, arrowhead);
        } else {
            // South should be flipped (upward)
            arrow.getChildren().addAll(arrowhead, shaft);
        }

        return arrow;
    }

    private HBox createHorizontalArrow(Direction direction) {
        Line shaft = new Line(0, 0, 20, 0);
        shaft.setStroke(Color.WHITE);
        shaft.setStrokeWidth(3);

        Polygon arrowhead = new Polygon(
                20.0, -5.0,  // Top right
                20.0, 5.0,   // Bottom right
                30.0, 0.0    // Tip of arrow
        );
        arrowhead.setFill(Color.WHITE);
        // Fix direction: EAST points left, WEST points right
        if (direction == Direction.WEST) {
            arrowhead.setRotate(180);
        }

        HBox arrow = new HBox();
        arrow.setAlignment(Pos.CENTER);
        arrow.setMinHeight(Constants.LANE_WIDTH);

        if (direction == Direction.WEST) {
            // West should be flipped
            arrow.getChildren().addAll(arrowhead, shaft);
        } else {
            // East should be normal
            arrow.getChildren().addAll(shaft, arrowhead);
        }

        return arrow;
    }
}
