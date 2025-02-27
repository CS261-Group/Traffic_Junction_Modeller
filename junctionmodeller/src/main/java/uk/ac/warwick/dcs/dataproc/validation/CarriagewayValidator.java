package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.enums.VehicleType;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.*;

/**
 * Validator for <code>Carriageway</code> objects that checks for validity of:
 * - incoming and outgoing flow values (must add up)
 * - order of lane directions (e.g., can't have right-only lane
 *   followed by left-only lane to its right)
 * - between the incoming lanes, at least one must permit an exit direction
 *   with a flow greater than 0
 */
public class CarriagewayValidator extends Validator<Carriageway> {
    public CarriagewayValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    /**
     * Number of incoming and outgoing lanes must be positive (>0).
     * Since the <code>Carriageway</code> uses an object under-the-hood
     * this is a somewhat unnecessary assertion to make.
     * @param carriageway Carriageway object to validate
     * @return List of errors.
     */
    private List<String> validateNumLanes(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();
        if (carriageway.getNumIncomingLanes() <= 0) {
            errors.add(diagFactory.createInvalidNumLanesMessage(carriageway.getDirection(), "incoming"));
        }
        if (carriageway.getNumOutgoingLanes() <= 0) {
            errors.add(diagFactory.createInvalidNumLanesMessage(carriageway.getDirection(), "outgoing"));
        }

        return errors;
    }

    /**
     * Sum of outgoing flows must add up to incoming flow.
     * @param carriageway Carriage object to validate.
     * @return List of errors.
     */
    private List<String> validateFlows(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();
        int flowSum = 0;
        int incomingFlow = carriageway.getIncomingFlow();
        int[] outgoingFlows = new int[4];

        try {
            for (Direction direction : Direction.values()) {
                if (direction != carriageway.getDirection()) {
                    outgoingFlows[direction.ordinal()] = carriageway.getOutgoingFlow(direction);
                    flowSum += carriageway.getOutgoingFlow(direction);
                }
            }
        } catch (InvalidDirectionException ex) {
            // this can't happen with our logic
            // since we skip
            assert false;
        }

        if (flowSum != incomingFlow) {
            errors.add(diagFactory.createInvalidFlowSumMessage(carriageway.getDirection(), incomingFlow, outgoingFlows));
        }

        return errors;
    }

    /**
     * Generate a bit mask for each lane based on its permitted exit
     * directions to make checking for the order of lanes by their
     * permitted exit directions easier.
     * @param carriageway The carriageway object to validate.
     * @param laneNum The number of the lane from the left. (leftmost
     *                lane has <code>laneNum=1</code>)
     * @return The bit mask of the lane.
     */
    private int getDirectionMask(Carriageway carriageway, int laneNum) {
        int mask = 0b000;

        Direction origin, left, forward, right;

        // initially set everything to origin direction
        origin = left = right = forward = carriageway.getDirection();
        switch (origin) {
            case NORTH:
                left = Direction.EAST;
                forward = Direction.SOUTH;
                right = Direction.WEST;
                break;
            case EAST:
                left = Direction.SOUTH;
                forward = Direction.WEST;
                right = Direction.NORTH;
                break;
            case SOUTH:
                left = Direction.WEST;
                forward = Direction.NORTH;
                right = Direction.EAST;
                break;
            case WEST:
                left = Direction.NORTH;
                forward = Direction.EAST;
                right = Direction.SOUTH;
                break;
            default:
                assert false;
        }
        // sanity check: must have assigned something to something else
        assert origin != left && origin != forward && origin != right;

        if (carriageway.getLaneAllowsDirection(laneNum, left)) {
            mask |= 0b100;
        }
        if (carriageway.getLaneAllowsDirection(laneNum, forward)) {
            mask |= 0b010;
        }
        if (carriageway.getLaneAllowsDirection(laneNum, right)) {
            mask |= 0b001;
        }

        // sanity check: valid masks for valid directions
        assert mask > 0 && mask <= 0b111;
        return mask;
    }

