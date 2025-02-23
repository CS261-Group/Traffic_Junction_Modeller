package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.ui.formdata.GroupTimings;
import uk.ac.warwick.dcs.ui.formdata.LaneGroups;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Arrays;

/**
 * Panel to choose the type of traffic light and the number of groups.
 * Also contains the settings for assigning lanes to traffic light groups.
 */
public class TrafficLightPanel extends CustomPanel implements IReadablePanel<TrafficLightData>, ILaneChangedSubscriber {
    private final static int MAX_NUM_GROUPS = Groups.MAX_GROUP_NUM;
    private final static int MIN_NUM_GROUPS = Groups.MIN_GROUP_NUM; // there must be at least 2 groups in any case
    private final static int DEFAULT_NUM_GROUPS = Groups.MIN_NUM_GROUPS;

    private JComboBox<TrafficLightType> lightTypeCombo;
    private JComboBox<Integer> groupsCombo;
    private int numGroups;
    private final LaneGroupPanel[] laneGroupsPanels;
    private GroupTimingsPanel groupTimingsPanel;

    public TrafficLightPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        numGroups = DEFAULT_NUM_GROUPS;
        laneGroupsPanels = new LaneGroupPanel[4];
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Traffic lights"));

        JLabel lightTypeLbl = new JLabel("Traffic light type:");
        lightTypeLbl.setFont(labelFont);

        JPanel comboBoxContainer = new JPanel(new GridLayout(0, 2));
        lightTypeCombo = new JComboBox<>();
        lightTypeCombo.addItem(TrafficLightType.FIXEDCYCLE);
        lightTypeCombo.addItem(TrafficLightType.ACTUATION);

        JLabel numGroupsLbl = new JLabel("Number of groups:");
        numGroupsLbl.setFont(labelFont);
        groupsCombo = new JComboBox<>();

        // add required selections
        for (int groupNum = MIN_NUM_GROUPS; groupNum <= MAX_NUM_GROUPS; groupNum++) {
            groupsCombo.addItem(groupNum);
        }
        groupsCombo.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int oldNumGroups = numGroups;
                numGroups = (int)e.getItem();
                updateChildrenNumGroups(oldNumGroups);
                updateUI();
            }
        });

        // add lane group settings for each direction
        assert laneGroupsPanels.length == 4; // sanity check: should be 4 panels
        assert numGroups >= MIN_NUM_GROUPS;
        laneGroupsPanels[Direction.NORTH.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.NORTH, 1, numGroups);
        laneGroupsPanels[Direction.EAST.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.EAST, 2, numGroups);
        laneGroupsPanels[Direction.SOUTH.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.SOUTH, 1, numGroups);
        laneGroupsPanels[Direction.WEST.ordinal()] = new LaneGroupPanel(headingFont, labelFont, Direction.WEST, 2, numGroups);

        // add all group timings
        groupTimingsPanel = new GroupTimingsPanel(headingFont, labelFont, numGroups);

        // add all created panels as required
        comboBoxContainer.add(lightTypeLbl);
        comboBoxContainer.add(lightTypeCombo);
        comboBoxContainer.add(numGroupsLbl);
        comboBoxContainer.add(groupsCombo);
        add(comboBoxContainer);
        for (LaneGroupPanel laneGroupPanel : laneGroupsPanels) {
            laneGroupPanel.setAlignmentX(LEFT_ALIGNMENT);
            add(laneGroupPanel);
        }
        add(groupTimingsPanel);

        // this should trigger the change event
        // and also select the default value
        groupsCombo.setSelectedItem(DEFAULT_NUM_GROUPS);
    }

    @Override
    public TrafficLightData getValue() {
        TrafficLightType lightType = (TrafficLightType)lightTypeCombo.getSelectedItem();
        assert groupsCombo.getSelectedItem() != null;
        int numGroups = (int)groupsCombo.getSelectedItem();

        LaneGroups[] directionalLaneGroups = Arrays.stream(laneGroupsPanels)
                .map(LaneGroupPanel::getValue)
                .toArray(LaneGroups[]::new);
        assert directionalLaneGroups.length == 4; // sanity check: one in each direction

        GroupTimings groupTimings = groupTimingsPanel.getValue();
        assert numGroups == groupTimings.groupTimings().size();

        return new TrafficLightData(lightType, numGroups, directionalLaneGroups, groupTimings);
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

    private void updateChildrenNumGroups(int oldNumGroups) {
        // assumes numGroups is set appropriately
        for (LaneGroupPanel laneGroup : laneGroupsPanels) {
            laneGroup.changeLanes(numGroups);
        }
        groupTimingsPanel.changeNumGroups(oldNumGroups, numGroups);
    }
}
