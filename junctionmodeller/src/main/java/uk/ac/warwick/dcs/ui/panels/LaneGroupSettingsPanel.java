package uk.ac.warwick.dcs.ui.panels;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;

public class LaneGroupSettingsPanel extends CustomPanel {
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
}
