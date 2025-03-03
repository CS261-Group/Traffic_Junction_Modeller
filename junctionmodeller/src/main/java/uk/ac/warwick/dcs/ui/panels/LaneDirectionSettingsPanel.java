package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ItemEvent;

/**
 * Panel used to enter a single lane's available directions.
 */
public class LaneDirectionSettingsPanel extends CustomPanel implements IReadablePanel<AvailableDirections> {
    private final Direction direction;
    private final AvailableDirections availableDirections;
    private final int laneNum;

    public LaneDirectionSettingsPanel(Font headingFont, Font labelFont, Direction dir, int num) {
        super(headingFont, labelFont);
        direction = dir;
        laneNum = num;
        availableDirections = new AvailableDirections();
        setUp();
    }

    @Override
    protected void setUp() {
        JLabel laneLbl = new JLabel("Lane " + laneNum);
        laneLbl.setFont(labelFont);
        add(laneLbl);

        // we want the order of checkboxes to be: left, forward, right
        // NORTH (0) -> order E,S,W (1,2,3)
        // EAST  (1) -> order S,W,N (2,3,0)
        // SOUTH (2) -> order W,N,E (3,0,1)
        // WEST  (3) -> order N,E,S (0,1,2)
        // i.e., start from .ordinal() + 1 and count 4 times modding by 4
        for (int i = 1; i < 4; i++) {
            int directionIdx = (i + direction.ordinal()) % 4;
            assert directionIdx != direction.ordinal();

            // create checkbox
            JCheckBox directionCheckBox = new JCheckBox(Constants.DIRECTIONS[directionIdx]);
            directionCheckBox.setFont(labelFont);

            // attach event listener that updates the available directions as required
            directionCheckBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {
                    boolean isAvailable = ((JCheckBox)e.getItem()).isSelected();
                    switch (directionIdx) {
                        case 0: // NORTH
                            availableDirections.setN(isAvailable);
                            break;
                        case 1: // EAST
                            availableDirections.setE(isAvailable);
                            break;
                        case 2: // SOUTH
                            availableDirections.setS(isAvailable);
                            break;
                        case 3: // WEST
                            availableDirections.setW(isAvailable);
                            break;
                        default: // error - should never occur
                            assert false;
                    }
                }
            });
            directionCheckBox.setSelected(true);
            add(directionCheckBox);
        }
    }

    @Override
    public AvailableDirections getValue() {
        return availableDirections;
    }
}
