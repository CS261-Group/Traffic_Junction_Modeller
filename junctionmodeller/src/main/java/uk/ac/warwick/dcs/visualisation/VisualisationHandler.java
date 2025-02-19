package uk.ac.warwick.dcs.visualisation;

import java.util.ArrayList;


// could possibly switch from a static class to a singleton but I don't really see the point
public class VisualisationHandler {
    private static ArrayList<Configuration> configList;
    private static int currentConfigIndex; // careful with this; may have to decrement when removing configs

    private static Visualiser visualiser; // singleton
    
    /*
     * This function is the entry-point of the Visualisation section
     * Eventually, I want its arguments to be the data for the configuration being run
     */
    public static boolean visualise(){
        // handle switching current visual stuff...

        // Long-term might need to use (dependency injection?) to give Visualiser the ability to switch configs

        // temp
        visualiser.Run();
        return true;
    }

    /*
     * Sets up the module; do things like initialising data structures e.g. ArrayList of configs
     */
    public static void init(){
        configList = new ArrayList<>();
        currentConfigIndex = -1;

        visualiser = new Visualiser();
    }

    /*
     * temp method for testing the module
     */
    public static void test(){
        Configuration aConfig = new Configuration();
        configList.add(aConfig);
        currentConfigIndex = configList.size() - 1;
    }


}