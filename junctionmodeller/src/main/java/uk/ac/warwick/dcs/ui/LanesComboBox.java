package uk.ac.warwick.dcs.ui;

import javax.swing.*;

class LanesComboBox extends JComboBox<Integer> {
    private static final int DEFAULT_SELECTION = 2;

    public LanesComboBox(int maxNumLanes) {
        for (int laneCount = 1; laneCount <= maxNumLanes; laneCount++) {
            addItem(laneCount);
        }

        setSelectedItem(DEFAULT_SELECTION);

        // TODO: set action on change
    }
}
