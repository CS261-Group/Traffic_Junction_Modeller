package uk.ac.warwick.dcs.visualisation.buttons;

import javafx.stage.Stage;
import uk.ac.warwick.dcs.visualisation.Constants;
import uk.ac.warwick.dcs.visualisation.menus.TabsMenu;

public class TabsButton extends UiButton {
    /**
     * Empirical magic number to get the width of the popup menu just right.
     */
    private static final int WIDTH_OFFSET = -2;

    /**
     * Empirical magic number to get the height of the popup menu just right.
     */
    private static final int HEIGHT_OFFSET = 78;


    public TabsButton(TabsMenu tabsMenu) {
        super("/tab.png");

        // toggle tabs menu on click
        setOnAction(e -> {
            if (tabsMenu.isShowing()) {
                tabsMenu.hide();
            } else {
                Stage stage = (Stage) getScene().getWindow();
                if (stage != null) {
                    // Get the window's position on the screen
                    double windowX = stage.getX();
                    double windowHeight = stage.getHeight();

                    // Show the popup at the calculated position
                    // I'll be so real these are magic numbers
                    tabsMenu.show(
                            stage,
                            windowX + WIDTH_OFFSET,
                            windowHeight - Constants.SIDE_POPUP_HEIGHT + HEIGHT_OFFSET
                    );
                }
            }
        });
    }
}
