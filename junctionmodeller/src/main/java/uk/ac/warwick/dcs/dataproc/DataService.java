package uk.ac.warwick.dcs.dataproc;

import java.util.List;

import org.javatuples.Pair;

import javafx.application.Platform;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.model.exceptions.NoSuchModelException;
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
    private final ILoaderService<String> fileLoaderService;
    private final ISaverService fileSaverService;
    
    private final IVisualisationFactory visualisationFactory;

    public DataService(IValidator<JunctionConfiguration> validator,IValidator<String> configNameValidator, IModelContainer modelContainer, ILoaderService<ConfigurationData> formLoaderService, ILoaderService<String> fileLoaderService, ISaverService fileSaverService, IVisualisationFactory visualisationFactory) {
        this.validator = validator;
        this.modelContainer = modelContainer;
        this.formLoaderService = formLoaderService;
        this.fileLoaderService = fileLoaderService;
        this.fileSaverService = fileSaverService;
        this.visualisationFactory = visualisationFactory;
        this.configNameValidator = configNameValidator;
    }

    @Override
    public List<String> submitEnteredConfiguration(ConfigurationData configData) {
        Pair<JunctionConfiguration, List<String>> loadResult = formLoaderService.load(configData);
        JunctionConfiguration junctionConfig = loadResult.getValue0();
        List<String> errors = loadResult.getValue1();

        // errors are present
        if (errors != null) {
            assert junctionConfig == null;
            return errors;
        }

        errors = validator.validate(junctionConfig);
        errors.addAll(configNameValidator.validate(configData.configName()));

        // If errors are found, return them before advancing
        if (!errors.isEmpty()) {
            return errors;
        }

        // Save to a file containing JunctionConfiguration
        errors = fileSaverService.save(junctionConfig,configData.configName());

        if (errors != null) {
            assert junctionConfig == null;
            return errors;
        }

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
        boolean success = modelContainer.addModel(configData.configName(), junctionConfig, modelVisualisation);
        if (!success) {
            return List.of("Couldn't run model, maybe reached maximum number of concurrently running models.");
        }

        // If no errors, return an empty list
        return List.of();
    }
    public String getConfigName(String filePath){
        int start = filePath.lastIndexOf("/")+1;
        int end = filePath.lastIndexOf(".json");
        if (start > 0 && end > start) {
            return filePath.substring(start, end);
        } else {
            return ""; // Return empty if not found
        }
    }

    @Override
    public List<String> submitFileConfiguration(String filePath, boolean showVisualisation) {
        Pair<JunctionConfiguration, List<String>> loadResult  = fileLoaderService.load(filePath);
        JunctionConfiguration junctionConfig = loadResult.getValue0();
        
        // Handle errors using the error handling logic from the form loader service
        // Implemented the error handling from FormLoaderService.load to get an error list
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
        // Create a model instance asynchronously
        // Create a model instance asynchronously
        ModelVisualisation modelVisualisation;
        if (showVisualisation) {
            modelVisualisation = visualisationFactory.createVisualisation(getConfigName(filePath), junctionConfig);
        } else {
            modelVisualisation = null;
        }
        boolean success = modelContainer.addModel(getConfigName(filePath), junctionConfig, modelVisualisation);
        if (!success) {
            return List.of("Couldn't run model, maybe reached maximum number of concurrently running models.");
        }

        // If no errors, return an empty list
        return List.of();
    }

    @Override
    public boolean deleteConfiguration(String configName) {
        try {
            modelContainer.deleteConfiguration(configName);
            return true;
        } catch (NoSuchModelException ex) {
            return false;
        }
    }
}
