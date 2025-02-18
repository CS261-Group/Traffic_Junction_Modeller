package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;

public interface IGradientFunction {

    //Continuous space, so can assume working with floats
    public float[] evaluateAt(SimpleMatrix values);
}
