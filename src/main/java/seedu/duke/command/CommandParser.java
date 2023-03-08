package seedu.duke.command;

import java.util.HashMap;

/**
 * A CommandParser processes user input from a defined format <p>
 * <p><br>
 * Each user input via console consists of: <br>
 * 1. COMMANDS - A Argument and Payload pairs <br>
 * 2. ARGUMENTS - String representing the action/parameters of the command <br>
 * 3. PAYLOADS - value of the action/parameters <br>
 * In short, user input is a list of commands, each command containing arguments and payloads. <br>
 * <br>
 * For example, a given user input: <code>"deadline work on CS2113 --by Sunday"</code>
 * <li>Has commands ["deadline work on CS2113", "by Sunday"]</li>
 * <li>Has arguments ["deadline", "by"]</li>
 * <li>Has payloads ["work on CS2113", ["Sunday"]</li>
 * <br>
 * Further, we define the FIRST command to be the MAIN command of any given user input. <br>
 * So, <code>"deadline work on CS2113 --by Sunday"</code> has <code>"deadline work on CS2113"</code>
 * as the main command
 */
public class CommandParser {

    private static final String ARGUMENT_DELIMITER = " --";
    private static final String PAYLOAD_DELIMITER = " ";

    // Message string constants for errors and ui
    private static final String ERROR_EMPTY_COMMAND = "Command length is empty.";
    private static final String ERROR_EMPTY_ARGUMENT = "Command is missing an argument!";

    /**
     * Constructs an instance of CommandParser. <br>
     * <p>
     * CommandParser should be used to break down raw user input into
     * logical <code>[Argument,Payload]</code> pairs
     */
    public CommandParser() {

    }

    private String[] splitIntoCommands(String fullCommandString) {
        // Perform a string length sanity check
        assert fullCommandString.length() > 0;
        return fullCommandString.split(ARGUMENT_DELIMITER);
    }

    private String getArgumentFromCommand(String commandString) throws BadCommandException {
        String[] words = commandString.split(PAYLOAD_DELIMITER);
        // Bad input checks
        if (words.length == 0) {
            throw new BadCommandException(ERROR_EMPTY_ARGUMENT);
        }
        return words[0];
    }

    private String getPayloadFromCommand(String commandString) {
        String[] words = commandString.split(PAYLOAD_DELIMITER);
        String payload = "";
        for (int i = 0; i < words.length; ++i) {
            payload = payload.concat(words[i]);
            if (i != words.length - 1) {
                payload = payload.concat(PAYLOAD_DELIMITER);
            }
        }
        // No checks for payload length is done as payload CAN be empty
        return payload;
    }

    public HashMap<String, String> parseUserInput(String userInput) throws BadCommandException {
        if (userInput.length() == 0) {
            throw new BadCommandException(ERROR_EMPTY_COMMAND);
        }
        HashMap<String, String> argumentPayload = new HashMap<>();
        String[] commands = splitIntoCommands(userInput);
        for (String command : commands) {
            String argument = getArgumentFromCommand(command);
            String payload = getPayloadFromCommand(command);
            argumentPayload.put(argument, payload);
        }
        return argumentPayload;
    }

}
