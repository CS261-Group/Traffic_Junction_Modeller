package uk.ac.warwick.dcs.visualisation;

import javafx.scene.layout.Pane;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.model.messaging.EvaluationUpdate;
import uk.ac.warwick.dcs.model.messaging.ModelUpdate;
import uk.ac.warwick.dcs.model.messaging.ModelUpdateType;
import uk.ac.warwick.dcs.visualisation.structure.JunctionPane;


public class ModelVisualisation extends Pane implements IModelVisualisation {
    private final String modelName;
    private final JunctionConfiguration junctionConfig; // Store the JunctionConfiguration
    
    // Constructor now accepts JunctionConfiguration
    public ModelVisualisation(String modelName, JunctionPane junctionPane, JunctionConfiguration junctionConfig) {
        super();
        this.modelName = modelName;
        this.junctionConfig = junctionConfig; // Store the JunctionConfiguration
        setWidth(Constants.VISUALISER_WIDTH);
        setHeight(Constants.VISUALISER_HEIGHT);
        getChildren().setAll(junctionPane);
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    public JunctionConfiguration getJunctionConfiguration() {
        return junctionConfig; // Return the stored JunctionConfiguration
    }

    @Override
    public void notify(ModelUpdate update) {
        // Handle notifications
    }
}
