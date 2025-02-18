package uk.ac.warwick.dcs.junctionmodeller;

import uk.ac.warwick.dcs.dataproc.DataServiceBuilder;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.ui.MainForm;

/**
 * Entrypoint for program.
 */
public class App {
    public static void main(String[] args) {
        // initialise data service so main form can communicate with data processing layer
        IDataService dataService = DataServiceBuilder.buildService();

        // making the frame visible
        MainForm form = new MainForm(dataService);
        form.setVisible(true);
    }
}
