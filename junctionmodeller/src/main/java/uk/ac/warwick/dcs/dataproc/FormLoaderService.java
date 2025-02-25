package uk.ac.warwick.dcs.dataproc;

import org.javatuples.Pair;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FormLoader;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

import java.util.List;

public class FormLoaderService implements ILoaderService<ConfigurationData> {
    @Override
    public Pair<JunctionConfiguration, List<String>> load(ConfigurationData configData) {
        FormLoader formLoader = new FormLoader(configData);
        JunctionConfiguration junctionConfig = formLoader.load();

        String loadErrors = formLoader.getLoadErrors();
        if (formLoader.getLoadErrors() == null) {
            return new Pair<>(junctionConfig, null);
        } else {
            return new Pair<>(null, List.of(loadErrors));
        }
    }
}
