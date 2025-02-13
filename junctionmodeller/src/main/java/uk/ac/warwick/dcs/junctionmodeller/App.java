package uk.ac.warwick.dcs.junctionmodeller;

import uk.ac.warwick.dcs.dataproc.DataService;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.ui.MainForm;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // initialise data service so main form can communicate with data processing layer
        IDataService dataService = new DataService(null); // TODO: implement validator

        // making the frame visible
        MainForm form = new MainForm(dataService);
        form.setVisible(true);
    }
}
