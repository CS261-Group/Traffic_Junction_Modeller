package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.ui.events.LanesChangedListener;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class LanesComboBox extends JComboBox<Integer> {
    private static final int DEFAULT_SELECTION = 2;
    private final List<ILaneChangedSubscriber> subscribers;

    public LanesComboBox(int maxNumLanes, List<ILaneChangedSubscriber> subs) {
        for (int laneCount = 1; laneCount <= maxNumLanes; laneCount++) {
            addItem(laneCount);
        }

        subscribers = subs;

        setSelectedItem(DEFAULT_SELECTION);
        addItemListener(new LanesChangedListener(subscribers));
    }


}
