package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.FlowData;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

import javax.swing.*;
import java.awt.Font;
import java.util.Arrays;

public class FlowsPanel extends CustomPanel implements IReadablePanel<FlowData> {
    private final Direction direction;
    private final IntegerTextField[] textFields;

    public FlowsPanel(Font headingFont, Font labelFont, Direction direction) {
        super(headingFont, labelFont);
        this.direction = direction;
        this.textFields = new IntegerTextField[4];
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int directionIdx = 0; directionIdx < 4; directionIdx++) {

            JLabel flowLbl;
            IntegerTextField flowField;
            if (directionIdx == direction.ordinal()) { // incoming flow
                flowLbl = new JLabel("Incoming " + Constants.DIRECTIONS[directionIdx]);
                flowField = new IntegerTextField(150);
            } else { // outgoing flow
                flowLbl = new JLabel("Outgoing " + Constants.DIRECTIONS[directionIdx]);
                flowField = new IntegerTextField(50);
            }
            flowLbl.setFont(labelFont);
            flowField.setFont(labelFont);
            textFields[directionIdx] = flowField;
            add(flowLbl);
            add(textFields[directionIdx]);
        }
    }

    @Override
    public FlowData getValue() {
        int[] flows = Arrays.stream(textFields).mapToInt(IntegerTextField::getIntegerValue).toArray();
        return new FlowData(flows);
    }
}
