package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

import javax.swing.JLabel;
import java.awt.Font;

@Deprecated
public class LaneArrivalFlowsSettingsPanel extends CustomPanel implements IReadablePanel<Integer> {
    private static final int DEFAULT_VALUE = 100;
    private final int laneNum;
    private IntegerTextField laneArrival;

    public LaneArrivalFlowsSettingsPanel(Font headingFont, Font labelFont, int num) {
        super(headingFont, labelFont);
        laneNum = num;
        setUp();
    }

    @Override
    protected void setUp() {
        JLabel laneLbl = new JLabel("Lane " + laneNum);
        add(laneLbl);

        // I'm assuming the flow will be something less than 7 digits
        laneArrival = new IntegerTextField(DEFAULT_VALUE);

        add(laneArrival);
    }

    @Override
    public Integer getValue() {
        return laneArrival.getIntegerValue();
    }
}
