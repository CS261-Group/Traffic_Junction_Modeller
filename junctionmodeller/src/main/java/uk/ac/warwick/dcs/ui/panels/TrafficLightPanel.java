package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TrafficLightPanel extends JPanel {
    public TrafficLightPanel() {
        super(new GridLayout(0, 2));
        setUp();
    }

    private void setUp() {
        setBorder(BorderFactory.createTitledBorder("Traffic lights"));

        add(new JLabel("Traffic light type:"));
        JComboBox<TrafficLightType> lightTypeCombo = new JComboBox<>();
        lightTypeCombo.addItem(TrafficLightType.FIXEDCYCLE);
        lightTypeCombo.addItem(TrafficLightType.ACTUATION);

        add(lightTypeCombo);

        add(new JLabel("Number of groups:"));
        JComboBox<Integer> groupsCombo = new JComboBox<>();
        for (int groupNum = 1; groupNum <= 3; groupNum++) {
            groupsCombo.addItem(groupNum);
        }
        add(groupsCombo);

        // TODO: dynamic groupings
    }
}
