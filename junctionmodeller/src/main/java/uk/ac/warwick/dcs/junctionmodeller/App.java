package uk.ac.warwick.dcs.junctionmodeller;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.DataService;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.dataproc.validation.IValidator;
import uk.ac.warwick.dcs.dataproc.validation.JunctionValidator;
import uk.ac.warwick.dcs.ui.MainForm;

/**
 * Entrypoint for program.
 */
public class App {
    public static void main(String[] args) {
        // initialise data service so main form can communicate with data processing layer
        IValidator<JunctionConfiguration> junctionValidator = new JunctionValidator();
        IDataService dataService = new DataService(junctionValidator);

        // making the frame visible
        MainForm form = new MainForm(dataService);
        form.setVisible(true);
    }
}
