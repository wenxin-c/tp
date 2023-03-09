package seedu.duke.reflection;

import seedu.duke.command.BadCommandException;
import seedu.duke.command.CommandParser;

import java.util.HashMap;

public class CommandManager {

    private static final CommandParser parser = new CommandParser();
    private static String commandType;
    private static HashMap<String, String> argumentPayload;
    private static final String UNKNOWN_COMMAND_TYPE = "Sorry I don't understand this command, please try again!!";

    public static void setArgumentPayload(String inputCommand) throws BadCommandException {
        argumentPayload = parser.parseUserInput(inputCommand);
    }

    public static void setCommandType(String inputCommand) throws BadCommandException {
        String mainArgument = parser.getMainArgument(inputCommand);
        commandType = commandType;
    }

    public static void execute(String inputCommand) throws BadCommandException {
        setCommandType(inputCommand);
        setArgumentPayload(inputCommand);
        switch (commandType) {
        case "get":
            GetCommand getQuestionsCmd = new GetCommand();
            getQuestionsCmd.printRandomReflectQuestions();
            break;
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_TYPE);
        }
    }

}

