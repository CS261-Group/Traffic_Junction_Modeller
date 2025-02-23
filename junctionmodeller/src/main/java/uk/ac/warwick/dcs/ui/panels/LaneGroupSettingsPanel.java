package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.formdata.LaneGroup;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Panel for assigning a single incoming lane to a single group.
 */
public class LaneGroupSettingsPanel extends CustomPanel implements IReadablePanel<LaneGroup> {
    private final int laneNum;
    private final int defaultValue;
    private int numGroups;
    private JComboBox<Integer> groupCombo;

    public LaneGroupSettingsPanel(Font headingFont, Font labelFont, int laneNum, int numGroups, int defaultValue) {
        super(headingFont, labelFont);
        this.laneNum = laneNum;
        this.defaultValue = defaultValue;
        this.numGroups = numGroups;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new GridLayout(0, 2));
        JLabel laneLbl = new JLabel("Lane " + laneNum);
        laneLbl.setFont(labelFont);

        groupCombo = new JComboBox<>();
        resetGroupCombo();

        add(laneLbl);
        add(groupCombo);
    }

    public void changeLanes(int newNumGroups) {
        numGroups = newNumGroups;
        resetGroupCombo();
    }

    private void resetGroupCombo() {
        assert groupCombo != null;

        groupCombo.removeAllItems();
        for (int group = 1; group <= numGroups; group++) {
            groupCombo.addItem(group);
        }
        groupCombo.setSelectedItem(defaultValue);
    }

    @Override
    public LaneGroup getValue() {
        assert groupCombo.getSelectedItem() != null;
        return new LaneGroup(laneNum, (int)groupCombo.getSelectedItem());
    }
}
