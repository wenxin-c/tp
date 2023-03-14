package wellnus.reflection;

import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ReflectionManager extends Manager {
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String BRIEF_DESCRIPTION = "Users can get a random set of questions to reflect on.";
    private static final String FULL_DESCRIPTION = "";
    private static final String GET_COMMAND = "get";
    private static final String GET_PAYLOAD = "";
    private static final String RETURN_MAIN = "return";
    private static final String RETURN_PAYLOAD = "";
    private static final String EXIT_COMMAND = "exit";
    private static final String EXIT_PAYLOAD = "";
    private static final String NO_ELEMENT_MESSAGE = "There is no new line of input, please key in inputs.";
    private static final String INVALID_COMMAND_MESSAGE = "Please check the available commands "
            + "and enter a valid command.";
    private static final ReflectUi UI = new ReflectUi();

    // I need to set this as static if I want to set it to true if ExitCommand object.
    // If I create another object in ExitCommand, the corresponding isExit will be for a new object,
    // not the one we intend to terminate.
    private static boolean isExit;
    private String commandType;
    private HashMap<String, String> argumentPayload;

    public ReflectionManager() {
        isExit = false;
    }

    public HashMap<String, String> getArgumentPayload() {
        return argumentPayload;
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * Method to be called to change self reflection status.
     * <br/>
     * True: self reflection exit<br/>
     * False: self reflection status reset<br/>
     *
     * @param status Whether the feature exit or not exit
     */
    public static void setIsExit(boolean status) {
        isExit = status;
    }

    /**
     * Get self reflection exit status.
     *
     * @return Exit status
     */
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
     * Set up the set of command-payload pair supported by self reflection.<br/>
     * <li>Command: get, Payload: ""
     * <li>Command: exit, Payload: ""
     * <li>Command: return, Payload: ""
     */
    @Override
    protected void setSupportedCommands() {
        try {
            HashMap<String, String> getCmdArgumentPayload = new HashMap<>();
            getCmdArgumentPayload.put(GET_COMMAND, GET_PAYLOAD);
            GetCommand getCmd = new GetCommand(getCmdArgumentPayload);
            HashMap<String, String> returnCmdArgumentPayload = new HashMap<>();
            returnCmdArgumentPayload.put(RETURN_MAIN, RETURN_PAYLOAD);
            GetCommand returnCmd = new GetCommand(returnCmdArgumentPayload);
            HashMap<String, String> exitCmdArgumentPayload = new HashMap<>();
            exitCmdArgumentPayload.put(EXIT_COMMAND, EXIT_PAYLOAD);
            GetCommand exitCmd = new GetCommand(exitCmdArgumentPayload);
            supportedCommands.add(getCmd);
            supportedCommands.add(returnCmd);
            supportedCommands.add(exitCmd);
        } catch (BadCommandException badCommandException) {
            UI.printErrorFor(badCommandException, INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Set command argument and payload pairs.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException If an invalid command was given
     */
    public void setArgumentPayload(String inputCommand) throws BadCommandException {
        argumentPayload = commandParser.parseUserInput(inputCommand);
    }

    /**
     * Get the main command type to determine which command to create.
     *
     * @param inputCommand Read from user input
     * @throws BadCommandException If an invalid command was given
     */
    public void setCommandType(String inputCommand) throws BadCommandException {
        String mainArgument = commandParser.getMainArgument(inputCommand);
        commandType = mainArgument;
    }

    /**
     * Main entry point of self reflection section.<br/>
     * <br/>
     * It first prints out greeting messages.<br/>
     * Then listen to and execute user commands.
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
                UI.printErrorFor(noSuchElement, NO_ELEMENT_MESSAGE);
            } catch (BadCommandException badCommand) {
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
     * <li>Exit program<br/>
     *
     * @throws BadCommandException If an invalid command was given
     */
    public void executeCommands() throws BadCommandException {
        switch (commandType) {
        case GET_COMMAND:
            GetCommand getQuestionsCmd = new GetCommand(argumentPayload);
            getQuestionsCmd.execute();
            break;
        case RETURN_MAIN:
            ReturnCommand returnCmd = new ReturnCommand(argumentPayload);
            returnCmd.execute();
            break;
        case EXIT_COMMAND:
            ExitCommand exitCmd = new ExitCommand(argumentPayload);
            exitCmd.execute();
            break;
        default:
            throw new BadCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}

