package uk.ac.warwick.dcs.visualisation.structure;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import org.javatuples.Pair;
import uk.ac.warwick.dcs.contracts.enums.Direction;

class IncomingLane extends Lane {
    protected IncomingLane(Direction direction, int laneNum, boolean[] availableDirections, int groupNum) {
        super(direction, laneNum, true);
        assert availableDirections.length == 4;

        // Calculate arrow start and end points
        double startX, startY;
        double endX, endY;
        if (direction.isVertical()) {
            startX = getMinWidth() / 2;
            startY = getMinHeight() * 0.2;
            endX = startX;
            endY = getMinHeight() * 0.5;
        } else {
            assert direction.isHorizontal();
            startX = getMinWidth() * 0.2;
            startY = getMinHeight() / 2;
            endX = getMinWidth() * 0.5;
            endY = startY;
        }

        // Create the arrow and add it to the lane
        Pair<Line, Polygon> arrowComponents = createArrow(startX, startY, endX, endY);

        // add components
        // TODO: group number
        getChildren().setAll(arrowComponents.getValue0(), arrowComponents.getValue1());
    }

    private Pair<Line, Polygon> createArrow(double startX, double startY, double endX, double endY) {
        // Line for arrow shaft
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.LIGHTYELLOW);
        line.setStrokeWidth(2);

        // Arrowhead (triangle)
        double arrowSize = 10;
        Polygon arrowhead = new Polygon();
        arrowhead.getPoints().addAll(
                0.0, 0.0,   // Tip of the arrow
                -arrowSize, arrowSize / 2,  // Left corner
                -arrowSize, -arrowSize / -2  // Right corner
        );
        arrowhead.setFill(Color.LIGHTYELLOW);

        // Rotate arrowhead to match direction
        double angle = Math.toDegrees(Math.atan2(endY - startY, endX - startX));
        arrowhead.setRotate(angle + 90);
        arrowhead.setTranslateX(endX);
        arrowhead.setTranslateY(endY);

        return new Pair<>(line, arrowhead);
    }
}
