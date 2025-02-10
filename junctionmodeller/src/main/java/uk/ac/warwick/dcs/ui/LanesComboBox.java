package uk.ac.warwick.dcs.ui;

import uk.ac.warwick.dcs.ui.events.LanesChangedListener;

import javax.swing.JComboBox;
import java.util.List;

public class LanesComboBox extends JComboBox<Integer> {
    private static final int DEFAULT_SELECTION = 2;

    public LanesComboBox(int maxNumLanes, List<ILaneChangedSubscriber> subscribers) {
        for (int laneCount = 1; laneCount <= maxNumLanes; laneCount++) {
            addItem(laneCount);
        }
        addItemListener(new LanesChangedListener(subscribers));

        // we do this after adding the event listener to make sure all
        // subscribers get notified
        setSelectedItem(DEFAULT_SELECTION);
    }


}
