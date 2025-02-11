package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;

public class TrafficLightPanel extends CustomPanel implements IReadablePanel<TrafficLightData>, ILaneChangedSubscriber {
    private final static int MAX_NUM_GROUPS = 6;
    private final static int MIN_NUM_GROUPS = 2; // there must be at least 2 groups in any case
    private final static int DEFAULT_GROUP_NUM = 2;

    private JComboBox<TrafficLightType> lightTypeCombo;
    private JComboBox<Integer> groupsCombo;
    private int numGroups;
    private LaneGroupPanel[] laneGroupsPanels;

    public TrafficLightPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        numGroups = MAX_NUM_GROUPS;

        laneGroupsPanels = new LaneGroupPanel[4];
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Traffic lights"));

        JLabel lightTypeLbl = new JLabel("Traffic light type:");
        lightTypeLbl.setFont(labelFont);
        add(lightTypeLbl);

        JPanel comboBoxContainer = new JPanel(new GridLayout(0, 2));
        lightTypeCombo = new JComboBox<>();
        lightTypeCombo.addItem(TrafficLightType.FIXEDCYCLE);
        lightTypeCombo.addItem(TrafficLightType.ACTUATION);

        JLabel numGroupsLbl = new JLabel("Number of groups:");
        numGroupsLbl.setFont(labelFont);
        add(numGroupsLbl);
        groupsCombo = new JComboBox<>();
        for (int groupNum = MIN_NUM_GROUPS; groupNum <= numGroups; groupNum++) {
            groupsCombo.addItem(groupNum);
        }
        groupsCombo.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                for (LaneGroupPanel laneGroup : laneGroupsPanels) {
                    laneGroup.changeLanes((int)e.getItem());
                }
            }
        });
        // this should trigger the change event
        groupsCombo.setSelectedItem(DEFAULT_GROUP_NUM);

        // add lane group settings for each direction
        assert laneGroupsPanels.length == 4; // sanity check: should be 4 panels
        assert numGroups >= MIN_NUM_GROUPS;
        laneGroupsPanels[Direction.NORTH.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.NORTH, 1, numGroups);
        laneGroupsPanels[Direction.EAST.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.EAST, 2, numGroups);
        laneGroupsPanels[Direction.SOUTH.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.SOUTH, 1, numGroups);
        laneGroupsPanels[Direction.WEST.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.WEST, 2, numGroups);

        // add all created panels as required
        comboBoxContainer.add(lightTypeCombo);
        comboBoxContainer.add(groupsCombo);
        add(comboBoxContainer);
        for (LaneGroupPanel laneGroupPanel : laneGroupsPanels) {
            add(laneGroupPanel);
        }
    }

    @Override
    public TrafficLightData getValue() {
        TrafficLightType lightType = (TrafficLightType)lightTypeCombo.getSelectedItem();
        assert groupsCombo.getSelectedItem() != null;
        int numGroups = (int)groupsCombo.getSelectedItem();

        // TODO: update

        return new TrafficLightData(lightType, numGroups);
    }

    @Override
    public void notify(int oldLanes, int newLanes, Direction direction) {
        // we effectively mediate the
        if (direction == Direction.NORTH) {
            laneGroupsPanels[Direction.NORTH.ordinal()].notify(oldLanes, newLanes, direction);
        } else if (direction == Direction.EAST) {
            laneGroupsPanels[Direction.EAST.ordinal()].notify(oldLanes, newLanes, direction);
        } else if (direction == Direction.SOUTH) {
            laneGroupsPanels[Direction.SOUTH.ordinal()].notify(oldLanes, newLanes, direction);
        } else if (direction == Direction.WEST) {
            laneGroupsPanels[Direction.WEST.ordinal()].notify(oldLanes, newLanes, direction);
        } else {
            assert false; // sanity check: this should never happen
        }
    }
}
