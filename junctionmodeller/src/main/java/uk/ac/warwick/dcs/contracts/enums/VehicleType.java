package uk.ac.warwick.dcs.contracts.enums;

/**
 * Enum used to specify the types of vehicles:
 * - permitted by a lane
 * - spawned in by the model
 * NOTE: <code>CYCLE</code> type is deprecated since buses
 * and cycles are treated the same way.
 */
public enum VehicleType {
    CAR,
//    CYCLE,
    BUS
}
