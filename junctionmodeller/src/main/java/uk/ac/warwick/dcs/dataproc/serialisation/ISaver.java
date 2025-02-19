/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package uk.ac.warwick.dcs.dataproc.serialisation;

import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;

/**
 *
 * @author eyhli
 */
public interface ISaver {
    public void saveFile(DirectionData[] directionData, TrafficLightData trafficLightData);
}
