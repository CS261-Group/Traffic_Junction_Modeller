package uk.ac.warwick.dcs.visualisation.buttons;

import javafx.stage.Stage;
import uk.ac.warwick.dcs.visualisation.Constants;
import uk.ac.warwick.dcs.visualisation.menus.InfoMenu;

public class InfoButton extends UiButton {
    public InfoButton(InfoMenu infoMenu) {
        super("/question.png");

        setOnAction(e -> {
            if (infoMenu.isShowing()) {
                infoMenu.hide();
            } else {
                Stage stage = (Stage) getScene().getWindow();
                if (stage != null) {
                    // Get the window's position on the screen
                    double windowX = stage.getX();
                    double windowY = stage.getY();
                    double windowWidth = stage.getWidth();
                    double windowHeight = stage.getHeight();

                    // Show the popup at the calculated position
                    // I'll be so real these are magic numbers
                    infoMenu.show(
                            stage,
                            windowX + (windowWidth - Constants.CENTRE_POPUP_WIDTH) / 2D,
                            windowY + (windowHeight - Constants.CENTRE_POPUP_HEIGHT) / 2D
                    );
                }
            }
        });
    }
}
