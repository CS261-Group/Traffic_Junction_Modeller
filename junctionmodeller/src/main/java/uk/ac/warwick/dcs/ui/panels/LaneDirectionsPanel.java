package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel used to configure the available directions of each lane.
 */
public class LaneDirectionsPanel extends CustomPanel implements ILaneChangedSubscriber, IReadablePanel<List<AvailableDirections>> {
    private final List<LaneDirectionSettingsPanel> lanes;

    public LaneDirectionsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        lanes = new ArrayList<>(Constants.MAX_LANES);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel laneDirectionsHeading = new JLabel("Lane directions");
        laneDirectionsHeading.setFont(headingFont);
        laneDirectionsHeading.setAlignmentX(LEFT_ALIGNMENT);
        add(laneDirectionsHeading);

        // lanes should be generated from initial notification method call
    }

    @Override
    public void notify(int oldLanes, int newLanes, Direction direction) {
        if (oldLanes < newLanes) {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneDirectionSettingsPanel setting = new LaneDirectionSettingsPanel(headingFont, labelFont, direction, lanes.size() + 1);
                setting.setAlignmentX(LEFT_ALIGNMENT);

                add(setting);
                lanes.add(setting);
            }
        } else {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneDirectionSettingsPanel removedSetting = lanes.remove(lanes.size() - 1); // remove last value
                remove(removedSetting);
            }
        }

        // update the UI to incorporate
        updateUI();
    }

    @Override
    public List<AvailableDirections> getValue() {
        return lanes.stream().map(LaneDirectionSettingsPanel::getValue).toList();
    }
}
