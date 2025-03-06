package uk.ac.warwick.dcs.ui.panels;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.DirectionInputData;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

/**
 * Panel used to get all relevant carriageway (structural) data from
 * a single direction.
 */
public class DirectionPanel extends CustomPanel implements IReadablePanel<DirectionData> {
    private final Direction direction;
//    private LaneArrivalFlowsPanel laneArrivalFlows;
    private LaneDirectionsPanel laneDirections;
//    private LaneDeparturesPanel laneDepartures;
    private FlowsPanel flowsPanel;
    private DirectionInputsPanel directionInputs;
    private final List<ILaneChangedSubscriber> externalSubscribers;

    public DirectionPanel(Font headingFont, Font labelFont, Direction direction,  List<ILaneChangedSubscriber> externalSubscribers) {
        super(headingFont, labelFont);
        this.direction = direction;
        this.externalSubscribers = externalSubscribers;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        setBorder(BorderFactory.createTitledBorder(Constants.DIRECTIONS[direction.ordinal()]));

        // custom panels required for each section
        flowsPanel = new FlowsPanel(headingFont, labelFont, direction);

        laneDirections = new LaneDirectionsPanel(headingFont, labelFont);


        // although this is first, it needs to be instantiated last so all the other
        // objects (which are subscribers) can be registered
        List<ILaneChangedSubscriber> subscribers = new LinkedList<>();
        subscribers.add(laneDirections);
        subscribers.addAll(externalSubscribers); // make sure external subscribers are also added

        // first panel defines alignment for whole column, for some reason
        directionInputs = new DirectionInputsPanel(headingFont, labelFont, Constants.MAX_LANES, subscribers, direction);

        add(directionInputs);
        add(flowsPanel);
        add(laneDirections);
    }

    @Override
    public DirectionData getValue() {
        DirectionInputData directionInputData = directionInputs.getValue();
        return new DirectionData(
                direction,
                flowsPanel.getValue(),
//                laneArrivalFlows.getValue(),
                laneDirections.getValue(),
//                laneDepartures.getValue(),
//                directionInputData.busLane(),
//                directionInputData.pedestrianCrossing(),
                directionInputData.numOutgoingLanes()
        );
    }
}
