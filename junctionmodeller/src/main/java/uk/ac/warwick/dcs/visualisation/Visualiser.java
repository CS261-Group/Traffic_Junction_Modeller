package uk.ac.warwick.dcs.visualisation;

/*
 * REMEMBER TO TALK ABOUT THIS IN THE DESIGN DOC:
 * 
 * Decided against FXGL in favour of Swing because FXGL was too powerful.
 * Swing is in-built and actually has all I need 
 */

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.LinkedList;
import java.util.List;

public class Visualiser extends GameApplication implements IModelVisualisation {
    private static final String WINDOW_TITLE = "Junction Visualiser";
    private final List<ModelVisualisation> modelVisualisations;

    public Visualiser() {
        super();
        modelVisualisations = new LinkedList<>();
    }

    /**
     * Launch the 'game', i.e., visualise the app.
     */
    public void run(String[] args) {
        launch(args);

    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(Constants.VISUALISATION_WIDTH);
        settings.setHeight(Constants.VISUALISATION_HEIGHT);
        settings.setTitle(WINDOW_TITLE);
        settings.setVersion("");
    }

    @Override
    public void notify(ModelUpdate update) {
        System.out.println("NOTIFIED");
        // TODO: implement
    }
}
