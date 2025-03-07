package uk.ac.warwick.dcs.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.evaluation.junctionmetrics.JunctionMetrics;
import uk.ac.warwick.dcs.model.exceptions.NoSuchModelException;
import uk.ac.warwick.dcs.visualisation.IModelVisualisation;

import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;

class ModelContainer implements IModelContainer {
    /**
     * Maximum number of models we can support concurrently.
     * i.e., the number of threads in the thread pool.
     */
    private static final int MAX_CONCURRENT_MODELS = 8;

    private final Map<String, Long> modelNames;
    private final Map<Long, Model> models;
    private final IModelFactory modelFactory;
    private final ExecutorService executorService;

    public ModelContainer(IModelFactory modelFactory) {
        this.models = new HashMap<>();
        this.modelNames = new HashMap<>();
        this.modelFactory = modelFactory;
        this.executorService = Executors.newFixedThreadPool(MAX_CONCURRENT_MODELS);  // Initial thread size, will be updated dynamically
    }

    @Override
    public boolean addModel(String configName, JunctionConfiguration junctionConfiguration, IModelVisualisation visualisation) {
        assert !executorService.isShutdown();
        assert !executorService.isTerminated();

        // we can't have more concurrently running threads
        if (models.size() == MAX_CONCURRENT_MODELS) {
            return false; // failure
        }

        Model model = modelFactory.createModel(junctionConfiguration, visualisation);
        models.put(model.getId(), model);
        modelNames.put(configName, model.getId());

        executorService.submit(model);

        return true; // successfully created
    }

    public JunctionMetrics evaluateModel(long modelId) throws NoSuchModelException {
        Model model = models.get(modelId);
        if (model == null) {
            throw new NoSuchModelException(modelId);
        }

        return model.evaluateModel();
    }

    @Override
    public void deleteConfiguration(String configName) throws NoSuchModelException {
        if (!modelNames.containsKey(configName)) {
            throw new NoSuchModelException(configName);
        }

        // extract running model from set of models
        Model model = models.remove(modelNames.get(configName));
        modelNames.remove(configName);

        // stop the model running
        model.stop();
    }

    /**
     * Wait for all models to finish evaluation and return the evaluated models.
     * 
     * @param futures Set of Future objects for the models that are being evaluated
     * @return A set of evaluated models
     */
    public Set<Model> getEvaluatedModels(Set<Future<Model>> futures) {
        Set<Model> evaluatedModels = new HashSet<>();
        
        for (Future<Model> future : futures) {
            try {
                Model evaluatedModel = future.get();  // This will block until the model is evaluated
                evaluatedModels.add(evaluatedModel);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        return evaluatedModels;
    }

    /**
     * Wait for all models to finish optimisation and return the optimised models.
     * 
     * @param futures Set of Future objects for the models that are being optimised
     * @return A set of optimised models
     */
    public Set<Model> getOptimisedModels(Set<Future<Model>> futures) {
        Set<Model> optimisedModels = new HashSet<>();
        
        for (Future<Model> future : futures) {
            try {
                Model optimisedModel = future.get();  // This will block until the model is optimised
                optimisedModels.add(optimisedModel);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        return optimisedModels;
    }

    /**
     * Shut down the executor service after finishing all tasks.
     */
    public void shutdown() {
        executorService.shutdown();
    }
}
