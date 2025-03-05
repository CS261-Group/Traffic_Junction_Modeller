package uk.ac.warwick.dcs.dataproc;
import java.util.List;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.saving.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;

class FileSaverService implements ISaverService{
    @Override
    public List<String> save(IValidator validator, JunctionConfiguration junctionConfig, String configName){
        Saver saver = new Saver(validator);
        saver.save(junctionConfig, configName);
        return List.of(saver.getSaveErrors());
    }
}
