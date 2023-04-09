package wellnus.focus.command;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.focus.feature.FocusManager;
import wellnus.focus.feature.FocusUi;
import wellnus.focus.feature.Session;

/**
 * Represents a command to stop the current session.
 */
public class StopCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "stop - Stop the session. You will have to `start` "
            + "your focus session again!";
    public static final String COMMAND_USAGE = "usage: stop";
    public static final String COMMAND_KEYWORD = "stop";
    private static final int COMMAND_NUM_OF_ARGUMENTS = 1;
    private static final String COMMAND_INVALID_COMMAND_MESSAGE = "Invalid command issued, expected 'stop'!";
    private static final String COMMAND_INVALID_ARGUMENTS_MESSAGE = "Invalid arguments given to 'stop'!";
    private static final String COMMAND_INVALID_PAYLOAD = "Invalid payload given to 'stop'!";
    private static final String STOP_MESSAGE = "Your focus session has ended."
            + System.lineSeparator()
            + "To start a new session, `start` it up!"
            + System.lineSeparator()
            + "You can also configure the session to your liking with `config`!";
    private static final String ERROR_NOT_STARTED = "Nothing to stop - the timer has not started yet!";
    private static final String COMMAND_INVALID_COMMAND_NOTE = "stop command " + COMMAND_USAGE;
    private final Session session;
    private final FocusUi focusUi;

    /**
     * Constructs a StopCommand object.
     * Session in FocusManager is passed into this class.
     *
     * @param arguments Argument-Payload Hashmap generated by CommandParser
     * @param session   Session object which is an arraylist of Countdowns
     */
    public StopCommand(HashMap<String, String> arguments, Session session) {
        super(arguments);
        this.session = session;
        this.focusUi = new FocusUi();
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
     * Prints message to indicate session has ended.
     * Stops the session.
     * Resets the current countdown index.
     */
    @Override
    public void execute() {
        try {
            validateCommand(super.getArguments());
        } catch (BadCommandException badCommandException) {
            focusUi.printErrorFor(badCommandException, COMMAND_INVALID_COMMAND_NOTE);
            return;
        }
        if (!session.hasAnyCountdown() || session.isSessionReady()) {
            focusUi.printOutputMessage(ERROR_NOT_STARTED);
            return;
        }
        focusUi.printOutputMessage(STOP_MESSAGE);
        session.getCurrentCountdown().setStop();
        session.initialiseSession();
        session.resetCurrentCountdownIndex();
    }

    /**
     * Validate the arguments and payloads from a commandMap generated by CommandParser.
     * User input is valid if no exceptions are thrown.
     *
     * @param arguments Argument-Payload map generated by CommandParser
     * @throws BadCommandException If the arguments have any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        if (arguments.size() != COMMAND_NUM_OF_ARGUMENTS) {
            throw new BadCommandException(COMMAND_INVALID_ARGUMENTS_MESSAGE);
        }
        if (!arguments.containsKey(COMMAND_KEYWORD)) {
            throw new BadCommandException(COMMAND_INVALID_COMMAND_MESSAGE);
        }
        if (!arguments.get(COMMAND_KEYWORD).equals("")) {
            throw new BadCommandException(COMMAND_INVALID_PAYLOAD);
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

