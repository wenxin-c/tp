package wellnus.command;

import wellnus.exception.BadCommandException;
import wellnus.ui.TextUi;

import java.util.HashMap;

public class ExitCommand extends Command {
    private static final String COMMAND_ARGUMENTS = "";
    private static final String COMMAND_DETAILED_DESCRIPTION = "Exits from the WellNUS++ application";
    private static final String COMMAND_INVALID_COMMAND_MESSAGE = "Invalid exit command given!";
    private static final String FEATURE_KEYWORD = "";
    private static final String GOODBYE_MESSAGE = "Thank you for using WellNUS++! Come back soon!";
    private final TextUi textUi;
    public static final String COMMAND_KEYWORD = "exit";

    public ExitCommand(HashMap<String, String> arguments) throws BadCommandException {
        super(arguments);
        this.textUi = new TextUi();
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private void printGoodbye() {
        this.getTextUi().printOutputMessage(ExitCommand.GOODBYE_MESSAGE);
    }

    /**
     * Identifies this Command's keyword. Override this in subclasses so
     * toString() returns the correct String representation.
     *
     * @return String Keyword of this Command
     */
    @Override
    protected String getCommandKeyword() {
        return ExitCommand.COMMAND_KEYWORD;
    }

    /**
     * Returns a detailed user-friendly description of what this specific command does.
     *
     * @return String Detailed explanation of this command
     */
    @Override
    protected String getDetailedDescription() {
        return ExitCommand.COMMAND_DETAILED_DESCRIPTION;
    }

    /**
     * Identifies the feature that this Command is associated with. Override
     * this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword for the feature associated with this Command
     */
    @Override
    protected String getFeatureKeyword() {
        return ExitCommand.FEATURE_KEYWORD;
    }

    /**
     * Returns all the supported arguments for this Command.
     *
     * @return String All supported arguments for this Command
     */
    @Override
    protected String getSupportedCommandArguments() {
        return ExitCommand.COMMAND_ARGUMENTS;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Executes the specified command from the user.<br>
     * <p>
     * May throw Exceptions if command fails.
     */
    @Override
    public void execute() {
        printGoodbye();
    }

    /**
     * Validate the arguments and payloads from a commandMap generated by CommandParser <br>
     * <br>
     * The validation logic and strictness is up to the implementer. <br>
     * <br>
     * As a guideline, <code>isValidCommand</code> should minimally: <br>
     * <li>Verify that ALL MANDATORY arguments exist</li>
     * <li>Verify that ALL MANDATORY payloads exist</li>
     * <li>Safely verify the payload type (int, date, etc should be properly processed)</li>
     * <br>
     * Additionally, payload value cleanup (such as trimming) is also possible. <br>
     * As Java is pass (object reference) by value, any changes made to commandMap
     * will persist out of the function call.
     *
     * @param commandMap Argument-Payload map generated by CommandParser
     * @throws BadCommandException if the commandMap has any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> commandMap) throws BadCommandException {
        if (!commandMap.containsKey(ExitCommand.COMMAND_KEYWORD)) {
            throw new BadCommandException(ExitCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
    }
}
