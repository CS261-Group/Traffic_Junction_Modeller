package uk.ac.warwick.dcs.contracts;

import java.util.Arrays;
import java.util.Iterator;

import uk.ac.warwick.dcs.contracts.lights.TrafficLightController;
import uk.ac.warwick.dcs.evaluation.metrics.Metric;

public class JunctionConfiguration implements Iterable<Carriageway> {
    // kept this in a variable because technically a junction can have
    // not necessarily 4 carriageways going into it, but we are assuming
    // it
    private static final int NUM_CARRIAGEWAYS = 4;

    private final Carriageway[] carriageways;

    // Traffic lights exists at the junction level now
    // because they can operate across carriageways
    // e.g. one traffic light turns green to let North and South Through
    private final TrafficLightController trafficLights;

    //use metric to updateAverageWaitTime, getAverageWaitTime etc...
    private final Metric metric = new Metric();


    public JunctionConfiguration(Carriageway[] cw, TrafficLightController tl) {
        assert cw.length == NUM_CARRIAGEWAYS;
        carriageways = cw;
        trafficLights = tl;
    }

    @Override
    public Iterator<Carriageway> iterator() {
        return Arrays.stream(carriageways).iterator();
    }
    public double getAverageWaitTime(){
        return metric.getAverageWaitTime();
    }
    
}
