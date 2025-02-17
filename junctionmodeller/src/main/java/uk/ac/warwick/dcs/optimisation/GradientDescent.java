package uk.ac.warwick.dcs.optimisation;

// slightly different from local search
public class GradientDescent<S> {

    private int[] variables;

    private int stepSize;

    public GradientDescent(int[] v){
        variables = v;
    }

    //takes a copy of the current state
    public S nextState(S currentState){

        //for each variable
        //use the partial derivative of the evaluation function
        //at the current state
        //to get the new value of the variable
        return null;

    }

}
