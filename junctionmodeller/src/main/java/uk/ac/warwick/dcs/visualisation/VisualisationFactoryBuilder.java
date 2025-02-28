package uk.ac.warwick.dcs.visualisation;

public class VisualisationFactoryBuilder {
    private static IVisualisationFactory visualisationFactory = null;

    public static IVisualisationFactory getVisualisationFactory() {
        if (visualisationFactory == null) {
            visualisationFactory = new VisualisationFactory();
        }
        return visualisationFactory;
    }
}
