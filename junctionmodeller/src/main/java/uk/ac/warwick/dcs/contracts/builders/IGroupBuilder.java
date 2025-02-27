package uk.ac.warwick.dcs.contracts.builders;

import uk.ac.warwick.dcs.contracts.exceptions.IncompleteBuildSettingsException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupNumberException;
import uk.ac.warwick.dcs.contracts.exceptions.InvalidGroupTimingException;
import uk.ac.warwick.dcs.contracts.structure.IncomingLane;
import uk.ac.warwick.dcs.contracts.timings.Groups;

public interface IGroupBuilder {
    /**
     * Compulsory setting. Must be set before <code>addLaneToGroup()</code>
     * and <code>setGroupTiming()</code> calls.
     * @param numGroups The number of traffic light groups we will have.
     * @return Same instance of builder object. Useful for chaining.
     */
    IGroupBuilder setNumGroups(int numGroups);

    /**
     * Compulsory to add every existing incoming lane to some group.
     * @param lane The <code>IncomingLane</code> object we are assigning to a group.
     * @param groupNum The group number of the group we are assigning it to.
     * @return Same instance of builder object. Useful for chaining.
     * @throws InvalidGroupNumberException Thrown if no traffic light group exists
     *                                     with <code>groupNum</code>.
     */
    IGroupBuilder addLaneToGroup(IncomingLane lane, int groupNum) throws InvalidGroupNumberException, IncompleteBuildSettingsException;

    /**
     * Compulsory setting for each group.
     * @param timing The timing (in secs) we set
     * @param groupNum The group number of the group which we are setting the timing
     *                 for.
     * @return Same instance of builder object. Useful for chaining.
     * @throws InvalidGroupNumberException Thrown if no traffic light group exists
     *                                     with <code>groupNum</code>.
     */
    IGroupBuilder setGroupTiming(int timing, int groupNum) throws InvalidGroupNumberException, InvalidGroupTimingException, IncompleteBuildSettingsException;

    /**
     * Compulsory setting.
     * @param optimising True if we are optimising the traffic light timings
     *                   in the configuration. False otherwise.
     * @return Same instance of builder object. Useful for chaining.
     */
    IGroupBuilder setOptimiseTimings(boolean optimising);

    /**
     *
     * @return Constructed <code>Groups</code> object from configured settings.
     * @throws IncompleteBuildSettingsException Thrown if some compulsory setting is not
     *                                          configured before <code>buildGroups</code>
     *                                          is called.
     */
    Groups buildGroups() throws IncompleteBuildSettingsException;
}
