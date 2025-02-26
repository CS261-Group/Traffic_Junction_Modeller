package uk.ac.warwick.dcs.dataproc;

import java.util.List;

import uk.ac.warwick.dcs.ui.formdata.ConfigurationData;

/**
 * Interface used to separate concerns between <code>ui</code>, <code>visualisation</code>
 * (frontend) and <code>model</code> libraries. Also, responsible for validation
 * and generally ensuring correctness of data being relayed between the two
 * ends of the system -- this involves converting localised data formats to
 * DTOs which are mutually understood.
 */
public interface IDataService {
    /**
     *
     * @param configData Data collected in a format localised to the <code>ui</code>
     *                   package.
     * @return A list of <code>String</code>s which correspond to diagnostic errors
     *         which the format of the entered data.
     */
    List<String> submitEnteredConfiguration(ConfigurationData configData,String configName);

    /**
     *
     * @param filePath The path to the file storing the loaded junction configuration.
     * @return A list of <code>String</code>s which correspond to diagnostic errors
     *         which the format of the entered data.
     */
    List<String> submitFileConfiguration(String filePath);
}
