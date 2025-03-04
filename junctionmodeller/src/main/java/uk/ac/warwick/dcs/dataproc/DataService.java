package uk.ac.warwick.dcs.dataproc;

import java.util.List;

import org.javatuples.Pair;

import javafx.application.Platform;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FileLoader;
import uk.ac.warwick.dcs.dataproc.saving.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.visualisation.IVisualisationFactory;
import uk.ac.warwick.dcs.visualisation.ModelVisualisation;
import uk.ac.warwick.dcs.visualisation.Visualiser;

/**
 * Concrete implementation of <code>IDataService</code> interface.
 */
class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;
    private final IValidator<String> configNameValidator;
    private final IModelContainer modelContainer;
    private final ILoaderService<ConfigurationData> formLoaderService;
    private final IVisualisationFactory visualisationFactory;
    private Saver saver;
    private FileLoader loader;

    public DataService(IValidator<JunctionConfiguration> validator,IValidator<String> configNameValidator, IModelContainer modelContainer, ILoaderService<ConfigurationData> formLoaderService, IVisualisationFactory visualisationFactory) {
        this.validator = validator;
        this.modelContainer = modelContainer;
        this.formLoaderService = formLoaderService;
        this.visualisationFactory = visualisationFactory;
        this.configNameValidator = configNameValidator;
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
            errors.addAll(configNameValidator.validate(configData.configName()));
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
        ModelVisualisation modelVisualisation;
        if (configData.showVisualisation()) {
            modelVisualisation = visualisationFactory.createVisualisation(configData.configName(), junctionConfig);

            // we wrap this action in Platform.runLater to avoid an issue with the threads
            // that Swing and JavaFX are running in, as they are 2 full scale 'main' threads.
            Platform.runLater(() -> {
                Visualiser.getInstance().addModelVisualisation(modelVisualisation);
            });
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
        ModelVisualisation modelVisualisation;
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
