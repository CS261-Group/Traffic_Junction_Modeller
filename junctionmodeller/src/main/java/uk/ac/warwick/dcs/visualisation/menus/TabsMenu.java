package uk.ac.warwick.dcs.visualisation.menus;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;

import java.util.List;

public class TabsMenu extends Popup implements IModelVisualisationSubscriber {
    private static final int POPUP_WIDTH = 150;
    private static final int POPUP_HEIGHT = 200;
    private static final int CORNER_RADIUS = 10;

    private final StackPane contentPane;
    private final VBox menu;
    private final List<ModelVisualisation> modelVisualisations;

    public TabsMenu(List<ModelVisualisation> visualisations) {
        modelVisualisations = visualisations;

        // root content pane
        contentPane = new StackPane();

        // background
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.LIGHTGRAY, // Background color
                new CornerRadii(CORNER_RADIUS), // Rounded corners
                Insets.EMPTY // No padding
        );
        contentPane.setBackground(new Background(backgroundFill));

        // close button
        Button closeBtn = new Button("X");
        closeBtn.setOnAction(e -> hide());

        // menu itself
        menu = new VBox();

        contentPane.getChildren().addAll(menu);
        contentPane.setPrefSize(POPUP_WIDTH, POPUP_HEIGHT);

        updateMenu();
    }

    private void updateMenu() {
        for (ModelVisualisation modelVisualisation : modelVisualisations) {
            Button menuItem = new Button(modelVisualisation.getModelName());
            menuItem.setOnAction(e -> {
                System.out.println("Selected: " + modelVisualisation.getModelName());
                hide();
            });
            menu.getChildren().setAll(menuItem);
        }
    }

    @Override
    public void notify(ModelVisualisation visualisation) {
        updateMenu();
    }
}
