package uk.ac.warwick.dcs.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.panels.*;

/**
 * Main entrypoint object for program. Contains all the form data.
 */
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
    private ErrorsPanel errorsPanel;
    private HistoryPanel historyPanel;

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

    private void setUp() {
        setTitle("Traffic Junction Configuration");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(true);
        setFont(labelFont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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

        // Error section
        errorsPanel = new ErrorsPanel(headingFont, labelFont);

        //History / Metrics Section
        historyPanel = new HistoryPanel(headingFont, labelFont);

        // add panels and create window
        mainPanel.add(northboundPanel);
        mainPanel.add(eastboundPanel);
        mainPanel.add(southboundPanel);
        mainPanel.add(westboundPanel);
        mainPanel.add(trafficLightPanel);
        mainPanel.add(submissionPanel);
        mainPanel.add(loadingPanel);
        mainPanel.add(errorsPanel);
        mainPanel.add(historyPanel);

        // construct window by adding singular main panel to
        // scrollable pane
        JScrollPane formContainer = new JScrollPane(mainPanel);
        formContainer.getVerticalScrollBar().setUnitIncrement(16);
        add(formContainer, BorderLayout.CENTER);
    }

    /**
     * We need this method for testing (package private access to
     * changing errors).
     * @param errors The errors to set.
     */
    void setErrors(List<String> errors) {
        errorsPanel.setErrors(errors);
    }

    /**
     * Package private so we can test it.
     * @return The configuration data aggregated from the form.
     */
    ConfigurationData getConfigDataFromForm() {
        // assemble required data
        DirectionData[] directionData = Arrays.stream(new DirectionPanel[]{
                northboundPanel, eastboundPanel, southboundPanel, westboundPanel
        }).map(DirectionPanel::getValue).toArray(DirectionData[]::new);

        TrafficLightData trafficLightData = trafficLightPanel.getValue();

        // read config name from panel
        boolean showVisualisation = submissionPanel.getValue();
        // TODO: should be part of submissionPanel.getValue()
        String configName = submissionPanel.getConfigurationName();

        return new ConfigurationData(configName, directionData, trafficLightData, showVisualisation);
    }

    /**
     * Action method for clicking the 'submit' button.
     * Effectively fetches the data stored across the
     * form and invokes the data service.
     */
    private void onSubmit() {
        // assemble required data
        ConfigurationData configData = getConfigDataFromForm();

        // submit configuration collected from form through data service
        List<String> errors = dataService.submitEnteredConfiguration(configData);
        assert errors != null;

        // update errors in UI
        setErrors(errors);
    }

    private void onLoad(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Configuration File");
        int userSelection = fileChooser.showOpenDialog(this);
        //issue, can't open appdata on GUI
        String path = Paths.get(System.getProperty("user.home"), "Documents/").toAbsolutePath().toString();
        File pathFolder = new File(path);
        fileChooser.setCurrentDirectory(pathFolder);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            fileChooser.setFileHidingEnabled(false);
            File selectedFile = fileChooser.getSelectedFile();
            // TODO: get showVisualisation instead of hard-coding false
            boolean showVisualisation = submissionPanel.getValue();
            List<String> errors = dataService.submitFileConfiguration(selectedFile.getAbsolutePath(), showVisualisation);

            // update errors in UI
            setErrors(errors);
        }
    }

    /**
     * Adds a row to the metrics history table
     * @param modelName Name of the model
     * @param metrics metrics to display
     */
    public void addMetricToHistory(String modelName, JunctionMetrics metrics){
        historyPanel.addRow(modelName, metrics.getAverageDelay(), metrics.getAverageQueue(), metrics.getMaxQueue());
    }
}
