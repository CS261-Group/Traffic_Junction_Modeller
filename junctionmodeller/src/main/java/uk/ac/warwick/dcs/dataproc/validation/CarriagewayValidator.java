package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;

import java.util.LinkedList;
import java.util.List;

public class CarriagewayValidator implements IValidator<Carriageway> {
    private final IDiagnosticFactory diagFactory;

    public CarriagewayValidator(IDiagnosticFactory diagnosticFactory) {
        diagFactory = diagnosticFactory;
    }

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

    private int getDirectionMask(Carriageway carriageway, int laneNum) {
        int mask = 0b000;

        Direction origin, left, forward, right;

        // initially set everything to origin direction
        origin = left = right = forward = carriageway.getDirection();
        switch (origin) {
            case NORTH:
                left = Direction.WEST;
                forward = Direction.SOUTH;
                right = Direction.EAST;
                break;
            case EAST:
                left = Direction.SOUTH;
                forward = Direction.WEST;
                right = Direction.NORTH;
                break;
            case SOUTH:
                left = Direction.EAST;
                forward = Direction.NORTH;
                right = Direction.WEST;
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

    private List<String> validateDirections(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();

        /*
         * There is an efficient way to check for the order of lane directions.
         * We will use a binary system where permitting left (changes depending on
         * orientation) is 0b100, forward is 0b010, right is 0b001, and masking
         * the directions permitted.
         * This way, we can simply assert that as the laneNum increases (we go from
         * leftmost lane to rightmost lane) the masks must be in a non-increasing
         * sequence.
         */
        int prev = 0b111; // maximum mask
        for (int laneNum = 1; laneNum <= carriageway.getNumIncomingLanes(); laneNum++) {
            int mask = getDirectionMask(carriageway, laneNum);

            // no longer a non-increasing sequence
            if (mask > prev) {
                errors.add(diagFactory.createInvalidPermittedDirectionsMessage(carriageway.getDirection(), laneNum));
            }

            // move prev along
            prev = mask;
        }

        return errors;
    }

    private List<String> validateOutgoingFlowExitExistence(Carriageway carriageway) {
        List<String> errors = new LinkedList<>();

        // directions which are valid
        boolean[] validDirections = new boolean[4];
        try {
            // naturally we ignore the incoming direction and 0 flow outgoing directions
            for (Direction direction : Direction.values()) {
                if (direction == carriageway.getDirection() || carriageway.getOutgoingFlow(direction) != 0) {
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

    @Override
    public List<String> validate(Carriageway carriageway) {
        List<String> errors = validateNumLanes(carriageway);
        errors.addAll(validateFlows(carriageway));
        errors.addAll(validateDirections(carriageway));
        errors.addAll(validateOutgoingFlowExitExistence(carriageway));
        return errors;
    }
}
