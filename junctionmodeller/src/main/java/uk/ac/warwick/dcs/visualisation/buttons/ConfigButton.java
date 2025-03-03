package uk.ac.warwick.dcs.visualisation.buttons;

public class ConfigButton extends UiButton {
    public ConfigButton() {
        super("/cog-wheel-silhouette.png");

        setOnAction(e -> {
            System.out.println("SETTINGS");
            // TODO: popup menu for settings
        });
    }
}
