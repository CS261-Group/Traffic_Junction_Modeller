package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ItemEvent;

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
        add(new JLabel("Lane " + laneNum));

        for (int i = 0; i < 4; i++) {
            int directionIdx = (i + direction.ordinal()) % 4;
            // since we can't go in the direction we are coming from
            // we must skip the index of the given direction
            if (directionIdx == direction.ordinal()) {
                continue;
            }

            // create checkbox
            JCheckBox directionCheckBox = new JCheckBox(Constants.DIRECTIONS[directionIdx]);

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
