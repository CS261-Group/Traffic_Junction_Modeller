package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.panels.DirectionPanel;
import uk.ac.warwick.dcs.ui.panels.SubmissionPanel;
import uk.ac.warwick.dcs.ui.panels.TrafficLightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainForm extends JFrame {
    private static final int HEADING_FONT_SIZE = 18;
    private static final int MINIMUM_FONT_SIZE = 14;

    private final DirectionPanelFactory panelFactory;
    private final Font headingFont;
    private final Font labelFont;

    private DirectionPanel northboundPanel;
    private DirectionPanel eastboundPanel;
    private DirectionPanel southboundPanel;
    private DirectionPanel westboundPanel;
    private TrafficLightPanel trafficLightPanel;
    private SubmissionPanel submissionPanel;
    private JButton submitButton;

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
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);

        // panels in each direction
        northboundPanel = panelFactory.createDirectionPanel(Direction.NORTH, getWidth());
        eastboundPanel = panelFactory.createDirectionPanel(Direction.EAST, getWidth());
        southboundPanel = panelFactory.createDirectionPanel(Direction.SOUTH, getWidth());
        westboundPanel = panelFactory.createDirectionPanel(Direction.WEST, getWidth());

        // Traffic Lights Section
        trafficLightPanel = new TrafficLightPanel(headingFont, labelFont);

        // Submission Section
        submissionPanel = new SubmissionPanel(headingFont, labelFont);

        mainPanel.add(northboundPanel);
        mainPanel.add(eastboundPanel);
        mainPanel.add(southboundPanel);
        mainPanel.add(westboundPanel);
        mainPanel.add(trafficLightPanel);

        // Submission button and add to submission panel externally
        // we do it externally because we want to access the other panels'
        // getValue() methods
        submitButton = new JButton("Confirm and Analyse");
        submitButton.addActionListener((e) -> {
            DirectionData[] directionData = Arrays.stream(new DirectionPanel[] {
                    northboundPanel, eastboundPanel, southboundPanel, westboundPanel
            }).map(DirectionPanel::getValue).toArray(DirectionData[]::new);

            Arrays.stream(directionData).forEach(x -> {
                assert x.arrivalFlows().size() == x.availableDirections().size() && x.arrivalFlows().size() == x.departureFlows().size();
            });

            trafficLightPanel.getValue();

            System.out.println("Data collected");
        });

        submissionPanel.add(submitButton);

        mainPanel.add(submissionPanel);


        JScrollPane formContainer = new JScrollPane(mainPanel);
        formContainer.getVerticalScrollBar().setUnitIncrement(16);
        add(formContainer, BorderLayout.CENTER);
        setVisible(true);

        // call the onchange event for each lane combo box to generate the
        // lane settings dynamically
        
    }
}
