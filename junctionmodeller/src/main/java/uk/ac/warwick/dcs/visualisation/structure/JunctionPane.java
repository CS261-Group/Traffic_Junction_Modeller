package uk.ac.warwick.dcs.visualisation.structure;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uk.ac.warwick.dcs.visualisation.Constants;

import java.util.Random;

public class JunctionPane extends Pane {
    static final Random rand = new Random();

    public JunctionPane() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        setMinWidth(Constants.VISUALISATION_WIDTH);
        setMinHeight(Constants.VISUALISATION_HEIGHT);
        setMaxWidth(Constants.VISUALISATION_WIDTH);
        setMaxHeight(Constants.VISUALISATION_HEIGHT);

        getChildren().setAll();
    }
}
