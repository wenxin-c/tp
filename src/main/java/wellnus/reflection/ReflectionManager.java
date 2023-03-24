package wellnus.reflection;

import java.util.*;
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
    private static final String LIKE_COMMAND = "like";
    private static final String LIKE_PAYLOAD = "1";
    private static final String FAV_COMMAND = "fav";
    private static final String FAV_PAYLOAD = "";
    private static final String NO_ELEMENT_MESSAGE = "There is no new line of input, please key in inputs.";
    private static final String INVALID_COMMAND_MESSAGE = "Please check the available commands "
            + "and enter a valid command.";
    private static final int EMPTY_COMMAND = 0;
    private static final String COMMAND_TYPE_ASSERTION = "Command type should have length greater than 0";

    // TODO: Update with more commands being added
    private static final int NUM_SUPPORTED_COMMANDS = 4;
    private static final String SUPPORTED_COMMANDS_ASSERTION = "The number of supported commands should be 4";
    private static final String ARGUMENT_PAYLOAD_ASSERTION = "Argument-payload pairs cannot be empty";
    private static final boolean INITIAL_EXIT_STATUS = false;
    private static final boolean INITIAL_GET_STATUS = false;
    private static final ReflectUi UI = new ReflectUi();

    // This attribute should be set as static to avoid confusion if a new object is created.
    // It means exit from self reflection back to main interface
    private static boolean isExit;

    // This is to check whether users have get a set of questions before like command
    // It is set to true by GetCommand and reset to false by HomeCommand
    private static boolean hasGetQuestions;
    private static Set<Integer> randomQuestionIndexes;
    private static ArrayList<HashSet<Integer>> dataIndex;
    private String commandType;
    private HashMap<String, String> argumentPayload;

    /**
     * Constructor to set initial isExit status to false and load the reflection questions.
     */
    public ReflectionManager() {
        setIsExit(INITIAL_EXIT_STATUS);
        setSupportedCommands();
        setHasGetQuestions(INITIAL_GET_STATUS);
        randomQuestionIndexes = new HashSet<>();
        // TODO: Load data from file through tokenizer
        this.dataIndex = new ArrayList<>();
        HashSet<Integer> setLike = new HashSet<>();
        // TODO: create a prev set here
        this.dataIndex.add(setLike);
    }

    public static void setIsExit(boolean status) {
        isExit = status;
    }

    public static boolean getIsExit() {
        return isExit;
    }

    public static boolean getHasGetQuestions() {
        return hasGetQuestions;
    }

    public static void setHasGetQuestions(boolean status) {
        hasGetQuestions = status;
    }

    public static void setRandomQuestionIndexes(Set<Integer> randomQuestionIndexes) {
        ReflectionManager.randomQuestionIndexes = randomQuestionIndexes;
    }

    public static void clearRandomQuestionIndexes() {
        randomQuestionIndexes.clear();
    }

    public static Set<Integer> getRandomQuestionIndexes() {
        return randomQuestionIndexes;
    }

    public static ArrayList<HashSet<Integer>> getDataIndex() {
        return dataIndex;
    }

    public static void setDataIndex(ArrayList<HashSet<Integer>> dataIndex) {
        ReflectionManager.dataIndex = dataIndex;
    }

    public HashMap<String, String> getArgumentPayload() {
        return argumentPayload;
    }

    public String getCommandType() {
        return commandType;
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
     * <li>Command: like, Payload: "1"(Integer from 1 to 5)
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
            HashMap<String, String> likeCmdArgumentPayload = new HashMap<>();
            likeCmdArgumentPayload.put(LIKE_COMMAND, LIKE_PAYLOAD);
            LikeCommand likeCmd = new LikeCommand(likeCmdArgumentPayload);
            HashMap<String, String> favCmdArgumentPayload = new HashMap<>();
            favCmdArgumentPayload.put(FAV_COMMAND, FAV_PAYLOAD);
            FavoriteCommand favCmd = new FavoriteCommand(favCmdArgumentPayload);
            supportedCommands.add(getCmd);
            supportedCommands.add(returnCmd);
            supportedCommands.add(likeCmd);
            supportedCommands.add(favCmd);
        } catch (BadCommandException | NumberFormatException badCommandException) {
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
        commandType = commandParser.getMainArgument(inputCommand);
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
            break;
        case LIKE_COMMAND:
            LikeCommand likeCmd = new LikeCommand(argumentPayload);
            likeCmd.execute();
            break;
        case FAV_COMMAND:
            FavoriteCommand favCmd = new FavoriteCommand(argumentPayload);
            favCmd.execute();
            break;
        default:
            throw new BadCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}

