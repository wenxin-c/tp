package seedu.duke.manager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;

import java.util.ArrayList;

/**
 * Manager is the superclass for all WellNUS++ event drivers <br>
 * <br>
 * Each manager is in charge of 'managing' exactly one feature. <br>
 * For example, <i>hb</i> and <i>reflect</i>. <br>
 * <br>
 * <p>
 * Each feature consists of multiple <code>MainCommands</code>,
 * stored in <code>supportedCommands</code> <br>
 * <br>
 * <p>
 * Each manager may also support entering other features
 * via <code>Manager</code> (event drivers),
 * stored in <code>supportedManagers</code> <br>
 * <br>
 * <p>
 * The manager should run an event driver (infinite loop) and is in charge
 * of a Feature's input, output, 'business' logic and graceful termination.
 */
public abstract class Manager {

    protected CommandParser commandParser;

    // For this Manager's feature, what commands exist?
    protected ArrayList<Command> supportedCommands;

    // For this Manager's feature, what features (event driver) does it support?
    protected ArrayList<Manager> supportedManagers;

    /**
     * Construct a feature Manager to handle control flow for the given feature. <br>
     * <br>
     * Internally, it sets up the following for convenience:
     * <li>CommandParser</li>
     * <li>Supported Commands</li>
     * <li>Supported Features</li>
     */
    public Manager() {
        this.commandParser = new CommandParser();
        this.supportedCommands = new ArrayList<>();
        this.supportedManagers = new ArrayList<>();
    }

    /**
     * Utility function to get the CommandParser tied to the Manager class.
     *
     * @return commandParser reference to this manager's instance of CommandParser
     */
    public CommandParser getCommandParser() {
        return this.commandParser;
    }

    /**
     * Utility function to get the featureName this Manager is administering
     *
     * @return name of the feature that this Manager handles
     */
    public abstract String getFeatureName();

    /**
     * Utility function to get a summary description of the feature this Manager is administering
     *
     * @return summary description of the feature that this Manager handles
     */
    public abstract String getBriefDescription();

    /**
     * Utility function to get the full description of the feature this Manager is administering
     *
     * @return full description of the feature that this Manager handles
     */
    public abstract String getFullDescription();

    /**
     * Utility function to set a list of main commands the feature supports <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedCommands.add([cmd1, cmd2, ...]); </code>
     */
    protected abstract void setSupportedCommands();

    /**
     * Utility function to set a list of main commands the feature supports <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedManagers.add([mgr1, mgr2, ...]); </code>
     */
    protected abstract void setSupportedFeatureManagers();

    /**
     * Checks if the feature is supported by this Manager
     *
     * @param featureName Name of the feature to query
     * @return true if featureName exists under supportedFeatures, else false
     * @throws NullPointerException is featureName is null
     */
    public boolean isSupportedFeature(String featureName) throws NullPointerException {
        // Sanity check for valid feature name variable
        if (featureName == null) {
            throw new NullPointerException("featureName cannot be null!");
        }

        for (Manager manager : supportedManagers) {
            String featureKeyword = manager.getFeatureName();
            if (featureName.equals(featureKeyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility function to get a string array of
     * all the supported commands' descriptions
     *
     * @return ArrayList of each command's full description
     */
    public ArrayList<String> getCommandDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Command command : supportedCommands) {
            descriptions.add(command.toString());
        }
        return descriptions;
    }

    /**
     * Utility function to get a list of main arguments the feature supports
     *
     * @return <code>ArrayList</code> of <code>Command</code>s
     */
    public ArrayList<Command> getSupportedCommands() {
        return supportedCommands;
    }

    /**
     * Utility function to get a list of main arguments the feature supports
     *
     * @return <code>ArrayList</code> of <code>Manager</code>s
     */
    public ArrayList<Manager> getSupportedFeatureManagers() {
        return supportedManagers;
    }

    /**
     * runEventDriver is the entry point into a feature's driver loop <br>
     * <br>
     * This should be the part that contains the infinite loop and switch cases,
     * but it is up to the implementer. <br>
     * Its implementation should include the following:
     * <li>A way to terminate runEventDriver</li>
     * <li>A way to read input from console</li>
     */
    public abstract void runEventDriver();

}
