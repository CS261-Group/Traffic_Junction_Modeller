package uk.ac.warwick.dcs.optimisation.localsearch;

import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.Random;

public class CycleTimeSearchSpace implements ISearchSpace{
    private final double INITIAL_STEP_SIZE = 0.01; // in seconds
    private final double maxCycleTime;
    private double stepSize;
    private double lastCycleTime;
    private Random randomNumGen;

    public CycleTimeSearchSpace(double cycleLostTime, double maxGreenTimes){
        //
        maxCycleTime = cycleLostTime + maxGreenTimes;
        stepSize = INITIAL_STEP_SIZE;
        randomNumGen = new Random();
        lastCycleTime = -1;
    }

    public boolean goToRandomNeighbour(JunctionData junctionData){
        double newCycleValue = - 1;
        lastCycleTime = junctionData.getCycleTime();

        // cannot be lower than the minimum or higher than the maximum
        while (newCycleValue < junctionData.MIN_CYCLE_TIME || newCycleValue > maxCycleTime){
            if (randomNumGen.nextBoolean()){
                newCycleValue = lastCycleTime - stepSize;
            } else {
                newCycleValue = lastCycleTime + stepSize;
            }
        }

        junctionData.setCycleTime(newCycleValue);
        return true;
    }

    public boolean goBackToPreviousPosition(JunctionData junctionData){
        if (lastCycleTime == -1){
            return false;
        }

        junctionData.setCycleTime(lastCycleTime);
        lastCycleTime = -1;

        return true;
    }
}
