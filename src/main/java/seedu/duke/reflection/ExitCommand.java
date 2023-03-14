package seedu.duke.reflection;

import seedu.duke.command.Command;
import seedu.duke.exception.BadCommandException;

import java.util.HashMap;

public class ExitCommand extends Command {
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String COMMAND_KEYWORD = "exit";
    private static final String FULL_DESCRIPTION = "";
    private static final String ARGUMENT = "exit";
    private static final String PAYLOAD = "";
    private static final ReflectUi UI = new ReflectUi();
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final String INVALID_COMMAND_MSG = "Command is invalid.";
    private static final String INVALID_COMMAND_NOTES = "Please check the available commands "
            + "and the format of commands.";
    private HashMap<String, String> argumentPayload;

    public ExitCommand(HashMap<String, String> arguments) throws BadCommandException {
        super(arguments);
        this.argumentPayload = getArguments();
    }

    /**
     * Get the command itself.
     *
     * @return Command: exit
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * TODO: Get detailed description of an exit command.
     * FULL_DESCRIPTION is not completed yet.
     *
     * @return Full description of exit command
     */
    @Override
    protected String getDetailedDescription() {
        return FULL_DESCRIPTION;
    }

    /**
     * Get the name of the feature in which this exit command is generated.
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
     * @return Argument: exit
     */
    @Override
    protected String getSupportedCommandArguments() {
        return ARGUMENT;
    }

    /**
     * To be completed after main manager is merged.
     */
    @Override
    public void execute() {
        try {
            validateCommand(this.argumentPayload);
        } catch (BadCommandException invalidCommandException) {
            UI.printErrorFor(invalidCommandException, INVALID_COMMAND_NOTES);
        }
    }

    /**
     * Validate the command and return a boolean value.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: exit
     * <li>Payload is empty
     * Whichever mismatch will cause the command to be invalid.
     */
    @Override
    public void validateCommand(HashMap<String, String> commandMap) throws BadCommandException {
        if (commandMap.size() != ARGUMENT_PAYLOAD_SIZE) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.containsKey(COMMAND_KEYWORD)) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.get(COMMAND_KEYWORD).equals(PAYLOAD)){
            throw new BadCommandException(INVALID_COMMAND_MSG);
        }
    }
}

