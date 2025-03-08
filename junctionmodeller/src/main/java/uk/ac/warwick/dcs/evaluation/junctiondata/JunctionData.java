package uk.ac.warwick.dcs.evaluation.junctiondata;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Group;
import uk.ac.warwick.dcs.model.messaging.ActuatedTiming;
import uk.ac.warwick.dcs.model.messaging.ActuationVisualisationData;
import uk.ac.warwick.dcs.model.messaging.FixedCycleVisualisationData;
import uk.ac.warwick.dcs.model.messaging.FixedTiming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JunctionData {
    public final double MIN_CYCLE_TIME = 30; // in seconds
    double cycleTime;
    private final TrafficLightType type;
    private ArrayList<GroupData> groupDataF;
    private ArrayList<ActuatedGroupData> groupDataA;

    public JunctionData(JunctionConfiguration junctionConfiguration) {
        cycleTime = junctionConfiguration.getCycleTime(); // should be 0 when optimising
        type = junctionConfiguration.getTrafficLightType();
        GroupDataBuilder groupBuilder = new GroupDataBuilder(junctionConfiguration);

        if (type == TrafficLightType.FIXEDCYCLE){
            groupDataF = new ArrayList<>(junctionConfiguration.getNumberOfGroups());
            for (Group group : junctionConfiguration.getGroups()) {
                //create group data
                groupDataF.add(groupBuilder.buildGroupData(group));
            }
        }
        else{
            groupDataA = new ArrayList<>(junctionConfiguration.getNumberOfGroups());
            for (Group group : junctionConfiguration.getGroups()) {
                //create group data
                groupDataA.add(groupBuilder.buildActuatedGroupData(group));
            }
        }
    }

    public Iterator<GroupData> iteratorF() {
        return groupDataF.iterator();
    }

    public Iterator<ActuatedGroupData> iteratorA() {
        return groupDataA.iterator();
    }

    public int getNumGroups(){
        if (type == TrafficLightType.FIXEDCYCLE){
            return groupDataF.size();
        } else {
            return groupDataA.size();
        }
    }

    public double getGroupExtensionTime(int groupIndex){
        if (type == TrafficLightType.ACTUATION){
            return groupDataA.get(groupIndex).getExtensionHeadway();
        } else {
            return 0;
        }
    }


    // index does not have to == group num
    public double getGroupGreenTime(int groupIndex){
        if (type == TrafficLightType.FIXEDCYCLE){
            return groupDataF.get(groupIndex).getGreenTime();
        } else {
            return groupDataA.get(groupIndex).getGreenTime();
        }
    }

    public void modifyGroupTiming(int groupIndex, double changeBy){
        if (type == TrafficLightType.FIXEDCYCLE){
            groupDataF.get(groupIndex).modifyTiming(changeBy);
        } else {
            groupDataA.get(groupIndex).modifyTiming(changeBy);
        }
    }

    public double getCycleTime(){
        return cycleTime;
    }

    public double[] getMaxFlowRatioForEachGroup(){
        if (type == TrafficLightType.FIXEDCYCLE){
            double[] flowRatios = new double[groupDataF.size()];

            for (int i = 0; i < groupDataF.size(); i++){
                flowRatios[i] = groupDataF.get(i).getMaxFlowRatio();
            }
            return flowRatios;
        } else {
            double[] flowRatios = new double[groupDataA.size()];

            for (int i = 0; i < groupDataA.size(); i++){
                flowRatios[i] = groupDataA.get(i).getMaxFlowRatio();
            }
            return flowRatios;
        }
    }

    //only for actuation traffic lights, returns 0 if called on fixed (not good)
    public double getTotalMaxGreenTimes(){
        if (type == TrafficLightType.ACTUATION) {
            double maxGreenTimes = 0;
            for (int i = 0; i < groupDataA.size(); i++){
                maxGreenTimes += groupDataA.get(i).getMaxGreenTime();
            }

            return maxGreenTimes;
        }
        return 0;
    }

    public void setCycleTime(double cycleTime){
        if (cycleTime < MIN_CYCLE_TIME){
            this.cycleTime = MIN_CYCLE_TIME;
        } else{
            this.cycleTime = cycleTime;
        }
    }

    public FixedCycleVisualisationData getFixedCycleVisualisationData() {
        return new FixedCycleVisualisationData(
                groupDataF.stream().map(x -> new FixedTiming((int)Math.round(x.greenTime))).toList()
        );
    }

    public ActuationVisualisationData getActuationVisualisationData() {
        return new ActuationVisualisationData(
                groupDataA.stream().map(x ->
                        new ActuatedTiming((int)Math.round(x.greenTime), (int)Math.round(x.maxGreenTime))).toList()
        );
    }

    public void setExtensionHeadway(int groupIndex, double newExtensionValue) {
        if (type == TrafficLightType.ACTUATION) {
            groupDataA.get(groupIndex).setExtensionHeadway(newExtensionValue);
        }

    }
}
