package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;

// changing from beam search to gradient descent
public class GradientDescent<F extends IGradientFunction> {

    private final int ITERATIONS = 10;
    private final double STEPSIZE = 0.1;

    // column vector containing values for variables
    private SimpleMatrix stateVector;
    private SimpleMatrix maskVector;

    // derivative of the cost function
    private F NablaF;

    public GradientDescent(float[] initialState, F gradientFunction, boolean[] variableMask){
        stateVector = new SimpleMatrix(initialState);
        NablaF = gradientFunction;
        maskVector = this.maskArrayToMatrix(variableMask);
    }

    public GradientDescent(float[] initialState, F gradientFunction){
        stateVector = new SimpleMatrix(initialState);
        NablaF = gradientFunction;
        maskVector = this.createEmptyMask(initialState.length);
    }

    // needs testing
    public float[] getStateValues(){
        return stateVector.getFDRM().getData();
    }

    public void nextState(){
        SimpleMatrix gradientVector = new SimpleMatrix(NablaF.evaluateAt(stateVector));
        stateVector = stateVector.minus(gradientVector.elementMult(maskVector).scale(STEPSIZE));
    }

    public float[] stepThrough(){
        for (int i = 0; i < ITERATIONS; i++){
            this.nextState();
        }
        return this.getStateValues();
    }

    // creates a nxn mask matrix from array
    private SimpleMatrix maskArrayToMatrix(boolean[] variableMask){

        SimpleMatrix mask = createEmptyMask(variableMask.length);

        // TODO replace 1s with 0s in line with variableMask

        return mask;
    }

    //no mask is just the identity matrix
    private SimpleMatrix createEmptyMask(int length){
        return SimpleMatrix.ones(1,length);
    }



}
