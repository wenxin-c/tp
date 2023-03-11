package seedu.duke.reflection;

import seedu.duke.command.Command;
import seedu.duke.exception.BadCommandException;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.manager.Manager;

import java.util.ArrayList;
import java.util.HashMap;

public class ReflectionManager extends Manager {
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String BRIEF_DESCRIPTION = "Users can get a random set of questions to reflect on.";
    private static final String FULL_DESCRIPTION = "";
    private static String commandType;
    private static HashMap<String, String> argumentPayload;
    private static final String GET_COMMAND = "get";
    private static final String RETURN_MAIN = "return";
    private static final String EXIT_COMMAND = "exit";
    private static final CommandParser parser;
    private ArrayList<Command> supportedCommands;
    private ArrayList<Manager> supportedManagers;

    public ReflectionManager() {
        this.parser = getCommandParser();
        this.supportedCommands = getSupportedCommands();
        this.supportedManagers = getSupportedFeatureManagers();
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
     * Get a full description of self reflection feature.
     * <br/>
     * FULL_DESCRIPTION is not completed yet!
     *
     * @return Full description of self reflection
     */
    @Override
    public String getFullDescription() {
        return FULL_DESCRIPTION;
    }

    @Override
    protected void setSupportedCommands() {
        supportedCommands.add();
    }

    @Override
    protected void setSupportedFeatureManagers() {

    }

    @Override
    public void runEventDriver() {
        SelfReflection selfReflection = new SelfReflection();
        selfReflection.run();
    }

    @Override
    public void validateCommand(HashMap<String, String> commandMap) {

    }

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



