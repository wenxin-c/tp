package wellnus.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;

/**
 * Get all the questions that are in the favorite list.
 *
 * @@author wenxin-c
 */
public class FavoriteCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "fav - Get the list of questions that have been added to "
            + "the favorite list.";
    public static final String COMMAND_USAGE = "usage: fav";
    private static final String COMMAND_KEYWORD = "fav";
    private static final String PAYLOAD = "";
    private static final String FEATURE_NAME = "reflect";
    private static final String INVALID_COMMAND_MSG = "Command is invalid.";
    private static final String INVALID_COMMAND_NOTES = "Please check the available commands "
            + "and the format of commands.";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key should be fav.";
    private static final String COMMAND_PAYLOAD_ASSERTION = "The payload should be empty.";
    private static final String EMPTY_FAV_LIST = "There is nothing in favorite list, "
            + "please get reflection questions first!!";
    private static final String DOT = ".";
    private static final String EMPTY_STRING = "";
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INCREMENT_ONE = 1;
    private static final Logger LOGGER = Logger.getLogger("ReflectFavCommandLogger");
    private static final ReflectUi UI = new ReflectUi();
    private ArrayList<HashSet<Integer>> dataIndex;
    private HashMap<String, String> argumentPayload;
    private QuestionList questionList;

    /**
     * Set up the argument-payload pairs for this command.<br/>
     * Pass in a questionList object from ReflectionManager to access the list of favorite questions.
     *
     * @param arguments Argument-payload pairs from users
     * @param questionList Object that contains the data about questions
     * @throws BadCommandException If an invalid command is given
     */
    public FavoriteCommand(HashMap<String, String> arguments, QuestionList questionList) throws BadCommandException {
        super(arguments);
        this.argumentPayload = getArguments();
        this.questionList = questionList;
        this.dataIndex = questionList.getDataIndex();
    }

    /**
     * Get the command itself.
     *
     * @return Command: get
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Get the name of the feature in which this get command is generated.
     *
     * @return Feature name: reflect
     */
    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
    }

    /**
     * Method to ensure that developers add in a command usage.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "usage: add --name (name of habit)"
     *
     * @return String of the proper usage of the habit
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Method to ensure that developers add in a description for the command.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "add - add a habit to your list"
     *
     * @return String of the description of what the command does
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Get a string of all favorite questions based on the favorite question indexes.
     *
     * @return String of favorite questions
     */
    public String getFavQuestions() {
        ArrayList<ReflectionQuestion> questions = questionList.getAllQuestions();
        String questionString = EMPTY_STRING;
        int displayIndex = INDEX_ONE;
        for (int questionIndex : this.dataIndex.get(INDEX_ZERO)) {
            questionString += (displayIndex + DOT + questions.get(questionIndex).toString()
                    + System.lineSeparator());
            displayIndex += INCREMENT_ONE;
        }
        return questionString;
    }

    /**
     * Entry point to this command.<br/>
     */
    @Override
    public void execute() {
        try {
            validateCommand(this.argumentPayload);
        } catch (BadCommandException invalidCommand) {
            LOGGER.log(Level.INFO, INVALID_COMMAND_MSG);
            UI.printErrorFor(invalidCommand, INVALID_COMMAND_NOTES);
            return;
        }
        assert argumentPayload.containsKey(COMMAND_KEYWORD) : COMMAND_KEYWORD_ASSERTION;
        assert argumentPayload.get(COMMAND_KEYWORD).equals(PAYLOAD) : COMMAND_PAYLOAD_ASSERTION;
        if (this.dataIndex.get(INDEX_ZERO).isEmpty()) {
            UI.printOutputMessage(EMPTY_FAV_LIST);
            return;
        }
        String outputString = getFavQuestions();
        UI.printOutputMessage(outputString);
    }

    /**
     * Validate the command.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: fav
     * <li>Payload is empty
     * Whichever mismatch will cause the command to be invalid.
     *
     * @param commandMap Argument-Payload map generated by CommandParser
     * @throws BadCommandException If an invalid command is given
     */
    @Override
    public void validateCommand(HashMap<String, String> commandMap) throws BadCommandException {
        if (commandMap.size() != ARGUMENT_PAYLOAD_SIZE) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.containsKey(COMMAND_KEYWORD)) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.get(COMMAND_KEYWORD).equals(PAYLOAD)) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        }
    }
}

