package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.ArrayList;

//vfor fixed timings currently
// Used to get neighbours to visit next
public class FixedGroupTimingSearchSpace {
    private final double INITIAL_STEP_SIZE = 1; // in seconds
    double[] stepSize;

    public FixedGroupTimingSearchSpace(int numGroups){
        //initialise step size vector
        stepSize = new double[numGroups];
        for (int i = 0; i < numGroups; i++){
            stepSize[i] = 1;
        }
    }

    static JunctionData randomNeighbour(JunctionData){

        return null;
    }

    static boolean isInBounds(JunctionData junctionData){
        return false;
    }
}
