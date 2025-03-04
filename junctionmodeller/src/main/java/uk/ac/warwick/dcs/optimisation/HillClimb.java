package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.ArrayList;

public class HillClimb<S extends ISearchSpace> {
    final double STEP_SIZE = 1;
    final int EXAMINE_NEIGHBOURS_LIMIT = 50;
    // final double MOMENTUM = 1.2;

    JunctionData currentNode;
    double currentEval;
    S searchSpace;

    EvaluationFunction function;

    public HillClimb(JunctionData initial, EvaluationFunction function, S searchSpace){
        this.currentNode = initial;
        this.function = function;
        this.searchSpace = searchSpace;
        currentEval = function.evaluationAt(currentNode);
    }

    //examine a random neighbour until a better one is found
    //then go to that neighbour
    // false if no steps could be made
    public boolean makeStep(){
        double bestEval = currentEval;
        int timeout = 0;

        while (++timeout < EXAMINE_NEIGHBOURS_LIMIT){
            if (!searchSpace.goToRandomNeighbour(currentNode)) {
                return false;
            }
            // current nodes position has been updated to the neighbours

            if (function.evaluationAt(currentNode) < bestEval) {
                currentEval = function.evaluationAt(currentNode);
                return true;
            } else {
                searchSpace.goBackToPreviousPosition(currentNode);
            }
        }

        return false;
    }

}
