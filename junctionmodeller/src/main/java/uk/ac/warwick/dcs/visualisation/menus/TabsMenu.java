package uk.ac.warwick.dcs.visualisation.menus;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.visualisation.Constants;
import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationChangedSubscriber;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;

import java.util.List;

public class TabsMenu extends Popup implements IModelVisualisationSubscriber {
    private static final int CORNER_RADIUS = 10;

    private final StackPane contentPane;
    private final VBox menu;
    private final Button closeBtn;
    private final List<ModelVisualisation> modelVisualisations;
    private final List<IModelVisualisationChangedSubscriber> subscribers;

    public TabsMenu(List<ModelVisualisation> visualisations, List<IModelVisualisationChangedSubscriber> changeSubscribers) {
        modelVisualisations = visualisations;
        subscribers = changeSubscribers;

        // affix popup dimensions
        setWidth(Constants.POPUP_HEIGHT);
        setHeight(Constants.POPUP_WIDTH);

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
        closeBtn = new Button("X");
        closeBtn.setOnAction(e -> hide());

        // menu itself
        menu = new VBox();
        menu.getChildren().addAll(closeBtn);

        // affix content pane dimensions
        contentPane.setMinWidth(Constants.POPUP_WIDTH);
        contentPane.setMinHeight(Constants.POPUP_HEIGHT);
        contentPane.setMaxWidth(Constants.POPUP_WIDTH);
        contentPane.setMaxHeight(Constants.POPUP_HEIGHT);

        // add menu to content pane
        contentPane.getChildren().addAll(menu);

        // add content pane to popup itself
        getContent().add(contentPane);

        updateMenu();
    }

    private void updateMenu() {
        // clear children to reset the menu
        menu.getChildren().clear();

        // add closeBtn in any case
        menu.getChildren().add(closeBtn);

        for (ModelVisualisation modelVisualisation : modelVisualisations) {
            // create button with configuration's name
            Button menuItem = new Button(modelVisualisation.getModelName());

            // fill width space
            menuItem.setMinWidth(Constants.POPUP_WIDTH);
            menuItem.setMaxWidth(Constants.POPUP_WIDTH);

            //
            menuItem.setOnAction(e -> {
                for (IModelVisualisationChangedSubscriber subscriber : subscribers) {
                    subscriber.notifyChanged(modelVisualisation);
                }
                hide();
            });

            // add new menu item
            menu.getChildren().add(menuItem);
        }
    }

    @Override
    public void notifyAdd(ModelVisualisation visualisation) {
        updateMenu();
    }
}
