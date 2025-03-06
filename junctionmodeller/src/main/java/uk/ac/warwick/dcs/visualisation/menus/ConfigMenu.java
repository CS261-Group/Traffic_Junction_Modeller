package uk.ac.warwick.dcs.visualisation.menus;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.visualisation.Constants;

public class ConfigMenu extends Popup {
    private final StackPane contentPane;

    public ConfigMenu() {
        contentPane = new StackPane();

        // style content window
        contentPane.setMinWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setMinHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        // add content

        // content to popup
        getContent().setAll(contentPane);
    }
}
