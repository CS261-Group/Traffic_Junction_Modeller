package uk.ac.warwick.dcs.visualisation.buttons;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.interfaces.IModelVisualisationSubscriber;
import uk.ac.warwick.dcs.visualisation.menus.TabsMenu;

import java.util.List;

import static javafx.geometry.Pos.BOTTOM_RIGHT;

public class TabsButton extends UiButton implements IModelVisualisationSubscriber {
    private final TabsMenu tabsMenu;

    public TabsButton(List<ModelVisualisation> modelVisualisations) {
        super("/tab.png");

        tabsMenu = new TabsMenu(modelVisualisations);

        // toggle tabs menu on click
        setOnAction(e -> {
            // TODO: fix
            if (tabsMenu.isShowing()) {
                tabsMenu.hide();
            } else {
                tabsMenu.show(getScene().getWindow());
            }
        });
    }

    @Override
    public void notify(ModelVisualisation visualisation) {
        tabsMenu.notify(visualisation);
    }
}
