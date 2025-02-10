package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DirectionPanel extends CustomPanel implements IReadablePanel<DirectionData> {
    private final Direction direction;
    private final int windowWidth;
    private LaneArrivalFlowsPanel laneArrivalFlows;
    private LaneDirectionsPanel laneDirections;
    private LaneDeparturesPanel laneDepartures;

    public DirectionPanel(Direction direction, Font headingFont, Font labelFont, int windowWidth) {
        super(headingFont, labelFont);
        this.direction = direction;
        this.windowWidth = windowWidth;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(BorderFactory.createTitledBorder(direction.toString()));

        // custom panels required for each section
        laneArrivalFlows = new LaneArrivalFlowsPanel(headingFont, labelFont);
        laneArrivalFlows.setAlignmentX(LEFT_ALIGNMENT);
        laneDirections = new LaneDirectionsPanel(headingFont, labelFont, direction);
        laneDirections.setAlignmentX(LEFT_ALIGNMENT);
        laneDepartures = new LaneDeparturesPanel(headingFont, labelFont);
        laneDepartures.setAlignmentX(LEFT_ALIGNMENT);

        // although this is first, it needs to be instantiated last so all the other
        // objects (which are subscribers) can be registered
        List<ILaneChangedSubscriber> subscribers = new LinkedList<>();
        subscribers.add(laneArrivalFlows);
        subscribers.add(laneDirections);
        subscribers.add(laneDepartures);

        // first panel defines alignment for whole column, for some reason
        JPanel directionInputs = new DirectionInputsPanel(Constants.MAX_LANES, subscribers, headingFont, labelFont);
        directionInputs.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(directionInputs);
        add(laneArrivalFlows);
        add(laneDirections);
        add(laneDepartures);
    }

    @Override
    public DirectionData getValue() {
        return new DirectionData(
                laneArrivalFlows.getValue(),
                laneDirections.getValue(),
                laneDepartures.getValue()
        );
    }
}
