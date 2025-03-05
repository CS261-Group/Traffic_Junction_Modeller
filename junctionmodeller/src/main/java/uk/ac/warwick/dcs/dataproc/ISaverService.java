package uk.ac.warwick.dcs.dataproc;
import java.util.List;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
public interface ISaverService {
    public List<String> save(JunctionConfiguration junctionConfig, String configName);
}
