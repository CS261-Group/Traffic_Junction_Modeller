package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.panels.DirectionPanel;
import uk.ac.warwick.dcs.ui.panels.SubmissionPanel;
import uk.ac.warwick.dcs.ui.panels.TrafficLightPanel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.warwick.dcs.dataproc.serialisation.Loader;
import uk.ac.warwick.dcs.dataproc.serialisation.Saver;
import uk.ac.warwick.dcs.ui.panels.LoadingPanel;

public class MainForm extends JFrame {
    private static final int HEADING_FONT_SIZE = 18;
    private static final int MINIMUM_FONT_SIZE = 14;

    private final DirectionPanelFactory panelFactory;
    private final Font headingFont;
    private final Font labelFont;

    // fields for JPanel derivatives that we can read data
    // from to collect the inputted data
    private DirectionPanel northboundPanel;
    private DirectionPanel eastboundPanel;
    private DirectionPanel southboundPanel;
    private DirectionPanel westboundPanel;
    private TrafficLightPanel trafficLightPanel;
    private SubmissionPanel submissionPanel;
    private LoadingPanel loadingPanel;
    private Saver saver;
    private Loader loader;

    // data service to submit data to next layer
    private final IDataService dataService;

    public MainForm(IDataService dataService) {
        // we need to initialise the factories before we
        // set up the form
        headingFont = new Font("Roboto", Font.BOLD, HEADING_FONT_SIZE);
        labelFont = new Font("Roboto", Font.PLAIN, MINIMUM_FONT_SIZE);
        panelFactory = new DirectionPanelFactory(headingFont, labelFont);
        this.dataService = dataService;

        // set up the UI elements
        setUp();
    }

    /**
     * Set up UI elements for the form.
     */
    private void setUp() {
        setTitle("Traffic Junction Configuration");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(true);
        setFont(labelFont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);

        // Traffic Lights Section
        trafficLightPanel = new TrafficLightPanel(headingFont, labelFont);

        // panels in each direction
        List<ILaneChangedSubscriber> externalSubscribers = new LinkedList<>();
        externalSubscribers.add(trafficLightPanel);
        northboundPanel = panelFactory.createDirectionPanel(Direction.NORTH, externalSubscribers);
        eastboundPanel = panelFactory.createDirectionPanel(Direction.EAST, externalSubscribers);
        southboundPanel = panelFactory.createDirectionPanel(Direction.SOUTH, externalSubscribers);
        westboundPanel = panelFactory.createDirectionPanel(Direction.WEST, externalSubscribers);

        // Submission Section
        submissionPanel = new SubmissionPanel(headingFont, labelFont);
        submissionPanel.setSubmissionAction((e) -> onSubmit());

        loadingPanel = new LoadingPanel(headingFont,labelFont);
        loadingPanel.setSubmissionAction((e) -> onLoad());
        // add panels and create window
        mainPanel.add(northboundPanel);
        mainPanel.add(eastboundPanel);
        mainPanel.add(southboundPanel);
        mainPanel.add(westboundPanel);
        mainPanel.add(trafficLightPanel);
        mainPanel.add(submissionPanel);
        mainPanel.add(loadingPanel);
        // construct window by adding singular main panel to
        // scrollable pane
        JScrollPane formContainer = new JScrollPane(mainPanel);
        formContainer.getVerticalScrollBar().setUnitIncrement(16);
        add(formContainer, BorderLayout.CENTER);
        setVisible(true);
        
    }

    private void onSubmit() {
        DirectionData[] directionData = Arrays.stream(new DirectionPanel[]{
                northboundPanel, eastboundPanel, southboundPanel, westboundPanel
        }).map(DirectionPanel::getValue).toArray(DirectionData[]::new);

//        Arrays.stream(directionData).forEach(x -> {
//            assert x.arrivalFlows().size() == x.availableDirections().size() && x.arrivalFlows().size() == x.departureFlows().size();
//        });
        TrafficLightData trafficLightData = trafficLightPanel.getValue();

        ConfigurationData configData = new ConfigurationData(directionData, trafficLightData);

        List<String> errors = dataService.submitEnteredConfiguration(configData);

        // TODO: handle errors with a scrollable modal
        saver = new Saver();
        saver.saveFile(directionData, trafficLightData);
        System.out.println("Data collected");
    }

    private void onLoad(){
        String path = loadingPanel.getValue();
        loader = new Loader();
        loader.LoadFile(path);
    }
    
    
}
