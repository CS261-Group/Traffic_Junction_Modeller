package uk.ac.warwick.dcs.dataproc;
import java.util.List;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.saving.Saver;

class FileSaverService implements ISaverService{
    @Override
    public List<String> save(JunctionConfiguration junctionConfig, String configName){
        Saver saver = new Saver();
        saver.save(junctionConfig, configName);
        if (saver.getSaveErrors() != null) {
            return List.of(saver.getSaveErrors());
        } else {
            return null;
        }
    }
}
