package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;

/**
 * Simple gradient descent algorithm that takes a
 * fully partially differentiated evaluation function
 * And moves an internal state of values down a slope
 * of any number of dimensions.
 *
 * All vectors are row vectors
 *
 * @param <F>   differentiated evaluation function
 */
public class GradientDescent<F extends IGradientFunction> {

    /** number of iterations the algorithm makes */
    private int ITERATIONS = 10;

    // arbitrary value chosen at the moment, will need fine-tuning
    /** the learning rate */
    private double STEPSIZE = 0.1;

    /** Vector containing the current variable values */
    private SimpleMatrix stateVector;

    /** Vector used to mask variables that are not being optimised for */
    private SimpleMatrix maskVector;

    /** differentiated evaluation function */
    private F delF;

    /**
     * No mask construction, uses an empty mask.
     *
     * @param initialState      Initial values, given in order that they appear in
     *                          the gradientFunction's parameters
     * @param gradientFunction  function to determine slope at the current state
     */
    public GradientDescent(float[] initialState, F gradientFunction){
        stateVector = new SimpleMatrix(initialState);
        delF = gradientFunction;
        maskVector = this.createEmptyMaskVector(initialState.length);
    }

    /**
     * @param variableMask true where a variable should be optimised and false where it should be masked,
     *                     in the same order as the gradientFunction's parameters
     */
    public GradientDescent(float[] initialState, F gradientFunction, boolean[] variableMask){
        stateVector = new SimpleMatrix(initialState);
        delF = gradientFunction;
        maskVector = this.maskArrayToVector(variableMask);
    }

    /**
     * Returns the values in the current state
     *
     * @return the values
     */
    public float[] getStateValues(){
        return stateVector.getFDRM().getData();
    }

    /**
     * Perform one iteration of the gradient descent.
     * Uses <a href="https://en.wikipedia.org/wiki/Gradient_descent">this</a>
     * algorithm, with the addition of a mask to prevent changes to variables
     */
    public void nextState(){
        SimpleMatrix gradientVector = delF.evaluateAt(stateVector);
        stateVector = stateVector.minus(gradientVector.elementMult(maskVector).scale(STEPSIZE));
    }

    /**
     * Start the gradient descent
     *
     * @return  the final array of values
     */
    public float[] stepThrough(){
        for (int i = 0; i < ITERATIONS; i++){
            this.nextState();
        }
        return this.getStateValues();
    }

    /**
     * Returns a vector to use as the mask
     *
     * @param variableMask  list of which variables to keep (true), and which variables to mask (false)
     * @return              vector of 1s and 0s.
     */
    private SimpleMatrix maskArrayToVector(boolean[] variableMask){
        SimpleMatrix mask = createEmptyMaskVector(variableMask.length);

        for (int i = 0; i < variableMask.length; i++){
            if (!variableMask[i]){
                //set column i in row vector to 0
                mask.set(0,i,0);
            }
        }
        return mask;
    }

    /**
     * An empty mask vector is a vector that won't delete/mask any values
     *
     * @param length    Length of the mask
     * @return          A vector of all 1s
     */
    private SimpleMatrix createEmptyMaskVector(int length){
        return SimpleMatrix.ones(1,length);
    }

}
