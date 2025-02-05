package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private static final int HEADING_FONT_SIZE = 18;
    private static final int MINIMUM_FONT_SIZE = 14;

    private final DirectionPanelFactory panelFactory;
    private final Font headingFont;
    private final Font labelFont;

    public MainForm() {
        // we need to initialise the factories before we
        // set up the form
        headingFont = new Font("Roboto", Font.BOLD, HEADING_FONT_SIZE);
        labelFont = new Font("Roboto", Font.PLAIN, MINIMUM_FONT_SIZE);
        panelFactory = new DirectionPanelFactory(headingFont, labelFont);

        // set up the UI elements
        setUp();
    }

    /**
     * Set up UI elements for the form.
     */
    private void setUp() {
        setTitle("Traffic Junction Configuration");
        setSize(668, 768);
        setFont(labelFont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Northbound Panel
        JPanel northboundPanel = panelFactory.createDirectionPanel(Direction.NORTH);
        JPanel eastboundPanel = panelFactory.createDirectionPanel(Direction.EAST);
        JPanel southboundPanel = panelFactory.createDirectionPanel(Direction.SOUTH);
        JPanel westboundPanel = panelFactory.createDirectionPanel(Direction.WEST);

        // Traffic Lights Section
        JPanel trafficLightPanel = new JPanel(new GridLayout(0, 2));
        trafficLightPanel.setBorder(BorderFactory.createTitledBorder("Traffic lights"));

        trafficLightPanel.add(new JLabel("Traffic light type:"));
        JComboBox<String> lightTypeCombo = new JComboBox<>(new String[]{"Fixed-time", "Adaptive"});
        trafficLightPanel.add(lightTypeCombo);

        trafficLightPanel.add(new JLabel("Number of groups:"));
        JComboBox<String> groupsCombo = new JComboBox<>(new String[]{"1", "2", "3"});
        trafficLightPanel.add(groupsCombo);

        trafficLightPanel.add(new JLabel("Lane #1 Group:"));
        JTextField lane1Group = new JTextField("1");
        trafficLightPanel.add(lane1Group);

        trafficLightPanel.add(new JLabel("Lane #2 Group:"));
        JTextField lane2Group = new JTextField("2");
        trafficLightPanel.add(lane2Group);

        trafficLightPanel.add(new JLabel("Group 1 Timing:"));
        JTextField group1Timing = new JTextField("30");
        trafficLightPanel.add(group1Timing);

        trafficLightPanel.add(new JLabel("Group 2 Timing:"));
        JTextField group2Timing = new JTextField("40");
        trafficLightPanel.add(group2Timing);

        JCheckBox showValues = new JCheckBox("Show values on diagram");
        JButton submitButton = new JButton("Analyse and Confirm");

        mainPanel.add(northboundPanel);
        mainPanel.add(eastboundPanel);
        mainPanel.add(southboundPanel);
        mainPanel.add(westboundPanel);
        mainPanel.add(trafficLightPanel);
        mainPanel.add(showValues);
        mainPanel.add(submitButton);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setVisible(true);
    }
}
