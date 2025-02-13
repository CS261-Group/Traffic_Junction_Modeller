package uk.ac.warwick.dcs.dataproc.validation;

public interface IDiagnosticFactory {
    String createErrorMessage(String field, String error);
    // TODO: add more types of create function for ease of use in different cases
}
