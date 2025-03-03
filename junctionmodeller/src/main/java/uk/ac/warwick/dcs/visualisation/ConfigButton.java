package uk.ac.warwick.dcs.visualisation;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfigButton extends Button {
    public ConfigButton() {
        super();

        // 10 px padding
        setPadding(new Insets(10));

        // Load the image from URL
        Image cogImage = new Image(getClass().getResourceAsStream("/cog-wheel-silhouette.png"));

        // Set image inside button
        ImageView imageView = new ImageView(cogImage);
        imageView.setFitWidth(24); // Adjust size
        imageView.setFitHeight(24);
        setGraphic(imageView);
    }
}
