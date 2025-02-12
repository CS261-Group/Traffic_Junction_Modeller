package uk.ac.warwick.dcs.ui.interfaces;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public interface ILaneChangedSubscriber {
    void notify(int oldLanes, int newLanes, Direction direction);
}
