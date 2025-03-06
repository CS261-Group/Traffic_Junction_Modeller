package uk.ac.warwick.dcs.contracts;

import java.util.Arrays;
import java.util.Iterator;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.exceptions.NoValueExistsException;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.contracts.timings.Groups;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;

/**
 * Object storing all aspects of configurations and settings taken from the user
 * or input file about a junction that is to be modeled/analysed. This object
 * contains references to its constituent:
 * - carriageways
 * - traffic lights
 * - traffic groups
 */
public class JunctionConfiguration implements Iterable<Carriageway> {
    private final Carriageway[] carriageways;

    // Traffic lights exists at the junction level now
    // because they can operate across carriageways
    // e.g. one traffic light turns green to let North and South Through
    private final TrafficLight trafficLights;
    private final Groups groups;

    public JunctionConfiguration(Carriageway[] cw, TrafficLight tl, Groups g) {
        assert cw.length == 4; // sanity checks: one carriageway object per direction
        carriageways = cw;
        trafficLights = tl;
        groups = g;
    }

    /**
     * Getter for contained <code>TrafficLights</code> object.
     * @return The contained <code>TrafficLights</code> object.
     */
    public TrafficLight getTrafficLights() { return trafficLights; }

    public TrafficLightType getTrafficLightType(){
        return trafficLights.getTrafficLightType();
    }

    /**
     * Getter for <code>Groups</code> object.
     * @return The contained <code>Groups</code> object.
     */
    public Groups getGroups() { return groups; }

    public int getGroupTimingValue(int groupNum) throws NoValueExistsException {
        try {
            return groups.getGroupTimingValue(groupNum);
        } catch (NoValueExistsException e) {
            throw new NoValueExistsException(e.getMessage());
        }
    }

    public double getCycleTime(){
        return groups.getCycleTime();
    }

    public double getCycleLostTime(){
        return groups.cycleLostTime();
    }



    /**
     * Getter for specific Carriageway by Direction (e.g., NORTH, SOUTH, EAST, WEST).
     * 
     * @param direction The cardinal direction for which to retrieve the corresponding Carriageway.
     * @return The <code>Carriageway</code> object corresponding to the specified direction.
     * @throws IllegalArgumentException if no <code>Carriageway</code> with the specified direction exists in the configuration.
     */
    public Carriageway getCarriageway(Direction direction) {
        for (Carriageway cw : carriageways) {
            if (cw.getDirection() == direction) {
                return cw;
            }
        }
        throw new IllegalArgumentException("No carriageway with the specified direction");
    }

    /**
     * Getter for the number of traffic light groups in the configuration.
     * 
     * @return The number of traffic light groups in the configuration.
     */
    public int getNumberOfGroups() {
        return groups.getNumGroups();
    }

    /**
     * Getter for total number of incoming lanes across all carriageways.
     * 
     * @return The total number of incoming lanes from all carriageways.
     */
    public int getTotalNumIncomingLanes() {
        int totalIncomingLanes = 0;
        for (Carriageway cw : carriageways) {
            totalIncomingLanes += cw.getNumIncomingLanes();
        }
        return totalIncomingLanes;
    }

    /**
     * Getter for total number of outgoing lanes across all carriageways.
     * 
     * @return The total number of outgoing lanes from all carriageways.
     */
    public int getTotalNumOutgoingLanes() {
        int totalOutgoingLanes = 0;
        for (Carriageway cw : carriageways) {
            totalOutgoingLanes += cw.getNumOutgoingLanes();
        }
        return totalOutgoingLanes;
    }

    /**
     * Getter for the incoming flow for a specific direction.
     * 
     * @param direction The cardinal direction (e.g., NORTH, SOUTH) for which the incoming flow is requested.
     * @return The incoming flow for the specified direction.
     */
    public int getIncomingFlow(Direction direction) {
        Carriageway cw = getCarriageway(direction);
        return cw.getIncomingFlow();
    }

    /**
     * Getter for the outgoing flow for a specific direction.
     * 
     * @param direction The cardinal direction (e.g., NORTH, SOUTH) for which the outgoing flow is requested.
     * @return The outgoing flow towards the specified direction.
     * @throws InvalidDirectionException Thrown if the provided direction is invalid for the given Carriageway.
     */
    public int getOutgoingFlow(Direction direction) throws InvalidDirectionException {
        Carriageway cw = getCarriageway(direction);
        return cw.getOutgoingFlow(direction);
    }

    /**
     * Getter for the total number of lanes (incoming + outgoing) for a specific direction.
     * 
     * @param direction The direction for which the total number of lanes is requested.
     * @return The total number of lanes for the specified direction.
     */
    public int getNumLanes(Direction direction) {
        Carriageway cw = getCarriageway(direction);
        return cw.getNumIncomingLanes() + cw.getNumOutgoingLanes();
    }

    public int getMaxGroupTimings(){
        return groups.getMaxGroupTiming();
    }

    public boolean getOptimising(){
        return groups.getOptimising();
    }

    @Override
    public Iterator<Carriageway> iterator() {
        return Arrays.stream(carriageways).iterator();
    }
}





   

