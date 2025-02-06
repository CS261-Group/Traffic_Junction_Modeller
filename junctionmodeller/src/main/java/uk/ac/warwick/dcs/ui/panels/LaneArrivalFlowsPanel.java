package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;

import javax.swing.*;
import java.awt.*;

public class LaneArrivalFlowsPanel extends CustomPanel implements ILaneChangedSubscriber {
    public LaneArrivalFlowsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel laneArrivalHeading = new JLabel("Lane arrival flows");
        laneArrivalHeading.setFont(headingFont);
        add(laneArrivalHeading);

        // add lanes
    }

    @Override
    public void notify(int oldLanes, int newLanes) {
        // TODO: implement
        System.out.println("Arrival flows: " + oldLanes + " " + newLanes);
    }
}
