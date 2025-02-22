package uk.ac.warwick.dcs.ui.interfaces;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Interface used with <code>LanesChangedListener</code> for the subscriber
 * objects/UI elements to be notified.
 */
public interface ILaneChangedSubscriber {
    /**
     *
     * @param oldLanes The previous number of lanes before update.
     * @param newLanes The new number of lanes that we updated to.
     * @param direction The inbound direction of the carriageway we
     *                  changed the number of lanes to.
     */
    void notify(int oldLanes, int newLanes, Direction direction);
}
