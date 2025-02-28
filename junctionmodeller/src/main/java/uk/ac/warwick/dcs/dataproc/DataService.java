package uk.ac.warwick.dcs.dataproc;

import java.util.List;

import org.javatuples.Pair;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FileLoader;
import uk.ac.warwick.dcs.dataproc.saving.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;
import uk.ac.warwick.dcs.visualisation.IVisualisationFactory;

/**
 * Concrete implementation of <code>IDataService</code> interface.
 */
class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;
    private final IModelContainer modelContainer;
    private final ILoaderService<ConfigurationData> formLoaderService;
    private final IVisualisationFactory visualisationFactory;
    private Saver saver;
    private FileLoader loader;

    public DataService(IValidator<JunctionConfiguration> validator, IModelContainer modelContainer, ILoaderService<ConfigurationData> formLoaderService, IVisualisationFactory visualisationFactory) {
        this.validator = validator;
        this.modelContainer = modelContainer;
        this.formLoaderService = formLoaderService;
        this.visualisationFactory = visualisationFactory;
    }

    @Override
    public List<String> submitEnteredConfiguration(ConfigurationData configData) {
        Pair<JunctionConfiguration, List<String>> loadResult = formLoaderService.load(configData);
        JunctionConfiguration junctionConfig = loadResult.getValue0();
        List<String> errors = loadResult.getValue1();

        if (errors != null) {
            assert junctionConfig == null;
            return errors;
        } else {
            errors = validator.validate(junctionConfig);
            assert errors != null;

            // If errors are found, return them before advancing
            if (!errors.isEmpty()) {
                return errors;
            }
        }

        // Save to a file containing JunctionConfiguration
        saver = new Saver(validator);
        saver.save(junctionConfig, configData.configName());

        // Create a model instance asynchronously
        IModelVisualisation modelVisualisation;
        if (configData.showVisualisation()) {
            modelVisualisation = visualisationFactory.createVisualisation(configData.configName(), junctionConfig);
        } else {
            modelVisualisation = null;
        }
        boolean success = modelContainer.addModel(junctionConfig, modelVisualisation);
        if (!success) {
            return List.of("Couldn't run model, maybe reached maximum number of concurrently running models.");
        }

        // If no errors, return an empty list
        return List.of();
    }

    @Override
    public List<String> submitFileConfiguration(String filePath, boolean showVisualisation) {
        loader = new FileLoader(filePath);
        JunctionConfiguration junctionConfig = loader.load();
        
        // Handle errors using the error handling logic from the form loader service
        // TODO: Implement the error handling from FormLoaderService.load to get an error list

        // Create a model instance asynchronously
        // Create a model instance asynchronously
        IModelVisualisation modelVisualisation;
        if (showVisualisation) {
            // TODO: get config name from saved file
            modelVisualisation = visualisationFactory.createVisualisation("configname", junctionConfig);
        } else {
            modelVisualisation = null;
        }
        boolean success = modelContainer.addModel(junctionConfig, modelVisualisation);
        if (!success) {
            return List.of("Couldn't run model, maybe reached maximum number of concurrently running models.");
        }

        // If no errors, return an empty list
        return List.of();
    }
}
