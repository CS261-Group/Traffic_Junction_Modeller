package uk.ac.warwick.dcs.ui.panels;

import java.awt.Font;
import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;

import javax.swing.*;

public class LaneDeparturesPanel extends CustomPanel implements ILaneChangedSubscriber {
    public LaneDeparturesPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel depFlowHeading = new JLabel("Lane departure flows");
        depFlowHeading.setFont(headingFont);
        add(depFlowHeading);

        // TODO: lanes
    }

    @Override
    public void notify(int oldLanes, int newLanes) {
        // TODO: implement
        System.out.println("Lane arrival: " + oldLanes + " " + newLanes);
    }
}
