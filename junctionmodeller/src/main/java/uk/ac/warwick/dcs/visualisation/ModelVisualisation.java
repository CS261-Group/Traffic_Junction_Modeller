package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;
import uk.ac.warwick.dcs.visualisation.structure.JunctionPane;

import java.util.Random;

public class ModelVisualisation extends Pane implements IModelVisualisation {
    private final String modelName;

    public ModelVisualisation(String modelName, JunctionPane junctionPane) {
        super();
        this.modelName = modelName;
        setWidth(Constants.VISUALISATION_WIDTH);
        setHeight(Constants.VISUALISATION_HEIGHT);

        getChildren().setAll(junctionPane);
    }

    @Override
    public String getModelName() { return modelName; }

    @Override
    public void notify(ModelUpdate update) {
        // TODO: implement
    }
}
