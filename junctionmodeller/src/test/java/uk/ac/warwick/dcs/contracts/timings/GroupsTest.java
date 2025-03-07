package uk.ac.warwick.dcs.contracts.timings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.NoValueExistsException;
import uk.ac.warwick.dcs.contracts.structure.*;

import java.util.List;

public class GroupsTest {

    @Test
    public void TestGroupTimings() {

        IncomingLane lane1 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, true, false, false});
        IncomingLane lane2 = new IncomingLane(Direction.EAST, VehicleType.CAR, new boolean[]{true, false, false, false});
        IncomingLane lane3 = new IncomingLane(Direction.SOUTH, VehicleType.CAR, new boolean[]{false, false, false, false});


        Group group1 = new Group(1, List.of(lane1, lane2, lane3));


        assertTrue(group1.containsLane(lane1), "Group should contain lane1.");
        assertTrue(group1.containsLane(lane2), "Group should contain lane2.");
        assertTrue(group1.containsLane(lane3), "Group should contain lane3.");
        
        GroupTiming group1Timing = new GroupTiming(1, 30);
      
        Groups groups = new Groups(List.of(group1), false, List.of(group1Timing));

        assertDoesNotThrow(() -> {
            groups.getGroupTiming(1); // This should not throw an exception
        }, "getGroupTiming should not throw an exception for valid group");

        assertDoesNotThrow(() -> {
            assertEquals(30, groups.getGroupTiming(1).getTiming(), "Group 1 timing should be 30 seconds.");
        }, "getGroupTiming should not throw an exception for valid group");

        assertDoesNotThrow(() -> {
            assertEquals(30, groups.getLaneTiming(lane1), "Lane 1 timing should match the group timing.");
            assertEquals(30, groups.getLaneTiming(lane2), "Lane 2 timing should match the group timing.");
            assertEquals(30, groups.getLaneTiming(lane3), "Lane 3 timing should match the group timing.");
        }, "should not throw a no value exception");
     
        Group group2 = new Group(2, List.of(lane1, lane2));
        GroupTiming group2Timing = new GroupTiming(2, 30);
        
        Groups groupsWithMultiple = new Groups(List.of(group1, group2), false, List.of(group1Timing, group2Timing));

        assertDoesNotThrow(() -> {
            assertEquals(30, groupsWithMultiple.getGroupTiming(1).getTiming(), "Group 1 timing should still be 30 seconds.");
            assertEquals(30, groupsWithMultiple.getGroupTiming(2).getTiming(), "Group 2 timing should be 30 seconds.");
        }, "should not throw a no value exception");

        assertDoesNotThrow(() -> {
            assertEquals(30, groupsWithMultiple.getLaneTiming(lane1), "Lane 1 timing should match the group 1 timing.");
            assertEquals(30, groupsWithMultiple.getLaneTiming(lane2), "Lane 2 timing should match the group 2 timing.");
        }, "should not throw a no value exception");
    }

    @Test
    public void testCycleTimeCalculation(){
        IncomingLane lane1 = new IncomingLane(Direction.NORTH, VehicleType.CAR, new boolean[]{false, true, false, false});
        IncomingLane lane2 = new IncomingLane(Direction.EAST, VehicleType.CAR, new boolean[]{true, false, false, false});
        IncomingLane lane3 = new IncomingLane(Direction.SOUTH, VehicleType.CAR, new boolean[]{false, false, false, false});

        Group group1 = new Group(1, List.of(lane1, lane2, lane3));
        GroupTiming group1Timing = new GroupTiming(1, 30);

        Group group2 = new Group(2, List.of(lane1, lane2));
        GroupTiming group2Timing = new GroupTiming(2, 30);

        Groups groupsWithMultiple = new Groups(List.of(group1, group2), false, List.of(group1Timing, group2Timing));

        //2*7.5
        assertEquals(15.0, groupsWithMultiple.cycleLostTime(), "Lost time");
        // 30 + 30 + 15
        assertEquals(75, groupsWithMultiple.getCycleTime(),"cycle time should be sum of grouptimings + lost cycle time");

    }

}