    /**
     * Map used to act as the permission relation for checking lane orders.
     * Excluding 0b000 (no available turns) and 0b101 (left + right only),
     * this is the permission Hasse diagram:
     *         100 (Left Only)
     *               ↓
     *         110 (Left + Forward)
     *               ↓
     *         111 (All directions)
     *               ↓
     *         010 (Forward Only)
     *               ↓
     *         011 (Forward + Right)
     *               ↓
     *         001 (Right only)
     * Nothing permits 0b000 (none) and nothing permits 0b101 (left-right).
     * Moreover, 0b000 (none) and 0b101 (left-right) permit no other configurations.
     */
    private final static Map<Integer, Set<Integer>> permissions = new HashMap<>() {{
        put(0b100, new HashSet<>(List.of(0b100, 0b110, 0b111, 0b010, 0b011, 0b001)));
        put(0b110, new HashSet<>(List.of(0b110, 0b111, 0b010, 0b011, 0b001)));
        put(0b111, new HashSet<>(List.of(0b111, 0b010, 0b011, 0b001)));
        put(0b010, new HashSet<>(List.of(0b010, 0b011, 0b001)));
        put(0b011, new HashSet<>(List.of(0b011, 0b001)));
        put(0b001, new HashSet<>(List.of(0b001)));
        put(0b101, new HashSet<>());
        put(0b000, new HashSet<>());
    }};

    /**
     * Ensure valid order of lane directions (e.g., can't have right-only lane
     * followed by left-only lane to its right).
     * @param carriageway Carriageway object to validate.
     * @return List of errors.
     */
    private List<String> validateDirections(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();

        int prev = 0b100; // left-only is maximally permissive
        for (int laneNum = 1; laneNum <= carriageway.getNumIncomingLanes(); laneNum++) {
            int mask = getDirectionMask(carriageway, laneNum);

            // if previous lane doesn't permit next, there's been an error
            assert permissions.containsKey(mask);
            if (!permissions.get(prev).contains(mask)) {
                errors.add(diagFactory.createInvalidPermittedDirectionsMessage(carriageway.getDirection(), laneNum));
            }

            // move prev along
            prev = mask;
        }

        return errors;
    }

    /**
     * Ensure some incoming lane permits an exit direction if the corresponding
     * outgoing flow is non-zero.
     * @param carriageway Carriageway object to validate.
     * @return List of errors.
     */
    private List<String> validateOutgoingFlowExitExistence(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();

        // directions which are valid
        boolean[] validDirections = new boolean[4];
        try {
            // naturally we ignore the incoming direction and 0 flow outgoing directions
            for (Direction direction : Direction.values()) {
                if (direction == carriageway.getDirection() || carriageway.getOutgoingFlow(direction) == 0) {
                    validDirections[direction.ordinal()] = true;
                }
            }
        } catch (InvalidDirectionException ex) {
            assert false; // this should never happen from the logic of the if statement
        }

        for (int laneNum = 1; laneNum <= carriageway.getNumIncomingLanes(); laneNum++) {
            for (Direction direction : Direction.values()) {
                if (direction != carriageway.getDirection()) {
                    if (carriageway.getLaneAllowsDirection(laneNum, direction)) {
                        validDirections[direction.ordinal()] = true;
                    }
                }
            }
        }

        if (!validDirections[0] || !validDirections[1] || !validDirections[2] || !validDirections[3]) {
            errors.add(diagFactory.createOutgoingFlowErrorMessage(carriageway.getDirection(), validDirections));
        }

        return errors;
    }

    /**
     * Ensure that ifa bus lane is configured, then one of the incoming
     * lanes must permit bus vehicle types.
     * @param carriageway Carriageway object to validate.
     * @return List of errors.
     */
    private List<String> validateBusLane(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();

        if (carriageway.isBusLane()) {
            boolean foundBusLane = false;
            for (int laneNum = 1; laneNum <= carriageway.getNumIncomingLanes(); laneNum++) {
                if (carriageway.getIncomingLane(laneNum).getVehicleType() == VehicleType.BUS) {
                    foundBusLane = true;
                    break;
                }
            }
            if (!foundBusLane) {
                errors.add(diagFactory.createNoBusLaneMessage(carriageway.getDirection()));
            }
        }

        return errors;
    }

    @Override
    public List<String> validate(Carriageway carriageway) {
        List<String> errors = validateNumLanes(carriageway);
        errors.addAll(validateFlows(carriageway));
        errors.addAll(validateDirections(carriageway));
        errors.addAll(validateOutgoingFlowExitExistence(carriageway));
        errors.addAll(validateBusLane(carriageway));
        return errors;
    }
}
