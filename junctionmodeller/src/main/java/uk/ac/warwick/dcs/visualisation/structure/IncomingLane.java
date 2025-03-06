package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.evaluation.junctiondata.LaneDataBuilder;
import uk.ac.warwick.dcs.visualisation.Constants;

import java.util.Objects;

/**
 * This class is largely ChatGPT'd because I don't know how to use JavaFX.
 */
class IncomingLane extends Lane {
    private final Pane contentPane;
    // Arrow file names
    private final static String forwardArrowPath = "/forward-arrow.png";
    private final static String leftForwardArrowPath = "/forward-arrow.png";
    private final static String leftRightForwardArrowPath = "/arrows/left-right-forward.png";
    private final static String rightForwardArrowPath = "/forward-arrow.png";
    private final static String rightArrowPath = "/forward-arrow.png";
    private final static String leftArrowPath = "/forward-arrow.png";

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

        ImageView imageView = getArrowImage(direction, availableDirections);
        imageView.setPreserveRatio(true);

        switch (direction) {
            case NORTH:
                imageView.setRotate(90);
                break;
            case WEST:
                imageView.setRotate(180);
                break;
            case SOUTH:
                imageView.setRotate(-90);
                break;
            default: break;
        }

        Pane arrowContainer;
        if (direction == Direction.EAST || direction == Direction.WEST){
            arrowContainer = createHorizontalArrowContainer(imageView);
        } else {
            arrowContainer = createVerticalArrowContainer(imageView);
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

    private HBox createHorizontalArrowContainer(ImageView imageView){
        HBox arrow = new HBox();
        arrow.setAlignment(Pos.CENTER);
        arrow.setMinHeight(Constants.LANE_WIDTH);

        // Ensure it resizes within contentPane
        arrow.setPrefHeight(Constants.LANE_WIDTH);
        arrow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Scale the image properly
        imageView.fitWidthProperty().bind(arrow.widthProperty());
        imageView.fitHeightProperty().bind(arrow.heightProperty());

        arrow.getChildren().add(imageView);
        return arrow;
    }

    private VBox createVerticalArrowContainer(ImageView imageView){
        VBox arrow = new VBox();
        arrow.setAlignment(Pos.CENTER);
        arrow.setMinWidth(Constants.LANE_WIDTH);

        // Ensure it resizes within contentPane
        arrow.setPrefWidth(Constants.LANE_WIDTH);
        arrow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Scale the image properly
        imageView.fitWidthProperty().bind(arrow.widthProperty());
        imageView.fitHeightProperty().bind(arrow.heightProperty());

        arrow.getChildren().add(imageView);
        return arrow;
    }

    @Deprecated
    private VBox createVerticalArrowOLD(Direction direction) {
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

    @Deprecated
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

    private ImageView getArrowImage(Direction direction, boolean[] availableDirections){
        // lane goes left
        if (availableDirections[LaneDataBuilder.leftOf(direction).ordinal()]){
            // lane goes forward
            if (availableDirections[LaneDataBuilder.aheadOf(direction).ordinal()]){
                //lane goes right
                if (availableDirections[LaneDataBuilder.rightOf(direction).ordinal()]){
                    return createArrowImageView(leftRightForwardArrowPath);
                }
                // lane does not go right
                else {
                    return createArrowImageView(leftForwardArrowPath);
                }
            }
            // lane does not go forward
            else {
                return createArrowImageView(leftArrowPath);
            }
        }
        // lane does not go left
        else{
            // lane goes forward
            if (availableDirections[LaneDataBuilder.aheadOf(direction).ordinal()]){
                //lane goes right
                if (availableDirections[LaneDataBuilder.rightOf(direction).ordinal()]){
                    return createArrowImageView(forwardArrowPath);
                }
                // lane does not go right
                else{
                    return createArrowImageView(rightForwardArrowPath);
                }
            }
            //lane does not go forward
            else {
                return createArrowImageView(rightArrowPath);
            }
        }
    }
    public ImageView createArrowImageView(String source){
        Image arrow = new Image(Objects.requireNonNull(getClass().getResourceAsStream(source)));
        return new ImageView(arrow);
    }
}
