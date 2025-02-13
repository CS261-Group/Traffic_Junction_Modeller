package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FormLoader;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import java.util.List;

public class DataService implements IDataService {
    private final IValidator validator;

    public DataService(IValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<String> submitEnteredConfiguration(ConfigurationData configData) {
        FormLoader formLoader = new FormLoader(configData);
        JunctionConfiguration junctionConfig = formLoader.load();

        List<String> errors = validator.validate(junctionConfig);
        assert errors != null;

        return errors;
    }

    @Override
    public List<String> submitFileConfiguration(String filePath) {
        // TODO: implement
        return null;
    }
}
