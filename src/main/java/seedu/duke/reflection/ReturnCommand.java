package seedu.duke.reflection;

import seedu.duke.command.Command;
import seedu.duke.exception.BadCommandException;
import seedu.duke.exception.InvalidCommandException;

import java.util.HashMap;

public class ReturnCommand extends Command {
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String COMMAND_KEYWORD = "return";
    private static final String FULL_DESCRIPTION = "";
    private static final String ARGUMENT = "return";
    private static final String PAYLOAD = "";
    private static final ReflectUi UI = new ReflectUi();
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final String INVALID_COMMAND_MSG = "Command is invalid.";
    private static final String INVALID_COMMAND_NOTES = "Please check the available commands and the format of commands.";
    private HashMap<String, String> argumentPayload;

    public ReturnCommand(HashMap<String, String> arguments) throws BadCommandException {
        super(arguments);
        this.argumentPayload = getArguments();
    }

    /**
     * Get the command itself.
     *
     * @return Command: return
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Get detailed description of a return command.
     * FULL_DESCRIPTION is not completed yet.
     *
     * @return Full description of return command
     */
    @Override
    protected String getDetailedDescription() {
        return FULL_DESCRIPTION;
    }

    /**
     * Get the name of the feature in which this return command is generated.
     *
     * @return Self reflection
     */
    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
    }

    /**
     * Only one supported argument for exit command.
     *
     * @return Argument: return
     */
    @Override
    protected String getSupportedCommandArguments() {
        return ARGUMENT;
    }

    /**
     * Return back to WellNUS++ main interface
     */
    @Override
    public void execute() {
        try {
            validateCommand();
        } catch (InvalidCommandException invalidCommandException) {
            UI.printErrorFor(invalidCommandException, INVALID_COMMAND_NOTES);
        }
        ReflectionManager.setIsExit(true);
    }

    /**
     * Validate the command and return a boolean value.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: return
     * <li>Payload is empty
     * Whichever mismatch will cause the command to be invalid.
     */
    protected void validateCommand() throws InvalidCommandException {
        if (argumentPayload.size() != ARGUMENT_PAYLOAD_SIZE) {
            throw new InvalidCommandException(INVALID_COMMAND_MSG);
        } else if (!argumentPayload.containsKey(COMMAND_KEYWORD)) {
            throw new InvalidCommandException(INVALID_COMMAND_MSG);
        } else if (!argumentPayload.get(COMMAND_KEYWORD).equals(PAYLOAD)){
            throw new InvalidCommandException(INVALID_COMMAND_MSG);
        }
    }
}


