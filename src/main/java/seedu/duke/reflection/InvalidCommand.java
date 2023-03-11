package seedu.duke.reflection;

import seedu.duke.command.Command;
import seedu.duke.exception.InvalidCommandException;

/**
 * Doesn't seem to align with what is required for command superclass
 */
public class InvalidCommand {
    private static final String INVALID_COMMAND_MESSAGE = "Sorry this command is invalid.";

    /**
     * Throw error and alert users of invalid command.
     *
     * @throws InvalidCommandException Invalid command from users
     */
    public void alertError() throws InvalidCommandException {
        throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
    }
}


