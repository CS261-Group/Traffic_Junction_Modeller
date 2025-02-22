package uk.ac.warwick.dcs.model;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public interface IModelFactory {
    Model createModel(JunctionConfiguration junctionConfiguration);
}
