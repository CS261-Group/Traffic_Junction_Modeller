package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;

public class LaneDirectionsPanel extends CustomPanel implements ILaneChangedSubscriber {
    public LaneDirectionsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel laneDirectionsHeading = new JLabel("Lane directions");
        laneDirectionsHeading.setFont(headingFont);
        add(laneDirectionsHeading);

        // TODO: implement lanes
    }

    @Override
    public void notify(int oldLanes, int newLanes) {
        // TODO: implement
        System.out.println("Directions: " + oldLanes + " " + newLanes);
    }
}
