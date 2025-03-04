package uk.ac.warwick.dcs.visualisation.menus;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.visualisation.Constants;

public class InfoMenu extends Popup {
    private final StackPane contentPane;

    public InfoMenu() {
        contentPane = new StackPane();

        // style content window
        contentPane.setMinWidth(Constants.INFO_POPUP_WIDTH);
        contentPane.setMaxWidth(Constants.INFO_POPUP_WIDTH);
        contentPane.setMaxHeight(Constants.INFO_POPUP_HEIGHT);
        contentPane.setMinHeight(Constants.INFO_POPUP_HEIGHT);
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        // add content
        // TODO: what to add, different colours of lanes, lane directions, groups (numbered)

        // content to popup
        getContent().setAll(contentPane);
    }
}
