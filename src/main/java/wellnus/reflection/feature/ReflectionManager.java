package wellnus.reflection.feature;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;
import wellnus.reflection.command.FavoriteCommand;
import wellnus.reflection.command.GetCommand;
import wellnus.reflection.command.HelpCommand;
import wellnus.reflection.command.HomeCommand;
import wellnus.reflection.command.LikeCommand;
import wellnus.reflection.command.PrevCommand;
import wellnus.reflection.command.UnlikeCommand;

/**
 * The manager for self reflection section.<br/>
 * This class oversees the command execution for self reflection section.
 */
public class ReflectionManager extends Manager {
    public static final String FEATURE_HELP_DESCRIPTION = "reflect - Self Reflection - Take some time to pause "
            + "and reflect with our specially curated list of questions and reflection management tools.";
    public static final String FEATURE_NAME = "reflect";
    private static final Logger LOGGER = WellNusLogger.getLogger("ReflectionManagerLogger");
    private static final String GET_COMMAND = "get";
    private static final String HOME_COMMAND = "home";
    private static final String HELP_COMMAND = "help";
    private static final String LIKE_COMMAND = "like";
    private static final String UNLIKE_COMMAND = "unlike";
    private static final String FAV_COMMAND = "fav";
    private static final String PREV_COMMAND = "prev";
    private static final String NO_ELEMENT_MESSAGE = "There is no new line of input, please key in inputs!";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command issued!";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INVALID_COMMAND_NOTES =
            "Supported commands in Self Reflection: " + LINE_SEPARATOR
            + "get command " + GetCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "like command " + LikeCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "unlike command " + UnlikeCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "fav command " + FavoriteCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "prev command " + PrevCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "help command " + HelpCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "home command " + HomeCommand.COMMAND_USAGE;
    private static final String COMMAND_TYPE_ASSERTION = "Command type should have length greater than 0";
    private static final String ARGUMENT_PAYLOAD_ASSERTION = "Argument-payload pairs cannot be empty";
    private static final String LOGO =
            "  #####                       ######                                           \n"
                    + " #     # ###### #      ###### #     # ###### ###### #      ######  ####  ##### \n"
                    + " #       #      #      #      #     # #      #      #      #      #    #   #   \n"
                    + "  #####  #####  #      #####  ######  #####  #####  #      #####  #        #   \n"
                    + "       # #      #      #      #   #   #      #      #      #      #        #   \n"
                    + " #     # #      #      #      #    #  #      #      #      #      #    #   #   \n"
                    + "  #####  ###### ###### #      #     # ###### #      ###### ######  ####    #   \n";
    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Self Reflection section :D"
            + System.lineSeparator() + "Feel very occupied and cannot find time to self reflect?"
            + System.lineSeparator() + "No worries, this section will give you the opportunity to reflect "
            + "and improve on yourself!!";
    private static final int EMPTY_COMMAND = 0;
    private static final boolean IS_EXIT_INITIAL = false;
    private static final ReflectUi UI = new ReflectUi();
    private static boolean isExit;
    private String commandType;
    private HashMap<String, String> argumentPayload;
    private QuestionList questionList = new QuestionList();

    /**
     * Constructor to set initial isExit status to false and load the reflection questions.
     */
    public ReflectionManager() {
        setIsExit(IS_EXIT_INITIAL);
        this.UI.setCursorName(FEATURE_NAME);
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
                LOGGER.log(Level.INFO, badCommand.getMessage());
                UI.printErrorFor(badCommand, INVALID_COMMAND_NOTES);
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
        case HELP_COMMAND:
            HelpCommand helpCmd = new HelpCommand(argumentPayload);
            helpCmd.execute();
            break;
        case HOME_COMMAND:
            HomeCommand returnCmd = new HomeCommand(argumentPayload, questionList);
            returnCmd.execute();
            break;
        case LIKE_COMMAND:
            LikeCommand likeCmd = new LikeCommand(argumentPayload, questionList);
            likeCmd.execute();
            break;
        case UNLIKE_COMMAND:
            UnlikeCommand unlikeCmd = new UnlikeCommand(argumentPayload, questionList);
            unlikeCmd.execute();
            break;
        case FAV_COMMAND:
            FavoriteCommand favCmd = new FavoriteCommand(argumentPayload, questionList);
            favCmd.execute();
            break;
        case PREV_COMMAND:
            PrevCommand prevCmd = new PrevCommand(argumentPayload, questionList);
            prevCmd.execute();
            break;
        default:
            throw new BadCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}

