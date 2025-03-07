package uk.ac.warwick.dcs.contracts.structure;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
//overwrite equals method so it compares data values (every object)
//make incoming lane take in a lane number and store it for itself and make a getter for it
//TODO: change the group validator to use the .equals method instead of ==

//Questions

//where is GroupsValidator
//by every object in TODO you mean every variable in the IncomingLane class
//what do you mean by available lane numbers

public class IncomingLane extends Lane {
    private static final int MAX_INCOMING_LANES = 5;

    private final VehicleType vehicleType;
    private final boolean[] availableDirections;
    private final int laneNum;

    public IncomingLane(Direction d, VehicleType vt, boolean[] directions, int laneNumber) {
        super(d);
        getDirection();
        vehicleType = vt;
        availableDirections = directions;
        laneNum = laneNumber;
        assert directions.length == 4; // sanity check: one for each direction
        assert !directions[d.ordinal()]; // sanity check: going backwards can't be valid
    }

    /**
     *
     * @return The <code>VehicleType</code> of the vehicles this lane
     *         permits. If <code>CAR</code> type is permitted, <code>BUS</code>
     *         (and <code>CYCLE</code>) vehicle types are also permitted.
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    
    public int getLaneNum(){
        return laneNum;
    }
    /**
     *
     * @param direction The direction we check if the lane permits going.
     * @return True if the lane permits exiting from the given <code>direction</code>,
     *         false otherwise. If the specified <code>direction</code> matches
     *         the incoming direction of the carriageway this lane belongs to,
     *         false is returned.
     */
    public boolean allowsGoing(Direction direction) {
        return availableDirections[direction.ordinal()];
    }

    @Override
    public int hashCode() {
        assert 1 <= laneNum && laneNum <= MAX_INCOMING_LANES;

        // block out 3 bits for lane number
        int hash = laneNum & 0x7;

        // next 4 bits for available directions (N, E, S, W)
        for (boolean directionPermitted : availableDirections) {
            hash <<= 1;
            hash |= directionPermitted ? 1 : 0;
        }

        // next 2 bits is for ordinal direction
        hash <<= 2;
        hash |= getDirection().ordinal() & 0b11;

        // mask permitted vehicle types (which is just car for demo purposes)
        // so we just fill with a single 1
        for (VehicleType permittedVehicle : VehicleType.values()) {
            hash <<= 1;
            hash |= (getVehicleType() == permittedVehicle ? 1 : 0);
        }
        
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof IncomingLane)){
            return false;
        }
        IncomingLane other = (IncomingLane) o;
        //compare direction, lane number
        if (other.getDirection() != this.getDirection() || other.getLaneNum() != this.getLaneNum()) {
            return false;
        }

        assert availableDirections.length == 4;
        assert availableDirections.length == other.availableDirections.length;

        // check permitted directions match
        for (int i = 0; i < availableDirections.length; i++) {
            if (availableDirections[i] != other.availableDirections[i]) {
                return false;
            }
        }

        // everything must match
        return true;
        
    }
}
