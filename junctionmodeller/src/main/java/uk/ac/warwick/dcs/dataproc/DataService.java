package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FormLoader;
import uk.ac.warwick.dcs.dataproc.serialisation.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of <code>IDataService</code> interface.
 */
class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;
    private final IModelContainer modelContainer;

    public DataService(IValidator<JunctionConfiguration> validator, IModelContainer modelContainer) {
        this.validator = validator;
        this.modelContainer = modelContainer;
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

        // if errors are found, return them before advancing
        if (!errors.isEmpty()) {
            return errors;
        }

        // TODO: save to a file containing JunctionConfiguration
        Saver saver = new Saver();

        // TODO: create a model instance asynchronously
        modelContainer.addModel(junctionConfig);

        // if no errors, return empty list
        return List.of();
    }

    @Override
    public List<String> submitFileConfiguration(String filePath) {
        // TODO: implement
        return null;
    }
}
