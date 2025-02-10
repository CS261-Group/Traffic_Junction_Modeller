package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.panels.*;

import java.awt.Font;

public class DirectionPanelFactory {
    private final Font headingFont;
    private final Font labelFont;

    public DirectionPanelFactory(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    public DirectionPanel createDirectionPanel(Direction direction, int windowWidth) {
        DirectionPanel directionPanel = new DirectionPanel(direction, headingFont, labelFont, windowWidth);
        return directionPanel;
    }
}
