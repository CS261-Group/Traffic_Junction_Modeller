package uk.ac.warwick.dcs.dataproc.saving;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public interface ISaver {
    void save(JunctionConfiguration junctionConfig,String configName);
}
