package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.visualisation.Constants;

public class JunctionPane extends Pane {
    public JunctionPane(int[] outgoingLaneCounts, int[] incomingLaneCounts, boolean[][][] incomingDirections) {
        assert outgoingLaneCounts.length == 4;
        assert incomingLaneCounts.length == 4;
        assert incomingDirections.length == 4;

        setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        setMinWidth(Constants.VISUALISER_WIDTH);
        setMinHeight(Constants.VISUALISER_HEIGHT);
        setMaxWidth(Constants.VISUALISER_WIDTH);
        setMaxHeight(Constants.VISUALISER_HEIGHT);

        // draw static junction components
        Intersection intersection = new Intersection();

        // draw for each direction: outgoing lanes, incoming lanes
        for (Direction direction : Direction.values()) {
            int numOutgoing = outgoingLaneCounts[direction.ordinal()];
            for (int i = 0; i < numOutgoing; i++) {
                getChildren().add(new OutgoingLane(direction, i));
            }
            int numIncoming = incomingLaneCounts[direction.ordinal()];
            for (int i = 0; i < numIncoming; i++) {
                getChildren().add(new IncomingLane(
                        direction,
                        i,
                        incomingDirections[direction.ordinal()][i],
                        5 // TODO: group number
                ));
            }
        }

        getChildren().add(intersection);
    }
}
