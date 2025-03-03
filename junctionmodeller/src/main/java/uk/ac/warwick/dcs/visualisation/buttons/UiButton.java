package uk.ac.warwick.dcs.visualisation.buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

abstract class UiButton extends Button {
    public UiButton(String resourcePath) {
        super();

        // 10 px padding
        setPadding(new Insets(10));

        // Load the image from URL
        Image questionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(resourcePath)));

        // Set image inside button
        ImageView imageView = new ImageView(questionImage);
        imageView.setFitWidth(24); // Adjust size
        imageView.setFitHeight(24);
        setGraphic(imageView);
    }
}
