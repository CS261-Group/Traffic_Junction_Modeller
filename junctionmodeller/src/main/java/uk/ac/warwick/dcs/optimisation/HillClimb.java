package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

import java.util.ArrayList;

//
public class HillClimb {
    final double STEP_SIZE = 1;
    // final double MOMENTUM = 1.2;

    JunctionData currentNode;
    double currentEval;

    EvaluationFunction function;

    public HillClimb(JunctionData initial, EvaluationFunction function){
        this.currentNode = initial;
        this.function = function;
        currentEval = function.evaluationAt(currentNode);
    }

    //examines all neighbours
    public void makeStep(){
        var neighbours = currentNeighbours();
        double bestEval = currentEval;

        for (JunctionData neighbour : neighbours){
            if (function.evaluationAt(neighbour) < bestEval){
                currentNode = neighbour;
                bestEval = function.evaluationAt(neighbour);
            }
        }
    }

    //maintain cycle time
    public JunctionData randomNeighbour(JunctionData node){
        int group1 = (int) (Math.random() * node.getNumGroups());
        int group2 = (int) (Math.random() * node.getNumGroups());

        //make change
        //currentNode.updateGroupTiming();

        //either keep change or revert it somehow
        return null;
    }

    private ArrayList<JunctionData> currentNeighbours(){
        return null;
    }
}
