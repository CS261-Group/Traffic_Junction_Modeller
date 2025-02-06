package uk.ac.warwick.dcs.ui;

public interface ILaneChangedSubscriber {
    void notify(int oldLanes, int newLanes);
}
