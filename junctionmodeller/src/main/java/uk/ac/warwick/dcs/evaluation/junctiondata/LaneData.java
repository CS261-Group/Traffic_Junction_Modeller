package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class LaneData {
    Direction direction;
    int laneNum;

    double saturationFlow;
    double arrivalFlow;

    public LaneData(Direction dir, int num, double saturation, double arrival){
        this.direction = dir;
        this.laneNum = num;
        this.saturationFlow = saturation;
        this.arrivalFlow = arrival;
    }

    public double getSaturationFlow(){
        return saturationFlow;
    }

    public double getArrivalFlow(){
        return arrivalFlow;
    }

    // arrival rate / saturation rate
    public double getFlowRatio(){
        return getArrivalFlow() / getSaturationFlow();
    }
}
