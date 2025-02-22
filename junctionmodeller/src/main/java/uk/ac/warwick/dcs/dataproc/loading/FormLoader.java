package uk.ac.warwick.dcs.dataproc.loading;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.*;
import uk.ac.warwick.dcs.dataproc.construction.FormJunctionFactory;
import uk.ac.warwick.dcs.ui.formdata.*;

/**
 * Implementation for <code>ILoader</code> interface used to
 * load <code>JunctionConfiguration</code> from the UI main
 * form.
 */
public class FormLoader implements ILoader {
    private final ConfigurationData configData;
    private String error;

    public FormLoader(ConfigurationData configData) {
        this.configData = configData;
        this.error = null;
    }

    @Override
    public JunctionConfiguration load() {
        // the builder we will construct the junction configuration with
        FormJunctionFactory factory = new FormJunctionFactory();

        try {
            return factory.createJunction(configData);
        } catch (InvalidDirectionException ex) {
            error = ex.getMessage();
        } catch (InvalidGroupNumberException ex) {
            error = ex.getMessage();
        } catch (InvalidGroupTimingException ex) {
            error = ex.getMessage();
        } catch (InvalidFlowValueException ex) {
            error = ex.getMessage();
        } catch (IncompleteBuildSettingsException ex) {
            error = ex.getMessage();
        } catch (InvalidPermittedDirectionsException ex) {
            error = ex.getMessage();
        }
        // NOTE: despite the equivalent handling, I still
        // separate out the handlers just in case I want to
        // handle them separately

        return null;
    }

    @Override
    public String getLoadErrors() {
        return error;
    }
}
