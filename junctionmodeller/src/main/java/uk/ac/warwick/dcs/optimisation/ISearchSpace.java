package uk.ac.warwick.dcs.optimisation;

import uk.ac.warwick.dcs.evaluation.junctiondata.JunctionData;

public interface ISearchSpace {
    boolean goToRandomNeighbour(JunctionData junctionData);
    boolean goBackToPreviousPosition(JunctionData junctionData);
}
