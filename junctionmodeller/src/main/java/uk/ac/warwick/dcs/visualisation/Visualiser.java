package uk.ac.warwick.dcs.visualisation;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.warwick.dcs.visualisation.buttons.ConfigButton;
import uk.ac.warwick.dcs.visualisation.buttons.InfoButton;
import uk.ac.warwick.dcs.visualisation.buttons.TabsButton;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private VisualisationTogglePane toggleSlot; // Holds the active pane
    private final List<ModelVisualisation> modelVisualisations;

    // observers of event that new model is added
    private final List<IModelVisualisationSubscriber> subscribers;

    public Visualiser() {
        super();
        modelVisualisations = new ArrayList<>(MAX_CONCURRENT_VISUALISATIONS);
        subscribers = new LinkedList<>();
    }

    @Override
    public void start(Stage stage) {
        // for singleton, since launch()/start() creates its own
        // internal instance -- I got this from ChatGPT
        instance = this;

        // create root pane
        root = new StackPane();

        // initialise and set up the toggling visualisation pane
        toggleSlot = new VisualisationTogglePane(Constants.VISUALISATION_WIDTH, Constants.VISUALISATION_HEIGHT);

        // buttons available
        ConfigButton configBtn = new ConfigButton();
        InfoButton infoBtn = new InfoButton();
        TabsButton tabsBtn = new TabsButton(modelVisualisations);

        root.getChildren().addAll(toggleSlot, configBtn, infoBtn, tabsBtn);

        // set alignments of UI buttons
        StackPane.setAlignment(configBtn, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(infoBtn, Pos.TOP_RIGHT);
        StackPane.setAlignment(tabsBtn, Pos.BOTTOM_LEFT);

        // add subscribers
        subscribers.add(tabsBtn);
        subscribers.add(toggleSlot);

        // create the root scene and stage sett
        stage.setTitle(WINDOW_TITLE);
        stage.setWidth(Constants.VISUALISER_WIDTH);
        stage.setHeight(Constants.VISUALISER_HEIGHT);
        stage.setOnCloseRequest(Event::consume); // don't close on close event
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED); // remove menu with close/minimise/maximise
        Scene rootScene = new Scene(root, Constants.VISUALISER_WIDTH, Constants.VISUALISER_HEIGHT);

        stage.setScene(rootScene);
        stage.show();

        // TODO: draw from junctionconfiguration
        // TODO: hovering help
        // TODO: dropdown select of running panes
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
        if (modelVisualisations.size() == MAX_CONCURRENT_VISUALISATIONS) {
            throw new RuntimeException("Cannot support more than " + MAX_CONCURRENT_VISUALISATIONS + " visualisations");
        }

        modelVisualisations.add(modelVisualisation);

        // update components requiring update
        for (IModelVisualisationSubscriber subscriber : subscribers) {
            subscriber.notify(modelVisualisation);
        }
    }

    /**
     *
     * @return Singleton instance of <code>Visualiser</code>.
     */
    public static Visualiser getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Visualiser must be created before getInstance() called");
        }
        return instance;
    }
}
