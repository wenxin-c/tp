package seedu.duke.reflection;

import seedu.duke.command.BadCommandException;
import seedu.duke.command.CommandParser;

import java.util.HashMap;

public class CommandManager {

    private static final CommandParser parser = new CommandParser();
    private static String commandType;
    private static HashMap<String, String> argumentPayload;
    private static final String GET_COMMAND = "get";
    private static final String RETURN_MAIN = "return";
    private static final String EXIT_COMMAND = "exit";

    /**
     * Set command argument and payload pairs.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException Empty command
     */
    public static void setArgumentPayload(String inputCommand) throws BadCommandException {
        argumentPayload = parser.parseUserInput(inputCommand);
    }

    /**
     * Get the main command type to determine which command to create.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException Empty command
     */
    public static void setCommandType(String inputCommand) throws BadCommandException {
        String mainArgument = parser.getMainArgument(inputCommand);
        commandType = mainArgument;
    }

    public static String getCommandType() {
        return commandType;
    }

    public static HashMap<String, String> getArgumentPayload() {
        return argumentPayload;
    }

    /**
     * Decide which command to create based on command type.<br/>
     * <br/>
     * Commands available at this moment are:
     * <li>Get a random set of reflection questions<br/>
     * <li>Return back main interface<br/>
     * <li>Exit program<br/>
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException Empty command
     */
    public static void execute(String inputCommand) throws BadCommandException, InvalidCommandException {
        setCommandType(inputCommand);
        setArgumentPayload(inputCommand);
        switch (commandType) {
        case GET_COMMAND:
            GetCommand getQuestionsCmd = new GetCommand();
            getQuestionsCmd.execute();
            break;
        case RETURN_MAIN:
            ReturnCommand returnCmd = new ReturnCommand();
            returnCmd.execute();
            break;
        case EXIT_COMMAND:
            ExitCommand exitCmd = new ExitCommand();
            exitCmd.execute();
            break;
        default:
            InvalidCommand invalidCmd = new InvalidCommand();
            invalidCmd.alertError();
            break;
        }
    }
}



