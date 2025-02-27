package uk.ac.warwick.dcs.dataproc;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.javatuples.Pair;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FileLoader;
import uk.ac.warwick.dcs.dataproc.saving.Saver;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;
import uk.ac.warwick.dcs.model.Model;

/**
 * Concrete implementation of <code>IDataService</code> interface.
 */
class DataService implements IDataService {
    private final IValidator<JunctionConfiguration> validator;
    private final IModelContainer modelContainer;
    private final ILoaderService<ConfigurationData> formLoaderService;
    private Saver saver;
    private FileLoader loader;

    // ExecutorService for asynchronous task execution
    private final ExecutorService executorService;

    public DataService(IValidator<JunctionConfiguration> validator, IModelContainer modelContainer, ILoaderService<ConfigurationData> formLoaderService) {
        this.validator = validator;
        this.modelContainer = modelContainer;
        this.formLoaderService = formLoaderService;
        this.executorService = Executors.newCachedThreadPool();  // Use a cached thread pool for dynamic task execution
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

        // Create a model instance asynchronously (hard-coded infinite loop inside the model instance)
        createModel(junctionConfig);

        // If no errors, return an empty list
        return List.of();
    }

    @Override
    public List<String> submitFileConfiguration(String filePath) {
        loader = new FileLoader(filePath);
        JunctionConfiguration junctionConfig = loader.load();
        
        // Handle errors using the error handling logic from the form loader service
        // TODO: Implement the error handling from FormLoaderService.load to get an error list

        // Create a model instance asynchronously (hard-coded infinite loop inside the model instance)
        createModel(junctionConfig);

        // If no errors, return an empty list
        return List.of();
    }

    /**
     * Creates a model instance asynchronously. 
     *
     * @param junctionConfig The junction configuration to be passed to the model
     */
    private void createModel(JunctionConfiguration junctionConfig) {
        executorService.submit(() -> {
            Model model = new Model();

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Model creation interrupted.");
                    break;  
                }
            }
            modelContainer.addModel(junctionConfig);
        });
    }
}
