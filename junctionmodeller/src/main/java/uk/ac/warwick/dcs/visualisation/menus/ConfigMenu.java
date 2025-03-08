package uk.ac.warwick.dcs.visualisation.menus;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;

import uk.ac.warwick.dcs.visualisation.Constants;
import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationChangedSubscriber;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;



public class ConfigMenu extends Popup implements IModelVisualisationSubscriber {
    private final VBox contentPane;
    private final List<ModelVisualisation> modelVisualisations;
    private final List<IModelVisualisationChangedSubscriber> subscribers;

    // This will store the current active visualisation
    private ModelVisualisation currentVisualisation;

    public ConfigMenu(List<ModelVisualisation> visualisations, List<IModelVisualisationChangedSubscriber> changeSubscribers) {
        contentPane = new VBox();
        modelVisualisations = visualisations;
        subscribers = changeSubscribers;

        // Style content window
        contentPane.setMinWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setMinHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));

 
        getContent().setAll(contentPane);
    }

    public void updateFlows() {
        // Clear existing flow entries to replace with new data
        contentPane.getChildren().clear();
        
        // Check if we have a current visualisation
        if (currentVisualisation != null) {
            // Get the instance of JunctionConfiguration from the current visualisation
            JunctionConfiguration junctionConfig = currentVisualisation.getJunctionConfiguration();

            int[][] flows = new int[Direction.values().length][5]; 
        

            for (Direction direction : Direction.values()) {

                flows[direction.ordinal()][0] = junctionConfig.getIncomingFlow(direction);
        

                for (Direction outgoingDirection : Direction.values()) {
                 
                    if (outgoingDirection != direction) {
                        int outgoingFlow = getOutgoingFlow(junctionConfig, direction, outgoingDirection);
                        flows[direction.ordinal()][outgoingDirection.ordinal()] = outgoingFlow;
                        
                 
                    }
                }
            }
        
      
            for (Direction direction : Direction.values()) {
                String directionName = direction.name();
                int[] directionFlows = flows[direction.ordinal()]; 
                
  
                StringBuilder flowDetails = new StringBuilder(directionName + " Flows: [Incoming: " + junctionConfig.getIncomingFlow(direction) + ", Outgoing: ");
                
          
                boolean first = true;
                for (Direction outgoingDirection : Direction.values()) {
                    if (outgoingDirection != direction) {
                        if (!first) {
                            flowDetails.append(", ");
                        }
                        flowDetails.append(directionFlows[outgoingDirection.ordinal()]);
                        first = false;
                    }
                }
    
                // Close the array and add the flow entry
                flowDetails.append("]");
                addFlowEntry(flowDetails.toString(), Color.LIGHTGREY); 
            }
        
       
            getContent().setAll(contentPane);
        }
    }


    private void addFlowEntry(String description, Color color) {
        HBox flowEntry = new HBox(10);
        flowEntry.setAlignment(Pos.CENTER_LEFT);

        Text flowText = new Text(description); 
        flowEntry.getChildren().add(flowText);

   

        contentPane.getChildren().add(flowEntry); 
    }

    // Method to get the outgoing flow between two directions
    private int getOutgoingFlow(JunctionConfiguration junctionConfig, Direction fromDirection, Direction toDirection) {
        try {
            return junctionConfig.getOutgoingFlowFromTo(fromDirection, toDirection);
        } catch (InvalidDirectionException e) {
            System.out.println("Invalid direction encountered: " + e.getMessage());
            return 0;  
        }
    }

    @Override
    public void notifyAdd(ModelVisualisation visualisation) {
        currentVisualisation = visualisation;
        updateFlows();

        // Notify all subscribers
        for (IModelVisualisationChangedSubscriber subscriber : subscribers) {
            subscriber.notifyChanged(visualisation);
        }
    }
    public void setCurrentVisualisation(ModelVisualisation visualisation) {
        this.currentVisualisation = visualisation;
        updateFlows();  
    }
}
