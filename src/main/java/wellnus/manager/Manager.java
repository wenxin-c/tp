package wellnus.manager;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;

/**
 * Manager is the superclass for all WellNUS++ event drivers. <br>
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
//@@author nichyjt
public abstract class Manager {

    protected CommandParser commandParser;

    /**
     * Construct a feature Manager to handle control flow for the given feature. <br>
     * <br>
     * Internally, it sets up the following for convenience:
     * <li>CommandParser</li>
     * <li>Supported Commands</li>
     */
    public Manager() {
        this.commandParser = new CommandParser();
    }

    /**
     * Utility function to get the CommandParser tied to the Manager class.
     *
     * @return CommandParser reference to this manager's instance of CommandParser
     */
    public CommandParser getCommandParser() {
        assert commandParser != null : "commandParser should not be null";
        return this.commandParser;
    }

    /**
     * Utility function to get the featureName this Manager is administering.
     *
     * @return Name of the feature that this Manager handles
     */
    public abstract String getFeatureName();

    /**
     * Abstract function to ensure developers add in a getter for the feature's help description.
     * <p>
     * This description will be shown when the user types in the help command. <br>
     * The description should be a brief overview of what the feature does. <br>
     * For example: <br>
     * "reflect: Reflect is your go-to tool to get, save and reflect on our specially
     * curated list of questions to reflect on"
     *
     * @return String of the feature's help description
     */
    public abstract String getFeatureHelpDescription();

    /**
     * runEventDriver is the entry point into a feature's driver loop. <br>
     * <br>
     * This should be the part that contains the infinite loop and switch cases,
     * but it is up to the implementer. <br>
     * Its implementation should include the following:
     * <li>A way to terminate runEventDriver</li>
     * <li>A way to read input from console</li>
     */
    public abstract void runEventDriver() throws BadCommandException;

}
