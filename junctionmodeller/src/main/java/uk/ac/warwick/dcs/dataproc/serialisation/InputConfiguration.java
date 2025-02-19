package uk.ac.warwick.dcs.serialisation;
import java.util.List;

import uk.ac.warwick.dcs.ui.formdata.AvailableDirections;
/**
 *
 * @author eyhli
 */
public class InputConfiguration {
    List<Integer> northboundArrivalFlows;
    List<AvailableDirections> northboundAvailableDirections;
    List<Integer> northboundDepartureFlows;

    List<Integer> eastboundArrivalFlows;
    List<AvailableDirections> eastboundAvailableDirections;
    List<Integer> eastboundDepartureFlows;

    List<Integer> southboundArrivalFlows;
    List<AvailableDirections> southboundAvailableDirections;
    List<Integer> southboundDepartureFlows;

    List<Integer> westboundArrivalFlows;
    List<AvailableDirections> westboundAvailableDirections;
    List<Integer> westboundDepartureFlows;
    
    public InputConfiguration(List<Integer> northboundArrivalFlows,List<AvailableDirections> northboundAvailableDirections,List<Integer> northboundDepartureFlows,List<Integer> eastboundArrivalFlows,List<AvailableDirections> eastboundAvailableDirections,List<Integer> eastboundDepartureFlows,List<Integer> southboundArrivalFlows,List<AvailableDirections> southboundAvailableDirections,List<Integer> southboundDepartureFlows,List<Integer> westboundArrivalFlows,List<AvailableDirections> westboundAvailableDirections,List<Integer> westboundDepartureFlows){
        this.northboundArrivalFlows = northboundArrivalFlows;
        this.northboundAvailableDirections = northboundAvailableDirections;
        this.northboundDepartureFlows = northboundDepartureFlows;

        this.eastboundArrivalFlows = eastboundArrivalFlows;
        this.eastboundAvailableDirections = eastboundAvailableDirections;
        this.eastboundDepartureFlows = eastboundDepartureFlows;

        this.southboundArrivalFlows = southboundArrivalFlows;
        this.southboundAvailableDirections = southboundAvailableDirections;
        this.southboundDepartureFlows = southboundDepartureFlows;

        this.westboundArrivalFlows = westboundArrivalFlows;
        this.westboundAvailableDirections = westboundAvailableDirections;
        this.westboundDepartureFlows = westboundDepartureFlows;

    }
}
