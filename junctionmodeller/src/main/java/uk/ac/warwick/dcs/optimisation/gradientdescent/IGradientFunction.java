package uk.ac.warwick.dcs.optimisation.gradientdescent;

import org.ejml.simple.SimpleMatrix;

/**
 * Interface used to generalise the functions used in gradient descent.
 */
@Deprecated
public interface IGradientFunction {
    /**
     * Evaluate the value of the given function at a given value.
     * @param values Inputs to the function.
     * @return Outputs to the function.
     */
    SimpleMatrix evaluateAt(SimpleMatrix values);
}
