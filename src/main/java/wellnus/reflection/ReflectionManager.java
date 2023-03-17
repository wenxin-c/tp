package wellnus.reflection;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;

/**
 * The manager for self reflection section.<br/>
 * This class oversees the command execution for self reflection section.
 */
public class ReflectionManager extends Manager {
    private static final Logger LOGGER = Logger.getLogger("ReflectionManagerLogger");
    private static final String FEATURE_NAME = "reflect";
    private static final String BRIEF_DESCRIPTION = "Users can get a random set of questions to reflect on.";
    private static final String FULL_DESCRIPTION = "";
    private static final String GET_COMMAND = "get";
    private static final String GET_PAYLOAD = "";
    private static final String HOME_COMMAND = "home";
    private static final String HOME_PAYLOAD = "";
    private static final String NO_ELEMENT_MESSAGE = "There is no new line of input, please key in inputs.";
    private static final String INVALID_COMMAND_MESSAGE = "Please check the available commands "
            + "and enter a valid command.";
    private static final String IS_EXIT_ASSERTION = "isExit should be true after exiting while loop";
    private static final int EMPTY_COMMAND = 0;
    private static final String COMMAND_TYPE_ASSERTION = "Command type should have length greater than 0";

    // TODO: Update with more commands being added
    private static final int NUM_SUPPORTED_COMMANDS = 2;
    private static final String SUPPORTED_COMMANDS_ASSERTION = "The number of supported commands should be 2";
    private static final String ARGUMENT_PAYLOAD_ASSERTION = "Argument-payload pairs cannot be empty";
    private static final boolean INITIAL_EXIT_STATUS = false;
    private static final ReflectUi UI = new ReflectUi();

    // This attribute should be set as static to avoid confusion if a new object is created.
    // It means exit from self reflection back to main interface
    private static boolean isExit;
    private String commandType;
    private HashMap<String, String> argumentPayload;

    /**
     * Constructor to set initial isExit status to false and load the reflection questions.
     */
    public ReflectionManager() {
        setIsExit(INITIAL_EXIT_STATUS);
        setSupportedCommands();
    }

    public HashMap<String, String> getArgumentPayload() {
        return argumentPayload;
    }

    public String getCommandType() {
        return commandType;
    }

    public static void setIsExit(boolean status) {
        isExit = status;
    }

    public static boolean getIsExit() {
        return isExit;
    }

    /**
     * Get Self Reflection feature name.
     *
     * @return Self Reflection feature name
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * Get a summary of description of self reflection feature.
     *
     * @return Brief description of self reflection
     */
    @Override
    public String getBriefDescription() {
        return BRIEF_DESCRIPTION;
    }

    /**
     * Get a full description of self reflection feature.<br/>
     * TODO: FULL_DESCRIPTION is not completed yet!
     *
     * @return Full description of self reflection
     */
    @Override
    public String getFullDescription() {
        return FULL_DESCRIPTION;
    }

    /**
     * Set up the set of command-payload pairs supported by self reflection.<br/>
     * <li>Command: get, Payload: ""
     * <li>Command: home, Payload: ""
     */
    @Override
    protected void setSupportedCommands() {
        try {
            HashMap<String, String> getCmdArgumentPayload = new HashMap<>();
            getCmdArgumentPayload.put(GET_COMMAND, GET_PAYLOAD);
            GetCommand getCmd = new GetCommand(getCmdArgumentPayload);
            HashMap<String, String> homeCmdArgumentPayload = new HashMap<>();
            homeCmdArgumentPayload.put(HOME_COMMAND, HOME_PAYLOAD);
            HomeCommand returnCmd = new HomeCommand(homeCmdArgumentPayload);
            supportedCommands.add(getCmd);
            supportedCommands.add(returnCmd);
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.INFO, INVALID_COMMAND_MESSAGE);
            UI.printErrorFor(badCommandException, INVALID_COMMAND_MESSAGE);
        }
        assert supportedCommands.size() == NUM_SUPPORTED_COMMANDS : SUPPORTED_COMMANDS_ASSERTION;
    }

    /**
     * Set command argument and payload pairs from user inputs.<br/>
     * This is to be used to generate command.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException If an invalid command was given
     */
    public void setArgumentPayload(String inputCommand) throws BadCommandException {
        argumentPayload = commandParser.parseUserInput(inputCommand);
        assert argumentPayload.size() > EMPTY_COMMAND : ARGUMENT_PAYLOAD_ASSERTION;
    }

    /**
     * Set the main command type to determine which command to create.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException If an invalid command was given
     */
    public void setCommandType(String inputCommand) throws BadCommandException {
        String mainArgument = commandParser.getMainArgument(inputCommand);
        commandType = mainArgument;
        assert commandType.length() > EMPTY_COMMAND : COMMAND_TYPE_ASSERTION;
    }

    /**
     * Main entry point of self reflection section.<br/>
     * <br/>
     * It prints out greeting messages, listen to and execute user commands.
     */
    @Override
    public void runEventDriver() {
        setIsExit(false);
        SelfReflection.greet();
        while (!isExit) {
            try {
                String inputCommand = UI.getCommand();
                setCommandType(inputCommand);
                setArgumentPayload(inputCommand);
                executeCommands();
            } catch (NoSuchElementException noSuchElement) {
                LOGGER.log(Level.INFO, NO_ELEMENT_MESSAGE);
                UI.printErrorFor(noSuchElement, NO_ELEMENT_MESSAGE);
            } catch (BadCommandException badCommand) {
                LOGGER.log(Level.INFO, INVALID_COMMAND_MESSAGE);
                UI.printErrorFor(badCommand, INVALID_COMMAND_MESSAGE);
            }
        }
    }

    /**
     * Decide which command to create based on command type.<br/>
     * <br/>
     * Commands available at this moment are:
     * <li>Get a random set of reflection questions<br/>
     * <li>Return back main interface<br/>
     *
     * @throws BadCommandException If an invalid command was given
     */
    public void executeCommands() throws BadCommandException {
        assert commandType.length() > EMPTY_COMMAND : COMMAND_TYPE_ASSERTION;
        switch (commandType) {
        case GET_COMMAND:
            GetCommand getQuestionsCmd = new GetCommand(argumentPayload);
            getQuestionsCmd.execute();
            break;
        case HOME_COMMAND:
            HomeCommand returnCmd = new HomeCommand(argumentPayload);
            returnCmd.execute();
            assert isExit : IS_EXIT_ASSERTION;
            break;
        default:
            throw new BadCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}

