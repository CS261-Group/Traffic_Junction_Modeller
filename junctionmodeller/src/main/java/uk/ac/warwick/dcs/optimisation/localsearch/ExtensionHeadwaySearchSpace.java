package uk.ac.warwick.dcs.optimisation.localsearch;

import org.javatuples.Pair;
import uk.ac.warwick.dcs.evaluation.junctiondata.ActuatedGroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;


import java.util.Iterator;
import java.util.Random;

public class ExtensionHeadwaySearchSpace implements ISearchSpace{
    private final double INITIAL_STEP_SIZE = 0.1; // in seconds
    private final double MAX_VALUE = 5;
    private final double MIN_VALUE = 1;
    private double[] stepSize;
    private Pair<Integer, Double> lastExtensionTime;
    private Random randomNumGen;
    private int numGroups;

    public ExtensionHeadwaySearchSpace(int numGroups) {
        this.numGroups = numGroups;
        stepSize = new double[numGroups];
        for (int i = 0; i < numGroups; i++) {
            stepSize[i] = INITIAL_STEP_SIZE;
        }
        randomNumGen = new Random();
        lastExtensionTime = Pair.with(-1, 0.0);
    }

    public boolean goToRandomNeighbour(JunctionData junctionData){
        double newExtensionValue = 0;
        int r1 = randomNumGen.nextInt(numGroups);

        //lastExtensionTime = junctionData.getExtensions();

        do{
            r1 = randomNumGen.nextInt(numGroups);
        }
        // cannot be lower than the minimum or higher than the maximum
        while (newExtensionValue < MIN_VALUE || newExtensionValue > MAX_VALUE);

        //junctionData.setCycleTime(newCycleValue);
        return true;
    }

    public boolean goBackToPreviousPosition(JunctionData junctionData){
        //if (lastCycleTime == -1){
        //    return false;
        //}

        //junctionData.setCycleTime(lastCycleTime);
        //lastCycleTime = -1;

        return true;
    }
}
