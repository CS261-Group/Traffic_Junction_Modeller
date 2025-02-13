package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.Carriageway;
import uk.ac.warwick.dcs.contracts.CarriagewayBuilder;
import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

public class FormLoader implements ILoader {
    private final ConfigurationData configData;
    private final CarriagewayBuilder carriagewayBuilder;

    public FormLoader(ConfigurationData configData) {
        this.configData = configData;
        this.carriagewayBuilder = new CarriagewayBuilder();
    }

    @Override
    public JunctionConfiguration load() {
        // TODO:
        return null;
    }
}
