package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import javax.swing.*;
import java.awt.*;

public class DirectionPanelFactory {
    private static final int MAX_LANES = 5;

    private final Font headingFont;
    private final Font labelFont;


    public DirectionPanelFactory(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    public JPanel createDirectionPanel(Direction direction) {
        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new BoxLayout(directionPanel, BoxLayout.Y_AXIS));
        directionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        directionPanel.setBorder(BorderFactory.createTitledBorder(direction.toString()));

        JPanel directionInputs = new JPanel();
        directionInputs.setLayout(new GridLayout(0, 2)); // any number of rows, 2 cols

        // add combobox for choosing number of lanes
        // we also pass in the reference to the lane settings
        // since we want to change it dynamically when the value
        // changes
        directionInputs.add(new JLabel("# of lanes incoming:"));
        directionInputs.add(new LanesComboBox(MAX_LANES));

        JCheckBox pedestrianCrossing = new JCheckBox("Pedestrian crossing");
        JCheckBox busLane = new JCheckBox("Bus Lane");
        directionInputs.add(pedestrianCrossing);
        directionInputs.add(busLane);

        directionPanel.add(directionInputs);

        // Lane Arrival Flows
        JLabel laneArrivalHeading = new JLabel("Lane arrival flows");
        laneArrivalHeading.setFont(headingFont);
        directionPanel.add(laneArrivalHeading);

        // Lane Directions
        JLabel laneDirectionsHeading = new JLabel("Lane directions");
        laneDirectionsHeading.setFont(headingFont);
        directionPanel.add(laneDirectionsHeading);

        JCheckBox lane1Eastbound = new JCheckBox("Eastbound →");
        JCheckBox lane1Northbound = new JCheckBox("Northbound ↑");
        JCheckBox lane1Westbound = new JCheckBox("Westbound ←");

        JCheckBox lane2Eastbound = new JCheckBox("Eastbound →");
        JCheckBox lane2Northbound = new JCheckBox("Northbound ↑");
        JCheckBox lane2Westbound = new JCheckBox("Westbound ←");

        directionPanel.add(lane1Eastbound);
        directionPanel.add(lane2Eastbound);
        directionPanel.add(lane1Northbound);
        directionPanel.add(lane2Northbound);
        directionPanel.add(lane1Westbound);
        directionPanel.add(lane2Westbound);

        // Lane Departure Flows
        directionPanel.add(new JLabel("Lane departure flows"));
        directionPanel.add(new JLabel(""));

        directionPanel.add(new JLabel("Lane #1"));
        JTextField lane1Departure = new JTextField("100");
        directionPanel.add(lane1Departure);

        directionPanel.add(new JLabel("Lane #2"));
        JTextField lane2Departure = new JTextField("100");
        directionPanel.add(lane2Departure);

        return directionPanel;
    }
}
