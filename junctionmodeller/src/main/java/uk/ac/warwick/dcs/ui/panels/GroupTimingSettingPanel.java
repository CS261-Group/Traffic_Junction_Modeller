package uk.ac.warwick.dcs.ui.panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.warwick.dcs.ui.formdata.GroupTiming;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

/**
 * Panel used to enter a single group timing configuration.
 */
public class GroupTimingSettingPanel extends CustomPanel implements IReadablePanel<GroupTiming> {
    private static final int DEFAULT_TIMING = 30;
    private final int groupNum;
    private IntegerTextField timingField;

    public GroupTimingSettingPanel(Font headingFont, Font labelFont, int groupNum) {
        super(headingFont, labelFont);
        this.groupNum = groupNum;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new GridLayout(0,1));
        JLabel timingLbl = new JLabel("Group " + groupNum);
        timingLbl.setFont(labelFont);
        JPanel timingLblContainer = new JPanel(new GridLayout(0,1));
        timingLblContainer.add(timingLbl);
        add(timingLblContainer);

        timingField = new IntegerTextField(DEFAULT_TIMING);
        timingField.setFont(labelFont);
        add(timingField);
    }

    @Override
    public GroupTiming getValue() {
        return new GroupTiming(groupNum, timingField.getIntegerValue());
    }
}
