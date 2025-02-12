package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.panels.*;

import java.awt.Font;
import java.util.List;

public class DirectionPanelFactory {
    private final Font headingFont;
    private final Font labelFont;

    public DirectionPanelFactory(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    public DirectionPanel createDirectionPanel(Direction direction, List<ILaneChangedSubscriber> externalSubscribers) {
        DirectionPanel directionPanel = new DirectionPanel(headingFont, labelFont, direction, externalSubscribers);
        return directionPanel;
    }
}
