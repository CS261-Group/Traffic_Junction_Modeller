package uk.ac.warwick.dcs.visualisation.structure;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import uk.ac.warwick.dcs.visualisation.Constants;

import java.util.List;

/**
 * Central part of junction.
 */
class Intersection extends StackPane {
    private final Rectangle rectangle;
    private final Text text;

    public Intersection() {
        setMinWidth(Constants.INTERSECTION_SIDE_LENGTH);
        setMaxWidth(Constants.INTERSECTION_SIDE_LENGTH);
        setMinHeight(Constants.INTERSECTION_SIDE_LENGTH);
        setMaxHeight(Constants.INTERSECTION_SIDE_LENGTH);

        rectangle = new Rectangle();
        rectangle.setWidth(Constants.INTERSECTION_SIDE_LENGTH);
        rectangle.setHeight(Constants.INTERSECTION_SIDE_LENGTH);
        rectangle.setFill(Color.GREY);

        rectangle.setX((double)(Constants.VISUALISER_WIDTH - Constants.INTERSECTION_SIDE_LENGTH) / 2D);
        rectangle.setY((double)(Constants.VISUALISER_WIDTH - Constants.INTERSECTION_SIDE_LENGTH) / 2D);

        text = new Text();

        getChildren().setAll(rectangle,text);
    }

    public Text getTextField(){
        return text;
    }
}
