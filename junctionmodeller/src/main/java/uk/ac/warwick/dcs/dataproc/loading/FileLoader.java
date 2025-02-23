/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.warwick.dcs.dataproc.loading;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.serialisation.InputConfiguration;

/**
 * Implementation of <code>ILoader</code> interface which loads a
 * junction configuration from a supplied file path.
 * @author eyhli
 */
public class FileLoader implements ILoader {
    private String error;
    private final String filePath;

    public FileLoader(String path) {
        filePath = path;
        error = null;
    }

    @Override
    public JunctionConfiguration load() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found");
        } else {
            try (FileInputStream input = new FileInputStream(file)) {
                byte[] fileData = input.readAllBytes();
                String json = new String(fileData, StandardCharsets.UTF_8);

                Gson gson = new Gson();
                // TODO: convert to JunctionConfiguration object (as per the interface)
//                InputConfiguration inputConfiguration = gson.fromJson(json, InputConfiguration.class);
//                System.out.println("form loaded, eastboundArrivalFlows are " + inputConfiguration.eastboundArrivalFlows);
                return null;
            } catch (Exception e) { // TODO: more specific exception handling
                error = e.getMessage();
            }
        }
        return null;
    }

    @Override
    public String getLoadErrors(){
        return error;
    }
}
