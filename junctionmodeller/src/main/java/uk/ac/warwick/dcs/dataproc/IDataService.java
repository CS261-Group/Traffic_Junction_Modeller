package uk.ac.warwick.dcs.dataproc;

import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

import java.util.List;

public interface IDataService {
    List<String> submitEnteredConfiguration(ConfigurationData configData);
    List<String> submitFileConfiguration(String filePath);
}
