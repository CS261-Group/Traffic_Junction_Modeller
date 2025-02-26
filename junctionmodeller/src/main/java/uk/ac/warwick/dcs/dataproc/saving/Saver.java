package uk.ac.warwick.dcs.dataproc.saving;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;

public class Saver implements ISaver{
    protected final IValidator validator;
    //so we don't get overalapping file names
    int fileCount;


    public Saver(IValidator validator) {
        this.validator = validator;
        fileCount = readFileCount();
    }
  
    

     public void updateFileCount(){
        //because a new saver is instantiated at each run, I need a file to store the number of files made since each instance of saver gets erased when program is closed
        String filePath = "src/main/java/uk/ac/warwick/dcs/dataproc/saving/fileCount.bin";
        File file = new File(filePath);
        
        try (DataOutputStream countFile = new DataOutputStream(new FileOutputStream(file))){
            countFile.writeInt(fileCount);
        } catch(IOException e) {
            e.printStackTrace();
        }
           
        
     }
     public int readFileCount(){
        String filePath = "src/main/java/uk/ac/warwick/dcs/dataproc/saving/fileCount.bin";
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

    @Override
    public void save(JunctionConfiguration junctionConfig, String configName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //determine base file path, depending on OS
        String os = System.getProperty("os.name").toLowerCase();
        String path = System.getProperty("user.home");
        //all paths are the same
        if(os.contains("win")){
            path = path + "/Documents/JunctionModellerConfigurations/";
        }
        else if(os.contains("mac")){
            path = path + "/Documents/JunctionModellerConfigurations/";
        }
        else{
            path = path + "/Documents/JunctionModellerConfigurations/";
        }
        
        File directory = new File(path);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory created: " + path);
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        }
        
        if(configName == ""){
            path = path + "configuration_" + String.valueOf(fileCount)+".json";  
            ++fileCount;
            updateFileCount();
        }   
        else{
            path = path + configName+ ".json";
        }
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(junctionConfig, writer);
            System.out.println("JSON file saved to "+path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        //changed to use JunctionConfiguration      
     }
}
