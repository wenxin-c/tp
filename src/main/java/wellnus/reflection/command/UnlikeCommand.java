package wellnus.reflection.command;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.ReflectionException;
import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.reflection.feature.IndexMapper;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectUi;

//@@author wenxin-c
/**
 * Unlike command to remove reflection questions from favorite questions.
 */
public class UnlikeCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "unlike - Remove a particular question "
            + "from favorite list.";
    public static final String COMMAND_USAGE = "usage: unlike (index)";
    public static final String COMMAND_KEYWORD = "unlike";
    private static final String FEATURE_NAME = "reflect";
    private static final String INVALID_COMMAND_MSG = "Invalid command issued, expected 'unlike'!";
    private static final String INVALID_COMMAND_NOTES = "Please try 'help' command to check the "
            + "available commands and their usages!";
    private static final String WRONG_INDEX_MSG = "Index is out of range!";
    private static final String WRONG_INDEX_NOTE = "Please input the correct index of the question you want to "
            + "remove from your favorite list!";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key should be unlike.";
    private static final String EMPTY_FAV_LIST_MSG = "The favorite list is empty, there is nothing to be removed.";
    private static final String TOKENIZER_ERROR = "The data cannot be tokenized for storage properly!!";
    private static final String STORAGE_ERROR = "The file data cannot be stored properly!!";
    private static final int INDEX_ZERO = 0;
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final int LOWER_BOUND = 1;
    private static final int EMPTY_LIST = 0;
    private static final Logger LOGGER = Logger.getLogger("ReflectUnlikeCommandLogger");
    private static final ReflectUi UI = new ReflectUi();
    private Set<Integer> favQuestionIndexes;
    private QuestionList questionList;

    /**
     * Set up the argument-payload pairs for this command.<br/>
     * Pass in a questionList object from ReflectionManager to access the indexes of the liked questions.
     *
     * @param arguments Argument-payload pairs from users
     * @param questionList Object that contains the data about questions
     */
    public UnlikeCommand(HashMap<String, String> arguments, QuestionList questionList) {
        super(arguments);
        this.questionList = questionList;
        this.favQuestionIndexes = questionList.getDataIndex().get(INDEX_ZERO);
    }

    /**
     * Get the command itself.
     *
     * @return Command: unlike
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Get the name of the feature in which this unlike command is generated.
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
     * Validate the command.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: like
     * <li>Payload must be string which parse into integer ranges from 1 to 5
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
        }
    }

    /**
     * Entry point to this command.<br/>
     * Check the validity of commands and add into favorite list.<br/>
     */
    @Override
    public void execute() {
        try {
            validateCommand(getArguments());
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.INFO, INVALID_COMMAND_MSG);
            UI.printErrorFor(badCommandException, INVALID_COMMAND_NOTES);
            return;
        }
        try {
            removeFavQuestion(getArguments().get(COMMAND_KEYWORD));
        } catch (TokenizerException tokenizerException) {
            LOGGER.log(Level.WARNING, TOKENIZER_ERROR);
            UI.printErrorFor(tokenizerException, TOKENIZER_ERROR);
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            UI.printErrorFor(storageException, STORAGE_ERROR);
        } catch (NumberFormatException numberFormatException) {
            LOGGER.log(Level.INFO, WRONG_INDEX_MSG);
            UI.printErrorFor(numberFormatException, WRONG_INDEX_NOTE);
        } catch (ReflectionException reflectionException) {
            UI.printOutputMessage(reflectionException.getMessage());
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.INFO, INVALID_COMMAND_MSG);
            UI.printErrorFor(badCommandException, INVALID_COMMAND_NOTES);
        }
    }

    /**
     * Remove the user input index from favorite list and print the question to be removed.<br/>
     * <br/>
     * A valid index will only be removed(i.e. passed validateCommand()) if the favorite list in not empty.
     *
     * @param questionIndex User input of the index of question to be removed from favorite list.
     * @throws TokenizerException If there is error in tokenization of index
     * @throws StorageException If there is error in storing the data
     */
    public void removeFavQuestion(String questionIndex) throws TokenizerException, StorageException,
            NumberFormatException, ReflectionException, BadCommandException {
        int questionIndexInt = Integer.parseInt(questionIndex);
        if (this.favQuestionIndexes.size() == EMPTY_LIST) {
            throw new ReflectionException(EMPTY_FAV_LIST_MSG);
        }
        if (questionIndexInt > this.favQuestionIndexes.size() || questionIndexInt < LOWER_BOUND) {
            throw new BadCommandException(WRONG_INDEX_MSG);
        }
        IndexMapper indexMapper = new IndexMapper(this.favQuestionIndexes);
        HashMap<Integer, Integer> indexQuestionMap = indexMapper.mapIndex();
        int indexToRemove = indexQuestionMap.get(questionIndexInt);
        questionList.removeFavListIndex(indexToRemove);
    }
}
