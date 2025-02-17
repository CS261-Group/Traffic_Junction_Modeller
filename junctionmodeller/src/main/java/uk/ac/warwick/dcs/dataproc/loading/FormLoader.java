package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidDirectionException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.dataproc.construction.JunctionFactory;
import uk.ac.warwick.dcs.ui.formdata.*;

import java.util.List;

public class FormLoader implements ILoader {
    private final ConfigurationData configData;

    public FormLoader(ConfigurationData configData) {
        this.configData = configData;
    }

    @Override
    public JunctionConfiguration load() {
        // the builder we will construct the junction configuration with
        JunctionFactory factory = new JunctionFactory();

        try {
            JunctionConfiguration junctionConfiguration = factory.createJunction(configData);
            return junctionConfiguration;
        } catch (InvalidDirectionException ex) {
            // TODO: handle properly and allow returned errors
        } catch (InvalidGroupNumberException ex) {
            // TODO: handle properly and allow returned errors
        }

        return null;
    }
}
