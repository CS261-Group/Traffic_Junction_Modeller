package uk.ac.warwick.dcs.visualisation;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.warwick.dcs.model.messaging.EvaluationUpdate;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;
import uk.ac.warwick.dcs.model.messaging.ModelUpdateType;
import uk.ac.warwick.dcs.ui.MainForm;
import uk.ac.warwick.dcs.visualisation.buttons.ConfigButton;
import uk.ac.warwick.dcs.visualisation.buttons.DeleteButton;
import uk.ac.warwick.dcs.visualisation.buttons.InfoButton;
import uk.ac.warwick.dcs.visualisation.buttons.TabsButton;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;
import uk.ac.warwick.dcs.visualisation.menus.InfoMenu;
import uk.ac.warwick.dcs.visualisation.menus.ConfigMenu;
import uk.ac.warwick.dcs.visualisation.menus.TabsMenu;

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
     * Window title.
     */
    private static final String WINDOW_TITLE = "Junction Visualiser";

    private StackPane root;
    private VisualisationTogglePane toggleSlot; // Holds the active pane
    private final List<ModelVisualisation> modelVisualisations;

    // popup menus
    private TabsMenu tabsMenu;
    private InfoMenu infoMenu;
    private ConfigMenu configMenu;

    // observers of event that new model is added
    private final List<IModelVisualisationSubscriber> addSubscribers;

    // main form for alerting
    private MainForm form;

    public Visualiser() {
        super();
        modelVisualisations = new ArrayList<>(8);
        addSubscribers = new LinkedList<>();
    }

    @Override
    public void start(Stage stage) {
        // for singleton, since launch()/start() creates its own
        // internal instance -- I got this from ChatGPT
        instance = this;

        // create root pane
        root = new StackPane();

        // initialise and set up the toggling visualisation pane
        toggleSlot = new VisualisationTogglePane(Constants.VISUALISER_WIDTH, Constants.VISUALISER_HEIGHT);

        // popups available
        tabsMenu = new TabsMenu(modelVisualisations, List.of(toggleSlot));
        infoMenu = new InfoMenu();
        configMenu = new ConfigMenu();

        // buttons available
        ConfigButton configBtn = new ConfigButton(configMenu);
        TabsButton tabsBtn = new TabsButton(tabsMenu);
        InfoButton infoBtn = new InfoButton(infoMenu);
        DeleteButton deleteBtn = new DeleteButton();

        root.getChildren().addAll(toggleSlot, configBtn, infoBtn, tabsBtn, deleteBtn);

        // set alignments of UI buttons
        StackPane.setAlignment(configBtn, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(infoBtn, Pos.TOP_RIGHT);
        StackPane.setAlignment(tabsBtn, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(deleteBtn, Pos.TOP_LEFT);

        // add subscribers
        addSubscribers.add(tabsMenu);
        addSubscribers.add(toggleSlot);

        // create the root scene and stage sett
        stage.setTitle(WINDOW_TITLE);
        stage.setWidth(Constants.VISUALISER_WIDTH);
        stage.setHeight(Constants.VISUALISER_HEIGHT);
        stage.setOnCloseRequest(Event::consume); // don't close on close event
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        Scene rootScene = new Scene(root, Constants.VISUALISER_WIDTH, Constants.VISUALISER_HEIGHT);

        stage.setScene(rootScene);
        stage.show();
    }

    /**
     * Launch the 'game', i.e., visualise the app.
     */
    public void run(String[] args, MainForm mainForm) {
        form = mainForm;
        launch(args);
    }

    /**
     * Package private method for <code>VisualisationFactory</code>.
     * @param modelVisualisation Model visualisation to add to displayed models.
     */
    public void addModelVisualisation(ModelVisualisation modelVisualisation) {
        modelVisualisations.add(modelVisualisation);

        // update components requiring update
        for (IModelVisualisationSubscriber subscriber : addSubscribers) {
            subscriber.notifyAdd(modelVisualisation);
        }
    }

    /**
     * The
     * @param modelUpdate The update object.
     */
    public void notifyUpdate(String configName, EvaluationUpdate modelUpdate) {
        form.addMetricToHistory(configName, modelUpdate.getMetrics());
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
