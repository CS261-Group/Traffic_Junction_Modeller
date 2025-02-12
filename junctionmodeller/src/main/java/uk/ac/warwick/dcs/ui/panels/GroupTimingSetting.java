package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.formdata.GroupTiming;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

import javax.swing.JLabel;
import java.awt.Font;

public class GroupTimingSetting extends CustomPanel implements IReadablePanel<GroupTiming> {
    private static final int DEFAULT_TIMING = 30;
    private final int groupNum;
    private IntegerTextField timingField;

    public GroupTimingSetting(Font headingFont, Font labelFont, int groupNum) {
        super(headingFont, labelFont);
        this.groupNum = groupNum;
        setUp();
    }

    @Override
    protected void setUp() {
        JLabel timingLbl = new JLabel("Group " + groupNum);
        timingLbl.setFont(labelFont);
        add(timingLbl);

        timingField = new IntegerTextField(DEFAULT_TIMING);
        timingField.setFont(labelFont);
        add(timingField);
    }

    @Override
    public GroupTiming getValue() {
        return new GroupTiming(groupNum, timingField.getIntegerValue());
    }
}
