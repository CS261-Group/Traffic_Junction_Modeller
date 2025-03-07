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
    private final Map<Long, Future<?>> runningModels;
    private final IModelFactory modelFactory;
    private final ExecutorService executorService;

    public ModelContainer(IModelFactory modelFactory) {
        this.models = new HashMap<>();
        this.modelNames = new HashMap<>();
        this.runningModels = new HashMap<>();
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
        Future<?> modelFuture = executorService.submit(model);

        models.put(model.getId(), model);
        modelNames.put(configName, model.getId());
        runningModels.put(model.getId(), modelFuture);


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

        // model must exist at this point
        assert model != null;

        // stop the model running
        model.stop();

        // interrupt the running thread
        Future<?> modelFuture = runningModels.get(model.getId());
        modelFuture.cancel(true);
    }
}
