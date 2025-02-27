package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.panels.*;

import java.awt.Font;
import java.util.List;

/**
 * Factory for creating <code>DirectionPanel</code> to encapsulate
 * all the panels for getting directional inputs easier.
 */
public class DirectionPanelFactory {
    private final Font headingFont;
    private final Font labelFont;

    public DirectionPanelFactory(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    /**
     *
     * @param direction Incoming direction of the carriageway we are configuring.
     * @param externalSubscribers List of subscribers to notify upon changing the number of lanes.
     * @return Constructed <code>DirectionPanel</code> from parameters.
     */
    public DirectionPanel createDirectionPanel(Direction direction, List<ILaneChangedSubscriber> externalSubscribers) {
        return new DirectionPanel(headingFont, labelFont, direction, externalSubscribers);
    }
}
