package wellnus.command;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.exception.BadCommandException;

//@@author nichyjt

/**
 * A CommandParser processes user input from a defined format <p>
 * <p><br>
 * Each user input via console consists of: <br>
 * <ol>
 * <li>COMMANDS - A Argument and Payload pairs </li><br>
 * <li>ARGUMENTS - String representing the action/parameters of the command </li><br>
 * <li>PAYLOADS - value of the action/parameters </li><br>
 * </ol>
 * <p>
 * In short, user input is a list of commands, each command containing arguments and payloads. <br><br>
 * Further, we define the FIRST command to be the MAIN command of any given user input. <br>
 * So, <code>"deadline work on CS2113 --by Sunday"</code> has <code>"deadline work on CS2113"</code>
 * as the main command <br><br>
 * Each command (argument-payload pair) except for the main command MUST
 * be delimited by <code>" --"</code> (whitespace intentional)
 * <br><br>
 * For example, a given user input: <code>"deadline work on CS2113 --by Sunday"</code>
 * <li>Has commands ["deadline work on CS2113", "by Sunday"]</li>
 * <li>Has arguments ["deadline", "by"]</li>
 * <li>Has payloads ["work on CS2113", ["Sunday"]</li>
 * <br>
 */
public class CommandParser {

    private static final String ARGUMENT_DELIMITER = " --";
    private static final String UNPADDED_DELIMITER = "--";
    private static final String PAYLOAD_DELIMITER = " ";

    // Message string constants for errors and ui
    private static final String ERROR_EMPTY_COMMAND = "Invalid command issued, command cannot be empty!";
    private static final String ERROR_EMPTY_ARGUMENT = "Invalid arguments given, command is missing an argument!";
    private static final String ERROR_REPEATED_ARGUMENT = "Invalid arguments given, command cannot have "
            + "repeated arguments!";
    private static final Logger LOGGER = WellNusLogger.getLogger("CommandParserLogger");
    private static final String LOG_STR_EMPTY_INPUT = "Input string is empty. This should be properly handled";
    private static final String LOG_EMPTY_ARG = "Argument is empty. This should be properly handled";

    /**
     * Constructs an instance of CommandParser. <br>
     * <p>
     * CommandParser should be used to break down raw user input into
     * logical <code>[Argument,Payload]</code> pairs
     */
    public CommandParser() {

    }

    /**
     * Split a userInput by the standardized delimiter.
     * <p>
     * This function handles some adversarial user input.
     * There are 2 possible adversarial inputs that this function checks for:
     * <p>
     * 1. Whitespace/Empty Arguments: `cmd payload -- payload1 -- ` <br>
     * Split renders it as ["cmd payload", " payload1", ""]
     * " payload1" will cause issues with rendering
     * So, check for empty commands and whitespace prefix. <br>
     * <p>
     * 2. Missing main argument: `--argument payload` <br>
     * Split renders this as ["--argument payload"]
     * So, check for "--" prefix.
     *
     * @param fullCommandString Raw user input from stdin in string form
     * @return String array of command substrings
     * @throws BadCommandException when command is empty or is problematic
     */
    private String[] splitIntoCommands(String fullCommandString) throws BadCommandException {
        assert fullCommandString != null : "fullCommandString should not be null";
        // Perform a string length sanity check
        fullCommandString = fullCommandString.strip();
        if (fullCommandString.length() == 0) {
            LOGGER.log(Level.INFO, LOG_STR_EMPTY_INPUT);
            throw new BadCommandException(ERROR_EMPTY_COMMAND);
        }
        int noLimit = -1;
        String[] rawCommands = fullCommandString.split(ARGUMENT_DELIMITER, noLimit);
        String[] cleanCommands = new String[rawCommands.length];
        for (int i = 0; i < rawCommands.length; ++i) {
            String currentCommand = rawCommands[i];
            // Case 1 check
            if (currentCommand.startsWith(" ") || currentCommand.length() == 0) {
                LOGGER.log(Level.INFO, LOG_EMPTY_ARG);
                throw new BadCommandException(ERROR_EMPTY_ARGUMENT);
            }
            // Strip command of whitespace to clean input
            currentCommand = currentCommand.strip();
            // Case 2 check
            if (currentCommand.startsWith(UNPADDED_DELIMITER)) {
                LOGGER.log(Level.INFO, LOG_EMPTY_ARG);
                throw new BadCommandException(ERROR_EMPTY_COMMAND);
            }
            cleanCommands[i] = currentCommand;
        }
        return cleanCommands;
    }

    private String getArgumentFromCommand(String commandString) throws BadCommandException {
        assert commandString != null : "commandString should not be null";

        String[] words = commandString.split(PAYLOAD_DELIMITER);
        // Bad input checks
        if (words.length == 0) {
            LOGGER.log(Level.INFO, LOG_STR_EMPTY_INPUT);
            throw new BadCommandException(ERROR_EMPTY_ARGUMENT);
        }
        return words[0].toLowerCase().strip();
    }

    private String getPayloadFromCommand(String commandString) {
        assert commandString != null : "commandString should not be null";

        String[] words = commandString.split(PAYLOAD_DELIMITER);
        String payload = "";
        // Ignore the first word (Main Command), so start from 1
        for (int i = 1; i < words.length; ++i) {
            payload = payload.concat(words[i]);
            if (i != words.length - 1) {
                payload = payload.concat(PAYLOAD_DELIMITER);
            }
        }
        // No checks for payload length is done as payload CAN be empty
        return payload.strip();
    }

    /**
     * Takes in raw user input and splits it into Argument-Payload pairs
     *
     * @param userInput Raw user input from stdin in string form
     * @return HashMap mapping a Argument (key) to a Payload (value)
     * @throws BadCommandException when command is empty or is problematic
     */
    public HashMap<String, String> parseUserInput(String userInput) throws BadCommandException {
        assert userInput != null : "userInput should not be null";

        if (userInput.length() == 0) {
            LOGGER.log(Level.INFO, LOG_STR_EMPTY_INPUT);
            throw new BadCommandException(ERROR_EMPTY_COMMAND);
        }
        HashMap<String, String> argumentPayload = new HashMap<>();
        String[] commands = splitIntoCommands(userInput);
        for (String command : commands) {
            String argument = getArgumentFromCommand(command);
            // Safety check if arguments already exists
            if (argumentPayload.containsKey(argument)) {
                throw new BadCommandException(ERROR_REPEATED_ARGUMENT);
            }
            String payload = getPayloadFromCommand(command);
            argumentPayload.put(argument, payload);
        }
        return argumentPayload;
    }

    /**
     * Takes in a string and returns the inferred "Main Argument"
     * <br>
     * Practically, this is the <b>First</b> argument of any command string.
     * For example, <code>"hb add --name foobar"</code> <br>
     * Has main argument "hb"
     *
     * @param userInput Any string input representing a command
     * @return the inferred Main Argument, converted to lowercase
     * @throws BadCommandException when String is empty
     */
    public String getMainArgument(String userInput) throws BadCommandException {
        assert userInput != null : "userInput should not be null";

        userInput = userInput.strip();
        if (userInput.length() == 0) {
            LOGGER.log(Level.INFO, LOG_STR_EMPTY_INPUT);
            throw new BadCommandException(ERROR_EMPTY_COMMAND);
        }
        String[] parameters = userInput.split(" ");
        return parameters[0].toLowerCase();
    }
}
