package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Font;

public class TrafficLightPanel extends CustomPanel implements IReadablePanel<TrafficLightData> {
    private JComboBox<TrafficLightType> lightTypeCombo;
    private JComboBox<Integer> groupsCombo;

    public TrafficLightPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new GridLayout(0, 2));
        setBorder(BorderFactory.createTitledBorder("Traffic lights"));

        JLabel lightTypeLbl = new JLabel("Traffic light type:");
        lightTypeLbl.setFont(labelFont);
        add(lightTypeLbl);

        lightTypeCombo = new JComboBox<>();
        lightTypeCombo.addItem(TrafficLightType.FIXEDCYCLE);
        lightTypeCombo.addItem(TrafficLightType.ACTUATION);

        add(lightTypeCombo);

        add(new JLabel("Number of groups:"));
        groupsCombo = new JComboBox<>();
        for (int groupNum = 1; groupNum <= 3; groupNum++) {
            groupsCombo.addItem(groupNum);
        }
        add(groupsCombo);

        // TODO: dynamic groupings
    }

    @Override
    public TrafficLightData getValue() {
        TrafficLightType lightType = (TrafficLightType)lightTypeCombo.getSelectedItem();
        assert groupsCombo.getSelectedItem() != null;
        int numGroups = (int)groupsCombo.getSelectedItem();

        return new TrafficLightData(lightType, numGroups);
    }
}
