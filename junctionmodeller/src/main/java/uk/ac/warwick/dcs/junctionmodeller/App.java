package uk.ac.warwick.dcs.junctionmodeller;

import uk.ac.warwick.dcs.dataproc.DataServiceBuilder;
import uk.ac.warwick.dcs.dataproc.IDataService;
import uk.ac.warwick.dcs.model.IModelContainer;
import uk.ac.warwick.dcs.model.ModelContainerBuilder;
import uk.ac.warwick.dcs.ui.MainForm;
import uk.ac.warwick.dcs.visualisation.Visualiser;

/**
 * Entrypoint for program.
 */
public class App {
    public static void main(String[] args) {
        // initialise data service so main form can communicate with data processing layer
        IModelContainer modelContainer = ModelContainerBuilder.buildModelContainer();
        IDataService dataService = DataServiceBuilder.buildService(modelContainer);

        // making the frame visible
        MainForm form = new MainForm(dataService);
        form.setVisible(true);

        // making the visualiser visible
        Visualiser visualiser = Visualiser.getInstance();
        visualiser.run(args);
    }
}
