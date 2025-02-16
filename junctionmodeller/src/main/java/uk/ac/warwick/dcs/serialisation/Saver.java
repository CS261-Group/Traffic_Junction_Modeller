/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.warwick.dcs.serialisation;
import com.google.gson.Gson;

import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
/**
 *
 * @author eyhli
 */
public class Saver implements ISaver{
    
    public void SaveFile(DirectionData[] directionData, TrafficLightData trafficLightData){
        DirectionData northboundData = directionData[0];
        DirectionData eastboundData = directionData[1];
        DirectionData southboundData = directionData[2];
        DirectionData westboundData = directionData[3];
        InputConfiguration inputConfiguration = new InputConfiguration(northboundData.arrivalFlows(), northboundData.availableDirections(), northboundData.departureFlows(), eastboundData.arrivalFlows(), eastboundData.availableDirections(), eastboundData.departureFlows(), southboundData.arrivalFlows(), southboundData.availableDirections(),southboundData.departureFlows(), westboundData.arrivalFlows(),westboundData.availableDirections(),westboundData.departureFlows());
        Gson gson = new Gson();
        String json = gson.toJson(inputConfiguration);
    }
}
