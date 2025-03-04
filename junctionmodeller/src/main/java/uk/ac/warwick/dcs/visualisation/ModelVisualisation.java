package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;

import java.util.Random;

public class ModelVisualisation extends Pane implements IModelVisualisation {
    private final String modelName;

    // TODO: remove
    static final Random rand = new Random();

    public ModelVisualisation(String modelName) {
        super();
        this.modelName = modelName;
        setWidth(Constants.VISUALISATION_WIDTH);
        setHeight(Constants.VISUALISATION_HEIGHT);

        // TODO: remove, just for seeing them
        setStyle("-fx-background-color: rgb(" + rand.nextInt(0, 256)+ "," +
                rand.nextInt(0, 256) + "," +
                rand.nextInt(0, 256) + ");");
    }

    @Override
    public String getModelName() { return modelName; }

    @Override
    public void notify(ModelUpdate update) {
        // TODO: implement
    }
}
