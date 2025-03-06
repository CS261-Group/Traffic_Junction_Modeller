package uk.ac.warwick.dcs.visualisation.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import uk.ac.warwick.dcs.visualisation.Constants;

public class InfoMenu extends Popup {
    private final VBox contentPane;

    public InfoMenu() {
        contentPane = new VBox();

        // style content window
        contentPane.setMinWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxWidth(Constants.CENTRE_POPUP_WIDTH);
        contentPane.setMaxHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setMinHeight(Constants.CENTRE_POPUP_HEIGHT);
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        contentPane.setAlignment(Pos.CENTER_LEFT);
        contentPane.setPadding(new Insets(50));

        // add content
        addTextWithRectangle("Incoming lanes", Color.GREY);
        addTextWithRectangle("Outgoing lanes", Color.LIGHTGREY);
        addTextWithImage("Delete current visualisation", "/delete.png");
        addTextWithImage("Help menu (this)", "/question.png");
        addTextWithImage("Change active configuration", "/tab.png");
        addTextWithImage("See current Model's configuration", "/cog-wheel-silhouette.png");

        // content to popup
        getContent().setAll(contentPane);
    }

    private void addTextWithRectangle(String text, Color color) {
        HBox entry = new HBox(10);
        entry.setAlignment(Pos.CENTER_LEFT);
        entry.getChildren().add(new Text(text));
        Rectangle rect = new Rectangle(50, 20, color);
        entry.getChildren().add(rect);
        contentPane.getChildren().add(entry);
    }

    private void addTextWithImage(String text, String imageUrl) {
        HBox entry = new HBox(10);

        entry.setAlignment(Pos.CENTER_LEFT);
        entry.getChildren().add(new Text(text));

        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        entry.getChildren().add(imageView);
        contentPane.getChildren().add(entry);
    }
}