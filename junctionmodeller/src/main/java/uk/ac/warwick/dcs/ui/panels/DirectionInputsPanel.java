package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.formdata.DirectionInputData;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.util.LanesComboBox;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.List;

/**
 * Panel which holds data regarding whether there is a bus lane and/or
 * a pedestrian crossing on the carriageway being configured.
 */
public class DirectionInputsPanel extends CustomPanel implements IReadablePanel<DirectionInputData> {
    private final List<ILaneChangedSubscriber> subscribers;
    private final int maxLanes;
    private final Direction direction;
    private JCheckBox pedestrianCrossing;
    private JCheckBox busLane;
    private LanesComboBox outLanesComboBox;

    public DirectionInputsPanel(Font headingFont, Font labelFont, int maxLanes, List<ILaneChangedSubscriber> subscribers, Direction direction) {
        super(headingFont, labelFont);
        this.maxLanes = maxLanes;
        this.subscribers = subscribers;
        this.direction = direction;

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
        LanesComboBox lanesComboBox = new LanesComboBox(maxLanes, subscribers, direction);

        add(numLanesLbl);
        add(lanesComboBox);

        JLabel numOutLanesLbl = new JLabel("# of lanes outgoing:");
        numOutLanesLbl.setFont(labelFont);
        outLanesComboBox = new LanesComboBox(maxLanes, List.of(), direction);

        add(numOutLanesLbl);
        add(outLanesComboBox);

        pedestrianCrossing = new JCheckBox("Pedestrian crossing");
        pedestrianCrossing.setFont(labelFont);
        busLane = new JCheckBox("Bus Lane");
        busLane.setFont(labelFont);

        add(pedestrianCrossing);
        add(busLane);
    }

    @Override
    public DirectionInputData getValue() {
        return new DirectionInputData(busLane.isSelected(), pedestrianCrossing.isSelected(), outLanesComboBox.getValue());
    }
}
