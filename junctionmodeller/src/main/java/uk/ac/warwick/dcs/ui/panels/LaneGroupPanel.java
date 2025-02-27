package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.LaneGroup;
import uk.ac.warwick.dcs.ui.formdata.LaneGroups;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel used to store data about which lanes are in which traffic light groups.
 */
public class LaneGroupPanel extends CustomPanel implements ILaneChangedSubscriber, IReadablePanel<LaneGroups> {
    private final Direction direction;
    private final int defaultGroup;
    private int numGroups;
    private final List<LaneGroupSettingsPanel> lanes;

    public LaneGroupPanel(Font headingFont, Font labelFont, Direction direction, int defaultGroup, int numGroups) {
        super(headingFont, labelFont);
        this.direction = direction;
        this.defaultGroup = defaultGroup;
        this.numGroups = numGroups;
        this.lanes = new ArrayList<>(Constants.MAX_LANES);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // add required label
        JLabel directionLbl = new JLabel(Constants.DIRECTIONS[direction.ordinal()] + "bound lanes:");
        directionLbl.setFont(labelFont);
        add(directionLbl);
    }

    public void changeLanes(int newNumGroups) {
        numGroups = newNumGroups;
        for (LaneGroupSettingsPanel settingsPanel : lanes) {
            settingsPanel.changeLanes(newNumGroups);
        }
    }

    @Override
    public void notify(int oldLanes, int newLanes, Direction direction) {
        if (oldLanes < newLanes) {
            while (lanes.size() != newLanes) {
                LaneGroupSettingsPanel setting = new LaneGroupSettingsPanel(headingFont, labelFont, lanes.size() + 1, numGroups, defaultGroup);
                setting.setAlignmentX(LEFT_ALIGNMENT);
                add(setting);
                lanes.add(setting);
            }
        } else {
            while (lanes.size() != newLanes) {
                LaneGroupSettingsPanel removedSetting = lanes.remove(lanes.size() - 1);
                remove(removedSetting);
            }
        }
        updateUI();
    }

    @Override
    public LaneGroups getValue() {
        List<LaneGroup> laneGroups = lanes.stream().map(LaneGroupSettingsPanel::getValue).toList();
        return new LaneGroups(laneGroups, direction);
    }
}
