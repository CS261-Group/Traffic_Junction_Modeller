package uk.ac.warwick.dcs.model;

/**
 * Interface for service used to generate Model IDs.
 */
public interface IIdGenerationService {
    /**
     *
     * @return A newly generated (unique) numeric ID.
     */
    long generateId();
}
