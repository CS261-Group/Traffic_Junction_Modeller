package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FormLoader;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

import java.util.ArrayList;
import java.util.List;

class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;

    public DataService(IValidator<JunctionConfiguration> validator) {
        this.validator = validator;
    }

    @Override
    public List<String> submitEnteredConfiguration(ConfigurationData configData) {
        FormLoader formLoader = new FormLoader(configData);
        JunctionConfiguration junctionConfig = formLoader.load();

        List<String> errors;
        if (junctionConfig == null) {
            errors = new ArrayList<>(1);
            errors.add(formLoader.getLoadErrors());
        } else {
            errors = validator.validate(junctionConfig);
            assert errors != null;
        }

        // TODO: create a model instance

        return errors;
    }

    @Override
    public List<String> submitFileConfiguration(String filePath) {
        // TODO: implement
        return null;
    }
}
