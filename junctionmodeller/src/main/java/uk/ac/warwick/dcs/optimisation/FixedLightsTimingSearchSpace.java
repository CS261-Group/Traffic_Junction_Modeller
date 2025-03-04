package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.contracts.timings.GroupTiming;
import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.Random;

//vfor fixed timings currently
// Used to get neighbours to visit next
public class FixedLightsTimingSearchSpace implements ISearchSpace{
    private final int TIME_OUT_CONST = 10;
    private final double INITIAL_STEP_SIZE = 1; // in seconds
    private double[] stepSize; // vector allows for momentum (not used rn)
    private int[] lastChanged;
    private Random randomNumGen;

    public FixedLightsTimingSearchSpace(int numGroups){
        //initialise step size vector
        stepSize = new double[numGroups];
        for (int i = 0; i < numGroups; i++){
            stepSize[i] = INITIAL_STEP_SIZE;
        }

        lastChanged = new int[] {-1,-1};
        randomNumGen = new Random();

    }

    public boolean goToRandomNeighbour(JunctionData junctionData){
        int upperBound = junctionData.getNumGroups();
        int r1;
        double newGreenValue1;
        int r2;
        double newGreenValue2;
        // to get rid of infinite loops in the case all minimised or maximised green timings
        // should be unlikely to occur
        int timeout = 0;

        // generate first random number
        do {
            if (timeout++ > TIME_OUT_CONST){
                return false;
            }
            r1 = randomNumGen.nextInt(upperBound);
            newGreenValue1 = junctionData.getGroupGreenTime(r1) - stepSize[r1];
        } while (newGreenValue1 < GroupTiming.MIN_GROUP_TIMING);
        timeout = 0;

        // this code chooses a different random number to r1
        // and makes sure that number doesnt result in a out of bounds timing
        do {
            if (timeout++ > TIME_OUT_CONST){
                return false;
            }
            do {
                r2 = randomNumGen.nextInt(upperBound);
            } while (r1 == r2);
            // must be the same step size as the previous change to keep cycle time fixed
            newGreenValue2 = junctionData.getGroupGreenTime(r2) + stepSize[r1];
        } while (newGreenValue2 > GroupTiming.MAX_GROUP_TIMING);

        //add r1, r2 to stack
        lastChanged[0] = r1;
        lastChanged[1] = r2;

        junctionData.modifyGroupTiming(r1, -stepSize[r1]);
        junctionData.modifyGroupTiming(r2, stepSize[r1]);

        return true;
    }

    public boolean goBackToPreviousPosition(JunctionData junctionData){
        if (lastChanged[0] == -1){
            return false;
        }

        junctionData.modifyGroupTiming(lastChanged[0], stepSize[lastChanged[0]]);
        junctionData.modifyGroupTiming(lastChanged[1], -stepSize[lastChanged[0]]);
        lastChanged[0] = -1;
        lastChanged[1] = -1;

        return true;
    }
}
