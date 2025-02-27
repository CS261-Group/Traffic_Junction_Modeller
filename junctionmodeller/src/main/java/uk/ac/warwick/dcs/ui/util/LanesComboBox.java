package uk.ac.warwick.dcs.ui.util;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.events.LanesChangedListener;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.JComboBox;
import java.util.List;

/**
 * Combo box specialised to alert a list of subscribers upon changing.
 */
public class LanesComboBox extends JComboBox<Integer> implements IReadablePanel<Integer> {
    private static final int DEFAULT_SELECTION = 2;

    public LanesComboBox(int maxNumLanes, List<ILaneChangedSubscriber> subscribers, Direction direction) {
        for (int laneCount = 1; laneCount <= maxNumLanes; laneCount++) {
            addItem(laneCount);
        }
        addItemListener(new LanesChangedListener(subscribers, direction));

        // we do this after adding the event listener to make sure all
        // subscribers get notified
        setSelectedItem(DEFAULT_SELECTION);
    }

    @Override
    public Integer getValue() {
        assert getSelectedItem() != null;
        return (int)getSelectedItem();
    }
}
