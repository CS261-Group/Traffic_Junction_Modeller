package uk.ac.warwick.dcs.dataproc.loading;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;

public class FileLoader implements ILoader {
    private final String filePath;
    private String error;

    public FileLoader(String filePath) {
        this.filePath = filePath;
    }

    public JunctionConfiguration load(){
        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(TrafficLight.class, new TrafficLightAdapter())
        .create();
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("File not found");
            return null;
        }
        try(FileReader reader = new FileReader(file)){
            System.out.println("file loaded successfully");
            return gson.fromJson(reader,JunctionConfiguration.class);
        }catch(IOException ex){
            error = ex.getMessage();
            return null;
        }
    }

    @Override
    public String getLoadErrors() {
        return error;
    }
}
