package uk.ac.warwick.dcs.contracts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.structure.*;


import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight; 

import java.util.Arrays;
import java.util.List;

public class TestAggregatingLanes {

    @Test
    public void testAggregateMetricsForIncomingRoad() {

        IncomingLane lane1 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, false, true, false});
        IncomingLane lane2 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, false, true, false});
        IncomingLane lane3 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, true, true, true});

        int incomingFlow = 100;
        int[] outgoingFlows = {0, 300, 400, 0};

        IncomingRoad incomingRoad = new IncomingRoad(Direction.NORTH, Arrays.asList(lane1, lane2, lane3), incomingFlow, outgoingFlows);

        assertEquals(3, incomingRoad.numLanes(), "The number of lanes should be 3.");
        assertEquals(incomingFlow, incomingRoad.getIncomingFlow(), "The incoming flow should match the provided value.");
    }

    @Test
    public void testAggregateMetricsForOutgoingRoad() {

        OutgoingLane lane1 = new OutgoingLane(Direction.NORTH);
        OutgoingLane lane2 = new OutgoingLane(Direction.NORTH);
        OutgoingLane lane3 = new OutgoingLane(Direction.SOUTH);

        List<OutgoingLane> outgoingLanes = Arrays.asList(lane1, lane2, lane3);
        OutgoingRoad outgoingRoad = new OutgoingRoad(Direction.EAST, outgoingLanes);

        assertEquals(3, outgoingRoad.numLanes(), "The number of outgoing lanes should be 3.");
    }

    @Test
    public void testAggregateMetricsForJunction() {
        // Define the incoming lanes for each direction
        IncomingLane laneNorth1 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, false, true, false});
        IncomingLane laneNorth2 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, false, true, false});
        IncomingLane laneNorth3 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, true, true, true});
        
        IncomingLane laneEast1 = new IncomingLane(Direction.EAST, VehicleType.CAR, new boolean[]{true, false, true, true});
        IncomingLane laneEast2 = new IncomingLane(Direction.EAST, VehicleType.CAR, new boolean[]{true, false, true, true});
        
        IncomingLane laneSouth1 = new IncomingLane(Direction.SOUTH, VehicleType.CAR, new boolean[]{false, true, false, false});
        
        IncomingLane laneWest1 = new IncomingLane(Direction.WEST, VehicleType.CAR, new boolean[]{true, false, true, false});
        
  
        OutgoingLane outgoingLaneNorth = new OutgoingLane(Direction.NORTH);
        OutgoingLane outgoingLaneSouth = new OutgoingLane(Direction.SOUTH);
        OutgoingLane outgoingLaneWest = new OutgoingLane(Direction.WEST);
        OutgoingLane outgoingLaneEast = new OutgoingLane(Direction.EAST);
        
     
        IncomingRoad incomingRoadNorth = new IncomingRoad(Direction.NORTH, Arrays.asList(laneNorth1, laneNorth2, laneNorth3), 100, new int[]{0, 40, 30, 0});
        IncomingRoad incomingRoadEast = new IncomingRoad(Direction.EAST, Arrays.asList(laneEast1, laneEast2), 150, new int[]{0, 0, 60, 0});
        IncomingRoad incomingRoadSouth = new IncomingRoad(Direction.SOUTH, Arrays.asList(laneSouth1), 80, new int[]{0, 40, 0, 0});
        IncomingRoad incomingRoadWest = new IncomingRoad(Direction.WEST, Arrays.asList(laneWest1), 60, new int[]{0, 30, 0, 0});
        
  
        OutgoingRoad outgoingRoadNorth = new OutgoingRoad(Direction.NORTH, Arrays.asList(outgoingLaneNorth));
        OutgoingRoad outgoingRoadSouth = new OutgoingRoad(Direction.SOUTH, Arrays.asList(outgoingLaneSouth));
        OutgoingRoad outgoingRoadWest = new OutgoingRoad(Direction.WEST, Arrays.asList(outgoingLaneWest));
        OutgoingRoad outgoingRoadEast = new OutgoingRoad(Direction.EAST, Arrays.asList(outgoingLaneEast));
        

        Carriageway northCarriageway = new Carriageway(Direction.NORTH, outgoingRoadNorth, incomingRoadNorth, false);
        Carriageway eastCarriageway = new Carriageway(Direction.EAST, outgoingRoadEast, incomingRoadEast, true);
        Carriageway southCarriageway = new Carriageway(Direction.SOUTH, outgoingRoadSouth, incomingRoadSouth, false);
        Carriageway westCarriageway = new Carriageway(Direction.WEST, outgoingRoadWest, incomingRoadWest, false);
        
 
        int totalIncomingLanes = incomingRoadNorth.numLanes() + incomingRoadEast.numLanes() + incomingRoadSouth.numLanes() + incomingRoadWest.numLanes();
        
   
        int totalOutgoingLanes = outgoingRoadNorth.numLanes() + outgoingRoadSouth.numLanes() + outgoingRoadWest.numLanes() + outgoingRoadEast.numLanes();
        
    
        JunctionConfiguration junction = new JunctionConfiguration(
                new Carriageway[]{northCarriageway, eastCarriageway, southCarriageway, westCarriageway},
                new FixedCycleTrafficLight(),
                null 
        );
        
 
        assertEquals(totalIncomingLanes, junction.getTotalNumIncomingLanes(), "The total number of incoming lanes should be " + totalIncomingLanes);
        assertEquals(totalOutgoingLanes, junction.getTotalNumOutgoingLanes(), "The total number of outgoing lanes should be " + totalOutgoingLanes);
    }
}
