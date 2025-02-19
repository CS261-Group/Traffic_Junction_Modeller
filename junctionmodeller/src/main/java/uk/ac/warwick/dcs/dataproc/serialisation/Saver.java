/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.warwick.dcs.serialisation;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

import uk.ac.warwick.dcs.ui.formdata.DirectionData;
import uk.ac.warwick.dcs.ui.formdata.TrafficLightData;
 /**
  *
  * @author eyhli
  */
 public class Saver implements ISaver{
     //so we don't get overalapping file names
     int fileCount;

     public void updateFileCount(){
        //because a new saver is instantiated at each run, I need a file to store the number of files made since each instance of saver gets erased when program is closed
        String filePath = "src/main/java/uk/ac/warwick/dcs/serialisation/fileCount.bin";
        File file = new File(filePath);
        
        try (DataOutputStream countFile = new DataOutputStream(new FileOutputStream(file))){
            countFile.writeInt(fileCount);
        } catch(IOException e) {
            e.printStackTrace();
        }
           
        
     }
     public int readFileCount(){
        String filePath = "src/main/java/uk/ac/warwick/dcs/serialisation/fileCount.bin";
        File file = new File(filePath);
        if (!file.exists()) {
            //create file to store fileCount
            try (DataOutputStream countFile = new DataOutputStream(new FileOutputStream(file))){
                countFile.writeInt(0);
            } catch(IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
        else{
            try(DataInputStream countFile = new DataInputStream(new FileInputStream(file))){
                return countFile.readInt();
            }catch(Exception e){
                e.printStackTrace();
            }
            return -1;
        }

     }

     public Saver(){
        fileCount = readFileCount();
     }

     @Override
     public void saveFile(DirectionData[] directionData, TrafficLightData trafficLightData){
         DirectionData northboundData = directionData[0];
         DirectionData eastboundData = directionData[1];
         DirectionData southboundData = directionData[2];
         DirectionData westboundData = directionData[3];
         InputConfiguration inputConfiguration = new InputConfiguration(northboundData.arrivalFlows(), northboundData.availableDirections(), northboundData.departureFlows(), eastboundData.arrivalFlows(), eastboundData.availableDirections(), eastboundData.departureFlows(), southboundData.arrivalFlows(), southboundData.availableDirections(),southboundData.departureFlows(), westboundData.arrivalFlows(),westboundData.availableDirections(),westboundData.departureFlows());
         Gson gson = new Gson();
         String json = gson.toJson(inputConfiguration);
         
         convertAndUploadFile(json);
           
         
     }
 
     private void convertAndUploadFile(String json){
        String path = System.getProperty("user.home") + "/appdata/configuration_"+String.valueOf(fileCount)+".bin";

        File file = new File(path);
        
        try (FileOutputStream outputFile = new FileOutputStream(file)){
        outputFile.write(json.getBytes());
        ++fileCount;
        updateFileCount();
        System.out.println("Saved to "+file.getAbsolutePath());
        } catch(IOException e) {
            e.printStackTrace();
        }
        
     }
 }
 