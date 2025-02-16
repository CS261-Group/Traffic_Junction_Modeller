/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.warwick.dcs.serialisation;
import com.google.gson.Gson;
/**
 *
 * @author eyhli
 */
public class Loader implements ILoader{
    public InputConfiguration LoadFile(String jsonstr){
        Gson gson = new Gson();
        InputConfiguration inputConfiguration = gson.fromJson(jsonstr, InputConfiguration.class);
        return inputConfiguration;
    }
}
