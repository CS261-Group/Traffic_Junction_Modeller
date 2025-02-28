package uk.ac.warwick.dcs.model;

/**
 * Concrete implementation of <code>IIdGenerationService</code>.
 */
class IdGenerationService implements IIdGenerationService {
    private long currentId; // track IDs

    public IdGenerationService() {
        currentId = 0;
    }

    @Override
    public long generateId() {
        return currentId++;
    }
}
