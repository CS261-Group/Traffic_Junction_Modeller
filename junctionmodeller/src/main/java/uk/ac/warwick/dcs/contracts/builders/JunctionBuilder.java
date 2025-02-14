package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteJunctionException;

public class JunctionBuilder implements ILightBuilder, ICarriagewayBuilder, IGroupBuilder {
    private final ILightBuilder lightBuilder;
    private final ICarriagewayBuilder carriagewayBuilder;

    public JunctionBuilder() {
        lightBuilder = new LightBuilder();
        carriagewayBuilder = new CarriagewayBuilder();
    }

    public JunctionConfiguration build() throws IncompleteJunctionException {
        return null;
    }
}
