package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

import javax.swing.JLabel;
import java.awt.Font;

public class LaneDepartureSettingsPanel extends CustomPanel implements IReadablePanel<Integer> {
    private static final int DEFAULT_VALUE = 100;
    private final int laneNum;
    private IntegerTextField departureFlowField;

    public LaneDepartureSettingsPanel(Font headingFont, Font labelFont, int num) {
        super(headingFont, labelFont);
        laneNum = num;
        setUp();
    }

    @Override
    protected void setUp() {
        JLabel laneLbl = new JLabel("Lane " + laneNum);
        laneLbl.setFont(labelFont);
        add(laneLbl);


        // I'm assuming the flow will be something less than 7 digits
        departureFlowField = new IntegerTextField(Constants.MAX_INT_TEXT_FIELD_DIGITS, DEFAULT_VALUE);

        add(departureFlowField);
    }

    @Override
    public Integer getValue() {
        return departureFlowField.getIntegerValue();
    }
}
