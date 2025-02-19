/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.warwick.dcs.dataproc.serialisation;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
/**
 *
 * @author eyhli
 */
public class Loader implements ILoader{
    private String error;
    public Loader(){
        error = null;
    }
    @Override
    public InputConfiguration LoadFile(String path){
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File not found");
        }
        else{
            try(FileInputStream input = new FileInputStream(file)){
                byte[] fileData = input.readAllBytes();
                String json = new String(fileData, StandardCharsets.UTF_8);

                Gson gson = new Gson();
                InputConfiguration inputConfiguration = gson.fromJson(json, InputConfiguration.class);
                System.out.println("form loaded, eastboundArrivalFlows are "+inputConfiguration.eastboundArrivalFlows);
                return inputConfiguration;
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public String getLoadErrors(){
        return error;
    }
}
