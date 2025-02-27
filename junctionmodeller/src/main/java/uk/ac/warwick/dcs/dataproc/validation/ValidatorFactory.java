package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
import uk.ac.warwick.dcs.contracts.structure.Carriageway;
import uk.ac.warwick.dcs.contracts.timings.Groups;

/**
 * Class used to be public-facing interface to create junction validator.
 * Contains singleton instances of:
 * - <code>DiagnosticFactory</code>
 * - <code>JunctionValidator</code>
 * - <code>CarriagewayValidator</code>
 * - <code>TrafficLightValidator</code>
 * - <code>GroupsValidator</code>
 * - <code>LaneAssignmentValidator</code>
 */
public class ValidatorFactory {
    private static IDiagnosticFactory diagnosticFactory = null;
    private static IValidator<JunctionConfiguration> junctionValidator = null;

    private static IValidator<Carriageway> carriagewayValidator = null;
    private static IValidator<TrafficLight> trafficLightValidator = null;
    private static IValidator<Groups> groupsValidator = null;
    private static IValidator<JunctionConfiguration> laneAssignmentValidator = null;

    /**
     *
     * @return Singleton instance of <code>DiagnosticFactory</code> object.
     */
    static IDiagnosticFactory getDiagnosticFactory() {
        if (diagnosticFactory == null) {
            diagnosticFactory = new DiagnosticFactory();
        }
        return diagnosticFactory;
    }

    /**
     *
     * @return Singleton instance of <code>IValidator&lt;JunctionConfiguration&gt;</code>.
     */
    public static IValidator<JunctionConfiguration> getJunctionValidator() {
        if (junctionValidator == null) {
            junctionValidator = new JunctionValidator(
                    getDiagnosticFactory(),
                    getCarriagewayValidator(),
                    getTrafficLightValidator(),
                    getGroupsValidator(),
                    getLaneAssignmentValidator());
        }
        return junctionValidator;
    }

    /**
     *
     * @return Singleton instance of contained <code>CarriagewayValidator</code> object.
     */
    private static IValidator<Carriageway> getCarriagewayValidator() {
        if (carriagewayValidator == null) {
            carriagewayValidator = new CarriagewayValidator(getDiagnosticFactory());
        }
        return carriagewayValidator;
    }

    /**
     *
     * @return Singleton instance of contained <code>TrafficLightValidator</code> object.
     */
    private static IValidator<TrafficLight> getTrafficLightValidator() {
        if (trafficLightValidator == null) {
            trafficLightValidator = new TrafficLightValidator(getDiagnosticFactory());
        }
        return trafficLightValidator;
    }

    /**
     *
     * @return Singleton instance of contained <code>GroupsValidator</code> object.
     */
    private static IValidator<Groups> getGroupsValidator() {
        if (groupsValidator == null) {
            groupsValidator = new GroupsValidator(getDiagnosticFactory());
        }
        return groupsValidator;
    }

    /**
     *
     * @return Singleton instance of contained <code>GroupsValidator</code> object.
     */
    private static IValidator<JunctionConfiguration> getLaneAssignmentValidator() {
        if (laneAssignmentValidator == null) {
            laneAssignmentValidator = new LaneAssignmentValidator(getDiagnosticFactory());
        }
        return laneAssignmentValidator;
    }
}
