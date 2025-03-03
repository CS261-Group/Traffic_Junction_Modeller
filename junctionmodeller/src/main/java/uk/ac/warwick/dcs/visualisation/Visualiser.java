package uk.ac.warwick.dcs.visualisation;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Main wrapping window for every
 */
public class Visualiser extends Application {
    /**
     * Singleton instance to allow <code>VisualisationFactory</code> to access
     * the single instance.
     */
    private static Visualiser instance;

    /**
     * Maximum number of panes that can be running simultaneously
     */
    private static final int MAX_CONCURRENT_VISUALISATIONS = 8;

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Junction Visualiser";

    private StackPane root;
    private StackPane toggleSlot; // Holds the active pane
    private final List<Pane> modelVisualisations;

    public Visualiser() {
        super();
        modelVisualisations = new ArrayList<>(MAX_CONCURRENT_VISUALISATIONS);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(WINDOW_TITLE);
        stage.setWidth(Constants.VISUALISER_WIDTH);
        stage.setHeight(Constants.VISUALISER_HEIGHT);
        stage.setOnCloseRequest(Event::consume); // don't close on close event
        stage.setResizable(false);

        // remove border (with minimise, maximise, and close buttons)
        stage.initStyle(StageStyle.UNDECORATED);

        root = new StackPane();
        Scene rootScene = new Scene(root, Constants.VISUALISER_WIDTH, Constants.VISUALISER_HEIGHT);

        // initialise and set up the toggling visualisation pane
        toggleSlot = new StackPane();
        modelVisualisations.add(createPane(Color.RED));
        modelVisualisations.add(createPane(Color.GREEN));
        modelVisualisations.add(createPane(Color.BLUE));

        // toggle button
        Button btn = new Button("Next Pane");
        btn.setOnAction(event -> nextPane());

        // TODO: metrics
        // TODO: key button which, on hover, overwrites the pane
        root.getChildren().addAll(toggleSlot, btn);


        stage.setScene(rootScene);
        stage.show();
    }

    // Creates a pane with a given background color
    private Pane createPane(Color color) {
        Pane pane = new Pane();
        pane.setMinSize(200, 150);
        pane.setStyle("-fx-background-color: " + toRgbString(color) + ";");
        return pane;
    }

    // Converts JavaFX Color to CSS RGB String
    private String toRgbString(Color color) {
        return "rgb(" + (int) (color.getRed() * 255) + "," +
                (int) (color.getGreen() * 255) + "," +
                (int) (color.getBlue() * 255) + ")";
    }

    int curIdx=0;
    private void nextPane() {
        toggleSlot.getChildren().setAll(modelVisualisations.get(curIdx));
        curIdx+=1;
        curIdx%=modelVisualisations.size();
    }

    /**
     * Launch the 'game', i.e., visualise the app.
     */
    public void run(String[] args) {
        launch(args);
    }

    /**
     * Package private method for <code>VisualisationFactory</code>.
     * @param modelVisualisation Model visualisation to add to displayed models.
     */
    public void addModelVisualisation(ModelVisualisation modelVisualisation) {
        modelVisualisations.add(modelVisualisation);
    }

    /**
     *
     * @return Singleton instance of <code>Visualiser</code>.
     */
    public static Visualiser getInstance() {
        if (instance == null) {
            instance = new Visualiser();
        }
        return instance;
    }
}
