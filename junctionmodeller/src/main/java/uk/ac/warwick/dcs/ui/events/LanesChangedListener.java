package uk.ac.warwick.dcs.ui.events;

import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class LanesChangedListener implements ItemListener {
    private final List<ILaneChangedSubscriber> subscribers;

    public LanesChangedListener(List<ILaneChangedSubscriber> subs) {
        subscribers = subs;
    }

    // used to keep track of previous value, just in case it
    // is required by the listener/subscriber/observer
    private int oldSelection = -1;

    @Override
    public void itemStateChanged(ItemEvent e) {
        switch (e.getStateChange()) {
            case ItemEvent.SELECTED:
                int newSelection = (int) e.getItem();
                for (ILaneChangedSubscriber subscriber : subscribers) {
                    subscriber.notify(oldSelection, newSelection);
                }
                break;
            case ItemEvent.DESELECTED:
                oldSelection = (int) e.getItem();
                break;
        }
    }
}
