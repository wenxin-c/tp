package seedu.duke.reflection;

public class InvalidCommand extends Command {
    private static final String INVALID_COMMAND_MESSAGE = "Sorry this command is invalid.";
    @Override
    public void execute() {

    }

    /**
     * Throw error and alert users of invalid command.
     *
     * @throws InvalidCommandException invalid command from users
     */
    public void alertError() throws InvalidCommandException {
        throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
    }
}
