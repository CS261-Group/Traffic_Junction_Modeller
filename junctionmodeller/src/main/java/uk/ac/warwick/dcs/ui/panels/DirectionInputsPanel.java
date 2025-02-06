package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.LanesComboBox;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.List;

public class DirectionInputsPanel extends CustomPanel {
    private final List<ILaneChangedSubscriber> subscribers;
    private final int maxLanes;

    public DirectionInputsPanel(int maxLanes, List<ILaneChangedSubscriber> subscribers, Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        this.maxLanes = maxLanes;
        this.subscribers = subscribers;

        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new GridLayout(0, 2)); // any number of rows, 2 cols

        // add combobox for choosing number of lanes
        // we also pass in the reference to the lane settings
        // since we want to change it dynamically when the value
        // changes
        JLabel numLanesLbl = new JLabel("# of lanes incoming:");
        numLanesLbl.setFont(labelFont);
        LanesComboBox lanesComboBox = new LanesComboBox(maxLanes, subscribers);

        add(numLanesLbl);
        add(lanesComboBox);

        JCheckBox pedestrianCrossing = new JCheckBox("Pedestrian crossing");
        JCheckBox busLane = new JCheckBox("Bus Lane");
        busLane.setFont(labelFont);

        add(pedestrianCrossing);
        add(busLane);
    }
}
