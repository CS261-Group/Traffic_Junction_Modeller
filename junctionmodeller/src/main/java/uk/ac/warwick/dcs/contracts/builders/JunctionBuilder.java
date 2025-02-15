package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.contracts.exceptions.IncompleteJunctionException;

public class JunctionBuilder implements ILightBuilder, ICarriagewayBuilder, IGroupBuilder {
    private final ILightBuilder lightBuilder;
    private final ICarriagewayBuilder[] carriagewayBuilders;

    public JunctionBuilder() {
        lightBuilder = new LightBuilder();
        carriagewayBuilders = new CarriagewayBuilder[4];

        carriagewayBuilders[Direction.NORTH.ordinal()] = new CarriagewayBuilder(Direction.NORTH);
        carriagewayBuilders[Direction.EAST.ordinal()] = new CarriagewayBuilder(Direction.EAST);
        carriagewayBuilders[Direction.SOUTH.ordinal()] = new CarriagewayBuilder(Direction.SOUTH);
        carriagewayBuilders[Direction.WEST.ordinal()] = new CarriagewayBuilder(Direction.WEST);
    }

    public JunctionConfiguration build() throws IncompleteJunctionException {
        return null;
    }
}
