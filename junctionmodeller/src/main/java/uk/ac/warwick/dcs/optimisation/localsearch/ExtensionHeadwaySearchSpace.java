package uk.ac.warwick.dcs.optimisation.localsearch;

import org.javatuples.Pair;
import uk.ac.warwick.dcs.evaluation.junctiondata.ActuatedGroupData;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;
import uk.ac.warwick.dcs.optimisation.noniterative.ActuatedCycleAndGreenTimeInitialiser;


import java.util.Random;

public class ExtensionHeadwaySearchSpace implements ISearchSpace{
    private final double INITIAL_STEP_SIZE = 0.1; // in seconds
    private double[] stepSize;
    private Pair<Integer, Double> lastExtensionTime; //group num, followed by value
    private final Random randomNumGen;
    private final int numGroups;
    private final double junctionCycleLostTime;

    public ExtensionHeadwaySearchSpace(double junctionCycleLostTime, int numGroups) {
        this.numGroups = numGroups;
        this.junctionCycleLostTime = junctionCycleLostTime;
        stepSize = new double[numGroups];
        for (int i = 0; i < numGroups; i++) {
            stepSize[i] = INITIAL_STEP_SIZE;
        }
        randomNumGen = new Random();
        lastExtensionTime = Pair.with(-1, 0.0);
    }

    public boolean goToRandomNeighbour(JunctionData junctionData){
        double MAX_VALUE = ActuatedGroupData.MAX_EH;
        double MIN_VALUE = ActuatedGroupData.MIN_EH;
        double newExtensionValue;
        int r1;

        // newExtensionValue cannot be lower than the minimum or higher than the maximum
        do{
            r1 = randomNumGen.nextInt(numGroups);
            if (randomNumGen.nextBoolean()){
                newExtensionValue = junctionData.getGroupExtensionTime(r1) + stepSize[r1];
            } else{
                newExtensionValue = junctionData.getGroupExtensionTime(r1) - stepSize[r1];
            }
        } while (newExtensionValue < MIN_VALUE || newExtensionValue > MAX_VALUE);

        lastExtensionTime = Pair.with(r1, junctionData.getGroupExtensionTime(r1));

        junctionData.setExtensionHeadway(r1, newExtensionValue);
        // need to re-initialise values after each extension headway change
        new ActuatedCycleAndGreenTimeInitialiser(junctionData, junctionCycleLostTime);

        return true;
    }

    public boolean goBackToPreviousPosition(JunctionData junctionData){
        if (lastExtensionTime.getValue0() == -1){
            return false;
        }
        junctionData.setExtensionHeadway(lastExtensionTime.getValue0(), lastExtensionTime.getValue1());
        lastExtensionTime = Pair.with(-1, 0.0);
        return true;
    }
}
