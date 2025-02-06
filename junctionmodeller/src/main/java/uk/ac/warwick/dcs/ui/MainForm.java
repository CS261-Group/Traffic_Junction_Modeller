package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.panels.DirectionPanel;
import uk.ac.warwick.dcs.ui.panels.SubmissionPanel;
import uk.ac.warwick.dcs.ui.panels.TrafficLightPanel;

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
        setResizable(false);
        setFont(labelFont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Northbound Panel
        DirectionPanel northboundPanel = panelFactory.createDirectionPanel(Direction.NORTH);
        northboundPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        DirectionPanel eastboundPanel = panelFactory.createDirectionPanel(Direction.EAST);
        eastboundPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        DirectionPanel southboundPanel = panelFactory.createDirectionPanel(Direction.SOUTH);
        southboundPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        DirectionPanel westboundPanel = panelFactory.createDirectionPanel(Direction.WEST);
        westboundPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Traffic Lights Section
        TrafficLightPanel trafficLightPanel = new TrafficLightPanel();

        // Submission Section
        SubmissionPanel submissionPanel = new SubmissionPanel(headingFont, labelFont);

        JPanel northWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        northWrapper.add(northboundPanel);

        JPanel eastWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        eastWrapper.add(eastboundPanel);

        JPanel southWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        southWrapper.add(southboundPanel);

        JPanel westWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        westWrapper.add(westboundPanel);

        mainPanel.add(northWrapper);
        mainPanel.add(eastWrapper);
        mainPanel.add(southWrapper);
        mainPanel.add(westWrapper);
        mainPanel.add(trafficLightPanel);
        mainPanel.add(submissionPanel);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setVisible(true);

        // call the onchange event for each lane combo box to generate the
        // lane settings dynamically
        
    }
}
