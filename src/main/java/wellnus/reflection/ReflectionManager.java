package wellnus.reflection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;

/**
 * The manager for self reflection section.<br/>
 * This class oversees the command execution for self reflection section.
 */
public class ReflectionManager extends Manager {
    public static final String FEATURE_HELP_DESCRIPTION = "Reflection - Take some time to pause and reflect "
            + "with our specially curated list of questions and reflection management tools.";
    private static final Logger LOGGER = Logger.getLogger("ReflectionManagerLogger");
    private static final String FEATURE_NAME = "reflect";
    private static final String GET_COMMAND = "get";
    private static final String HOME_COMMAND = "home";
    private static final String HELP_COMMAND = "help";
    private static final String LIKE_COMMAND = "like";
    private static final String FAV_COMMAND = "fav";
    private static final String NO_ELEMENT_MESSAGE = "There is no new line of input, please key in inputs.";
    private static final String INVALID_COMMAND_MESSAGE = "Please check the available commands "
            + "and enter a valid command.";
    private static final String COMMAND_TYPE_ASSERTION = "Command type should have length greater than 0";
    private static final String ARGUMENT_PAYLOAD_ASSERTION = "Argument-payload pairs cannot be empty";
    private static final String LOGO =
            "  _____ ______ _      ______   _____  ______ ______ _      ______ _____ _______ _____ ____  _   _ \n"
                    + " / ____|  ____| |    |  ____| |  __ \\|  ____|  ____| "
                    + "|    |  ____/ ____|__   __|_   _/ __ \\| \\ | |\n"
                    + "| (___ | |__  | |    | |__    | |__) | |__  | |__  | "
                    + "|    | |__ | |       | |    | || |  | |  \\| |\n"
                    + " \\___ \\|  __| | |    |  __|   |  _  /|  __| |  __| "
                    + "| |    |  __|| |       | |    | || |  | | . ` |\n"
                    + " ____) | |____| |____| |      | | \\ \\| |____| |    "
                    + "| |____| |___| |____   | |   _| || |__| | |\\  |\n"
                    + "|_____/|______|______|_|      |_|  \\_\\______|_|    "
                    + "|______|______\\_____|  |_|  "
                    + "|_____\\____/|_| \\_|\n";
    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Self Reflection section :D"
            + System.lineSeparator() + "Feel very occupied and cannot find time to self reflect?"
            + System.lineSeparator() + "No worries, this section will give you the opportunity to reflect "
            + "and improve on yourself!!";
    private static final int EMPTY_COMMAND = 0;
    private static final boolean IS_EXIT_INITIAL = false;
    private static final Integer[] ARR_INDEXES = { 5, 6, 7, 8, 1};
    private static final Set<Integer> RANDOM_INDEXES = new HashSet<>(Arrays.asList(ARR_INDEXES));
    private static final ReflectUi UI = new ReflectUi();
    // This attribute should be set as static to avoid confusion if a new object is created.
    // It means exit from self reflection back to main interface
    private static boolean isExit;
    private String commandType;
    private HashMap<String, String> argumentPayload;
    private QuestionList questionList = new QuestionList();

    /**
     * Constructor to set initial isExit status to false and load the reflection questions.
     */
    public ReflectionManager() {
        setIsExit(IS_EXIT_INITIAL);
    }

    public static void setIsExit(boolean status) {
        isExit = status;
    }

    public static boolean getIsExit() {
        return isExit;
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
     * @return Feature name: reflect
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * Abstract function to ensure developers add in a getter for the feature's help description.
     * <p>
     * This description will be shown when the user types in the help command. <br>
     * The description should be a brief overview of what the feature does. <br>
     * For example: <br>
     * "reflect: Reflect is your go-to tool to get, save and reflect on our specially
     * curated list of questions to reflect on"
     *
     * @return String of the feature's help description
     */
    @Override
    public String getFeatureHelpDescription() {
        return FEATURE_HELP_DESCRIPTION;
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

    //@@author wenxin-c
    /**
     * Print greeting logo and message.
     */
    public void greet() {
        UI.printLogoWithSeparator(LOGO);
        UI.printOutputMessage(GREETING_MESSAGE);
    }
    //@@author

    //@@author wenxin-c
    /**
     * Main entry point of self reflection section.<br/>
     * <br/>
     * It prints out greeting messages, listen to and execute user commands.
     */
    @Override
    public void runEventDriver() {
        setIsExit(false);
        this.greet();
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
    //@@author

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
            GetCommand getQuestionsCmd = new GetCommand(argumentPayload, questionList);
            getQuestionsCmd.execute();
            break;
        case HOME_COMMAND:
            HomeCommand returnCmd = new HomeCommand(argumentPayload, questionList);
            returnCmd.execute();
            break;
        case LIKE_COMMAND:
            LikeCommand likeCmd = new LikeCommand(argumentPayload, questionList);
            likeCmd.execute();
            break;
        case FAV_COMMAND:
            FavoriteCommand favCmd = new FavoriteCommand(argumentPayload, questionList);
            favCmd.execute();
            break;
        case HELP_COMMAND:
            HelpCommand helpCmd = new HelpCommand(argumentPayload);
            helpCmd.execute();
            break;
        default:
            throw new BadCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}

