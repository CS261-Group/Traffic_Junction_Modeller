package uk.ac.warwick.dcs.dataproc.validation;

class DiagnosticFactory implements IDiagnosticFactory {
    @Override
    public String createErrorMessage(String field, String error) {
        return field + ": " + error;
    }
}
