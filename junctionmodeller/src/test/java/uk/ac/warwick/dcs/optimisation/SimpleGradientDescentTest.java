package uk.ac.warwick.dcs.optimisation;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import uk.ac.warwick.dcs.optimisation.gradientdescent.GradientDescent;
import uk.ac.warwick.dcs.optimisation.gradientdescent.IGradientFunction;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGradientDescentTest {

//    @Test
//    void createEmptyVariableMaskVectorLength2(){
//        SimpleGradientFunction f = new SimpleGradientFunction();
//        GradientDescent gradientDescentCalculator = new GradientDescent(new float[] {0,0}, f);
//        assertFalse(1 < 0);
//    }
//
//    @Test
//    void createFullVariableMaskVectorLength2(){
//        SimpleGradientFunction f = new SimpleGradientFunction();
//        GradientDescent gradientDescentCalculator = new GradientDescent(new float[] {0,0}, f, new boolean[] {false,false});
//        assertFalse(1 < 0);
//    }

    @Test
    void gettingInitialStateOfSize4(){
        SimpleGradientFunction f = new SimpleGradientFunction();
        //using java type inference
        var gradientDescentCalculator = new GradientDescent<SimpleGradientFunction>(new float[] {5.7f, -12.24f, 3, 3}, f);
        assertArrayEquals(new float[] {5.7f, -12.24f, 3, 3}, gradientDescentCalculator.getStateValues());
    }

    @Test
    void getStateValues() {
    }

    @Test
    void nextState() {
    }

    @Test
    void stepThrough() {
    }
}

// F = x^2 + 2y^2
// Del F = [2x + 2y^2, x^2 + 4y]
class SimpleGradientFunction implements IGradientFunction {

    public SimpleGradientFunction(){}

    public float delFWithRespectToX(float x, float y){
        return 2*x + 2*y*y;
    }

    public float delFWithRespectToY(float x, float y){
        return x*x + 4*y;
    }

    @Override
    public SimpleMatrix evaluateAt(SimpleMatrix values) {
        float x = (float) values.get(0,0);
        float y = (float) values.get(1,0);

        float[] slopeVector = new float[2];

        slopeVector[0] = delFWithRespectToX(x,y);
        slopeVector[1] = delFWithRespectToY(x,y);

        return new SimpleMatrix(slopeVector);
    }
}