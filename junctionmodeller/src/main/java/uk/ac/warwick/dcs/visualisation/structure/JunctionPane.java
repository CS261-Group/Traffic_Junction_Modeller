package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.model.messaging.OptimisationUpdate;
import uk.ac.warwick.dcs.visualisation.Constants;

import java.util.List;

public class JunctionPane extends Pane {
    Intersection intersection;

    public JunctionPane(int[] outgoingLaneCounts, int[] incomingLaneCounts, boolean[][][] incomingDirections, int[][] incomingLaneGroupNums) {
        assert outgoingLaneCounts.length == 4;
        assert incomingLaneCounts.length == 4;
        assert incomingDirections.length == 4;
        assert incomingLaneGroupNums.length == 4;

        setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        setMinWidth(Constants.VISUALISER_WIDTH);
        setMinHeight(Constants.VISUALISER_HEIGHT);
        setMaxWidth(Constants.VISUALISER_WIDTH);
        setMaxHeight(Constants.VISUALISER_HEIGHT);

        // draw static junction components
        intersection = new Intersection();

        // draw for each direction: outgoing lanes, incoming lanes
        for (Direction direction : Direction.values()) {
            int numOutgoing = outgoingLaneCounts[direction.ordinal()];
            for (int i = 0; i < numOutgoing; i++) {
                getChildren().add(new OutgoingLane(direction, i));
            }

            // iterates forwards in laneNum but backwards in directions and groupNums
            // so that lanes appear in correct order
            int numIncoming = incomingLaneCounts[direction.ordinal()];
            for (int i = 0; i < numIncoming ; i++) {
                getChildren().add(new IncomingLane(
                        direction,
                        i,
                        incomingDirections[direction.ordinal()][numIncoming - 1 - i],
                        incomingLaneGroupNums[direction.ordinal()][numIncoming - 1 - i]
                ));
            }
        }

        getChildren().add(intersection);
    }


    public void displayGroupTimings(OptimisationUpdate modelUpdate){
        List<String> groupTimings = modelUpdate.getOptimisation().getTimings();

        StringBuilder builder = new StringBuilder();

        for (String timing : groupTimings){
            builder.append(timing).append('\n');
        }

        intersection.getTextField().setText(builder.toString());
    }
}
