package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public interface IDiagnosticFactory {
    String createFieldErrorMessage(String field, String error);
    String createInvalidFlowSumMessage(Direction direction, int incomingFlow, int[] outgoingFlows);
    String createInvalidPermittedDirectionsMessage(Direction direction, int laneNum);
    String createInvalidNumLanesMessage(Direction direction, String type);
    String createOutgoingFlowErrorMessage(Direction direction, boolean[] validDirections);
}
