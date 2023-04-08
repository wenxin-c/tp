package wellnus.reflection.command;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.command.Command;
import wellnus.common.WellNusLogger;
import wellnus.exception.BadCommandException;
import wellnus.exception.ReflectionException;
import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.reflection.feature.IndexMapper;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectUi;

//@@author wenxin-c
/**
 * Like command to add reflection questions into favorite list.
 */
public class LikeCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "like - Add a particular question to favorite list.";
    public static final String COMMAND_USAGE = "usage: like (index)";
    public static final String COMMAND_KEYWORD = "like";
    private static final String FEATURE_NAME = "reflect";
    private static final String INVALID_COMMAND_MSG = "Invalid command issued, expected 'like'!";
    private static final String INVALID_ARGUMENT_MSG = "Invalid arguments given to 'like'!";
    private static final String INVALID_COMMAND_NOTES = "like command " + COMMAND_USAGE;
    private static final String WRONG_INDEX_MSG = "Invalid index payload given to 'like', expected a valid integer!";
    private static final String WRONG_INDEX_OUT_BOUND = "Invalid index payload given to 'like', index is out of range!";
    private static final String MISSING_SET_QUESTIONS = "A set of questions has not been gotten!";
    private static final String MISSING_SET_QUESTIONS_NOTES = "Please try 'get' command to generate a set of questions "
            + "before adding to favorite list!";
    private static final String TOKENIZER_ERROR = "Error tokenizing data!";
    private static final String STORAGE_ERROR = "Error saving to storage!";
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final int UPPER_BOUND = 5;
    private static final int LOWER_BOUND = 1;
    private static final Logger LOGGER = WellNusLogger.getLogger("ReflectLikeCommandLogger");
    private static final ReflectUi UI = new ReflectUi();
    private Set<Integer> randomQuestionIndexes;
    private QuestionList questionList;

    /**
     * Set up the argument-payload pairs for this command.<br/>
     * Pass in a questionList object from ReflectionManager to access the indexes of the previous set of questions.
     *
     * @param arguments Argument-payload pairs from users
     * @param questionList Object that contains the data about questions
     */
    public LikeCommand(HashMap<String, String> arguments, QuestionList questionList) {
        super(arguments);
        this.questionList = questionList;
        this.randomQuestionIndexes = questionList.getRandomQuestionIndexes();
    }

    /**
     * Get the command itself.
     *
     * @return Command: like
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Get the name of the feature in which this like command is generated.
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
            throw new BadCommandException(INVALID_ARGUMENT_MSG);
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
            addFavQuestion(getArguments().get(COMMAND_KEYWORD));
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.INFO, MISSING_SET_QUESTIONS);
            UI.printErrorFor(badCommandException, MISSING_SET_QUESTIONS_NOTES);
        } catch (TokenizerException tokenizerException) {
            LOGGER.log(Level.WARNING, TOKENIZER_ERROR);
            UI.printErrorFor(tokenizerException, TOKENIZER_ERROR);
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            UI.printErrorFor(storageException, STORAGE_ERROR);
        } catch (NumberFormatException numberFormatException) {
            LOGGER.log(Level.INFO, WRONG_INDEX_MSG);
            BadCommandException exception = new BadCommandException(WRONG_INDEX_MSG);
            UI.printErrorFor(exception, INVALID_COMMAND_NOTES);
        } catch (ReflectionException reflectionException) {
            UI.printErrorFor(reflectionException, INVALID_COMMAND_NOTES);
        }
    }

    /**
     * Add this index to favorite list and print the question to be added.<br/>
     * <br/>
     * A valid index will only be added(i.e. passed validateCommand()) if there is a set of questions gotten previously
     *
     * @param questionIndex User input of the index of question to be added to favorite list.
     * @throws BadCommandException If there is not a set of question generated yet.
     * @throws TokenizerException If there is error in tokenization of index
     * @throws StorageException If there is error in storing the data
     */
    public void addFavQuestion(String questionIndex) throws BadCommandException, TokenizerException, StorageException,
            NumberFormatException, ReflectionException {
        int questionIndexInt = Integer.parseInt(questionIndex);
        if (questionIndexInt > UPPER_BOUND || questionIndexInt < LOWER_BOUND) {
            throw new ReflectionException(WRONG_INDEX_OUT_BOUND);
        }
        if (!questionList.hasRandomQuestionIndexes()) {
            UI.printOutputMessage(MISSING_SET_QUESTIONS);
            return;
        }
        IndexMapper indexMapper = new IndexMapper(this.randomQuestionIndexes);
        HashMap<Integer, Integer> indexQuestionMap = indexMapper.mapIndex();
        int indexToAdd = indexQuestionMap.get(questionIndexInt);
        questionList.addFavListIndex(indexToAdd);
    }
}

