package wellnus.focus.command;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.feature.FocusManager;
import wellnus.focus.feature.FocusUi;
import wellnus.focus.feature.Session;

/**
 * The HomeCommand class is a command class that returns user back to the main WellNUS++ program.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "home - Stop the session and go back to WellNUS++.";
    public static final String COMMAND_USAGE = "usage: home";
    public static final String COMMAND_KEYWORD = "home";
    private static final int COMMAND_NUM_OF_ARGUMENTS = 1;
    private static final String COMMAND_INVALID_ARGUMENTS_MESSAGE = "That is not a valid home command for "
            + "focus timer!";
    private static final String COMMAND_INVALID_COMMAND_MESSAGE = "Wrong command given for home!";
    private static final String HOME_MESSAGE = "Thank you for using focus timer. Keep up the productivity!";
    private static final String NO_ADDITIONAL_MESSAGE = "";
    private static final String WRONG_COMMAND_ARGUMENTS_MESSAGE = "'home' command shouldn't have additional '%s' "
            + "argument";
    private final FocusUi focusUi;
    private final Session session;

    /**
     * Constructor for HomeCommand object.
     * Session in FocusManager is passed into this class.
     *
     * @param arguments Argument-Payload map generated by CommandParser
     * @param session   Session object which is an arraylist of Countdowns
     */
    public HomeCommand(HashMap<String, String> arguments, Session session) {
        super(arguments);
        this.focusUi = new FocusUi();
        this.session = session;
    }

    /**
     * Check if a HomeCommand is executed and user wants to return to home.
     *
     * @param command User command
     * @return true If user wants to exit feature false if not
     */
    public static boolean isExit(Command command) {
        return command instanceof HomeCommand;
    }

    /**
     * Identifies this Command's keyword.
     * Override this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword of this Command
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Identifies the feature that this Command is associated with.
     * Override this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword for the feature associated with this Command
     */
    @Override
    protected String getFeatureKeyword() {
        return FocusManager.FEATURE_NAME;
    }

    /**
     * Stops the current countdown to close background thread.
     * Prints the exit feature message for the focus timer feature on the user's screen.
     */
    @Override
    public void execute() throws WellNusException {
        validateCommand(super.getArguments());
        if (!session.isSessionReady()) {
            session.getCurrentCountdown().setStop();
        }
        // Reset the state of the countdown timer
        session.resetCurrentCountdownIndex();
        session.initialiseSession();
        focusUi.printOutputMessage(HOME_MESSAGE);
    }

    /**
     * Validate the arguments and payloads from a commandMap generated by CommandParser.
     * User input is valid if no exceptions are thrown.
     *
     * @param arguments Argument-Payload map generated by CommandParser
     * @throws BadCommandException if the commandMap has any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        if (arguments.size() > HomeCommand.COMMAND_NUM_OF_ARGUMENTS) {
            throw new BadCommandException(HomeCommand.COMMAND_INVALID_ARGUMENTS_MESSAGE);
        }
        if (!arguments.containsKey(HomeCommand.COMMAND_KEYWORD)) {
            throw new BadCommandException(HomeCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
        String payload = arguments.get(getCommandKeyword());
        if (!payload.isBlank()) {
            throw new BadCommandException(String.format(WRONG_COMMAND_ARGUMENTS_MESSAGE, payload));
        }
    }

    /**
     * Method to ensure that developers add in a command usage.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "usage: add --name (name of habit)"
     *
     * @return String of the proper usage of the habit
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Method to ensure that developers add in a description for the command.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "add - add a habit to your list"
     *
     * @return String of the description of what the command does
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
