package uk.ac.warwick.dcs.dataproc;

import java.util.List;

import org.javatuples.Pair;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.Loader;
import uk.ac.warwick.dcs.dataproc.saving.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

/**
 * Concrete implementation of <code>IDataService</code> interface.
 */
class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;
    private final IModelContainer modelContainer;
    private final ILoaderService<ConfigurationData> formLoaderService;
    private Saver saver;
    private Loader loader;


    public DataService(IValidator<JunctionConfiguration> validator, IModelContainer modelContainer, ILoaderService<ConfigurationData> formLoaderService) {
        this.validator = validator;
        this.modelContainer = modelContainer;
        this.formLoaderService = formLoaderService;
    }

    @Override
    public List<String> submitEnteredConfiguration(ConfigurationData configData, String configName) {
        Pair<JunctionConfiguration, List<String>> loadResult = formLoaderService.load(configData);
        JunctionConfiguration junctionConfig = loadResult.getValue0();
        List<String> errors = loadResult.getValue1();

        //save to a file containing JunctionConfiguration
        saver = new Saver(validator);
        saver.save(junctionConfig,configName);

        

        if (errors != null) {
            assert junctionConfig == null;
            return errors;
        } else {
            errors = validator.validate(junctionConfig);
            assert errors != null;

            // if errors are found, return them before advancing
            if (!errors.isEmpty()) {
                //keep getting errors so switched to the end
                return errors;
            }
        }

        // TODO: create a model instance asynchronously
        modelContainer.addModel(junctionConfig);

        // if no errors, return empty list
        return List.of();
    }

    @Override
    public List<String> submitFileConfiguration(String filePath) {
        loader = new Loader();
        JunctionConfiguration junctionConfig = loader.LoadFile(filePath);
        
        // TODO: Use error handling from FormLoaderService.load to get errors list
        
        // TODO: create a model instance asynchronously
        modelContainer.addModel(junctionConfig);

        // if no errors, return empty list
        return List.of();
    }
}
