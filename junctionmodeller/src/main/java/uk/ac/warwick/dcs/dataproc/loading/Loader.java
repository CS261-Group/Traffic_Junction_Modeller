package uk.ac.warwick.dcs.dataproc.loading;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;

public class Loader {

    public JunctionConfiguration LoadFile(String path){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File not found");
            return null;
        }
        try(FileReader reader = new FileReader(file)){
            System.out.println("file loaded successfully");
            return gson.fromJson(reader,JunctionConfiguration.class);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
