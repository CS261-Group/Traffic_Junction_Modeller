package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public interface ILoader {
    JunctionConfiguration load();
    String getLoadErrors();
}
