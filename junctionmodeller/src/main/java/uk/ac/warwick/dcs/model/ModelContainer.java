package uk.ac.warwick.dcs.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;

public class ModelContainer implements IModelContainer {
    private Set<Model> models;
    private final IModelFactory modelFactory;
    private ExecutorService executorService;

    public ModelContainer(IModelFactory modelFactory) {
        this.models = new HashSet<>();
        this.modelFactory = modelFactory;
        this.executorService = Executors.newFixedThreadPool(4);  // Initial thread size, will be updated dynamically
    }

    @Override
    public void addModel(JunctionConfiguration junctionConfiguration) {
        Model model = modelFactory.createModel(junctionConfiguration);
        models.add(model);
        adjustThreadPoolSize();  // Adjust thread pool size when a new model is added
    }

    /**
     * Dynamically adjusts the thread pool size based on the number of models.
     */
    private void adjustThreadPoolSize() {
        int modelCount = models.size();
        int poolSize = Math.max(1, modelCount);  // Ensure at least 1 thread.
        
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();  // Shutdown the old thread pool
        }
        
        // Reinitialize the executor service with the new pool size
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    /**
     * Evaluate all models concurrently and return a Future set of models.
     * 
     * @param junctionConfiguration The configuration for evaluation
     * @return A set of Future objects representing the models that are being evaluated
     */
    public Set<Future<Model>> evaluateModelsConcurrently(JunctionConfiguration junctionConfiguration) {
        Set<Future<Model>> futures = new HashSet<>();
        
        for (Model model : models) {
            Callable<Model> task = () -> {
                model.evaluateModel(junctionConfiguration);  // Evaluate the model
                return model;
            };
            Future<Model> future = executorService.submit(task);
            futures.add(future);
        }
        
        return futures;
    }

    /**
     * Optimise all models concurrently using the given junction configuration.
     * 
     * @param junctionConfiguration The configuration for the junction to be optimised
     * @return A set of Future objects representing the models that are being optimised
     */
    public Set<Future<Model>> optimiseModelsConcurrently(JunctionConfiguration junctionConfiguration) {
        Set<Future<Model>> futures = new HashSet<>();
        
        // Submit tasks for each model to optimize them concurrently
        for (Model model : models) {
            Callable<Model> task = () -> {
                model.optimiseModel(junctionConfiguration);  // Optimise the model (method to be implemented)
                return model;
            };
            Future<Model> future = executorService.submit(task);
            futures.add(future);
        }
        
        return futures;
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
