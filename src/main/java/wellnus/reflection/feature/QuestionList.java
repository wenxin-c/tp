package wellnus.reflection.feature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.storage.ReflectionTokenizer;
import wellnus.storage.Storage;

/**
 * This class contains the list of questions available in reflect feature,
 * and the list of indexes of favorite questions liked by the user.<br/>
 * <br/>
 * This class calls methods to load the list of indexes of favorite questions from data file,
 * and save the updated data into data file.<br/>
 * <br/>
 * It also stores the indexes of the previous set of questions(i.e. set of 5 random indexes)
 * which will then be used for other commands.
 */
public class QuestionList {

    // Questions are adopted from website: https://www.usa.edu/blog/self-discovery-questions/
    private static final String[] QUESTIONS = {
        "What are three of my most cherished personal values?",
        "What is my purpose in life?",
        "What is my personality type?",
        "Did I make time for myself this week?",
        "Am I making time for my social life?",
        "What scares me the most right now?",
        "What is something I find inspiring?",
        "What is something that brings me joy?",
        "When is the last time I gave back to others?",
        "What matters to me most right now?"
    };
    private static final int TOTAL_NUM_QUESTIONS = 10;
    private static final int RANDOM_NUMBER_UPPERBOUND = 10;
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INCREMENT_ONE = 1;
    private static final String TOTAL_NUM_QUESTION_ASSERTIONS = "The total number of questions is 10.";
    private static final String ADD_FAV_SUCCESS_ONE = "You have added question: ";
    private static final String ADD_FAV_SUCCESS_TWO = " Into favorite list!!";
    private static final String REMOVE_FAV_SUCCESS_ONE = "You have removed question: ";
    private static final String REMOVE_FAV_SUCCESS_TWO = " From favorite list!!";
    private static final String DUPLICATE_LIKE = " Is already in the favorite list!";
    private static final String STORAGE_ERROR = "Error storing data!";
    private static final String TOKENIZER_ERROR = "Previous reflect data will not be restored.";
    private static final String DOT = ".";
    private static final String EMPTY_STRING = "";
    private static final String FILE_NAME = "reflect";
    private static final String QUOTE = "\"";
    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR =
            new RandomNumberGenerator(RANDOM_NUMBER_UPPERBOUND);
    private static final Logger LOGGER = WellNusLogger.getLogger("ReflectQuestionListLogger");
    private static final ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
    private static final ReflectUi UI = new ReflectUi();
    private static final boolean HAS_RANDOM_QUESTIONS = true;
    private static final boolean NOT_HAS_RANDOM_QUESTIONS = false;
    private static final boolean HAS_FAV_QUESTIONS = true;
    private static final boolean NOT_HAS_FAV_QUESTIONS = false;
    private ArrayList<ReflectionQuestion> questions = new ArrayList<>();
    private Set<Integer> randomQuestionIndexes;
    private ArrayList<Set<Integer>> dataIndex;
    private Storage storage;

    //@@author wenxin-c
    /**
     * Constructor to create a SelfReflection object and set up the questions available.
     */
    public QuestionList() {
        try {
            storage = new Storage();
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            UI.printErrorFor(storageException, STORAGE_ERROR);
        }
        this.randomQuestionIndexes = new HashSet<>();
        this.dataIndex = new ArrayList<>();
        HashSet<Integer> setLike = new HashSet<>();
        HashSet<Integer> setPrev = new HashSet<>();
        this.dataIndex.add(setLike);
        this.dataIndex.add(setPrev);
        try {
            this.loadQuestionData();
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            UI.printErrorFor(storageException, STORAGE_ERROR);
        } catch (TokenizerException tokenizerException) {
            overrideErrorReflectData();
            LOGGER.log(Level.WARNING, TOKENIZER_ERROR);
            UI.printErrorFor(tokenizerException, TOKENIZER_ERROR);
        }
        setUpQuestions();
        assert questions.size() == TOTAL_NUM_QUESTIONS : TOTAL_NUM_QUESTION_ASSERTIONS;
    }

    private void overrideErrorReflectData() {
        ArrayList<String> emptyTokenizedReflectIndexes = new ArrayList<>();
        try {
            storage.saveData(emptyTokenizedReflectIndexes , Storage.FILE_REFLECT);
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
        }
    }

    /**
     * Load the pool of introspective questions available for users.
     */
    public void setUpQuestions() {
        for (String question : QUESTIONS) {
            addReflectQuestion(question);
        }
    }

    /**
     * Create a ReflectionQuestion object for each question and add to questions list.
     *
     * @param question String of question description.
     */
    public void addReflectQuestion(String question) {
        ReflectionQuestion newQuestion = new ReflectionQuestion(question);
        questions.add(newQuestion);
    }

    public void setDataIndex(ArrayList<Set<Integer>> dataIndex) {
        this.dataIndex = dataIndex;
    }

    public ArrayList<Set<Integer>> getDataIndex() {
        return dataIndex;
    }

    /**
     * Tokenize the indexes of liked questions and store them in a data file.
     *
     * @throws TokenizerException If there is error during tokenization
     * @throws StorageException If data cannot be stored properly
     */
    public void storeQuestionData() throws StorageException {
        ArrayList<String> tokenizedQuestionList = reflectionTokenizer.tokenize(this.dataIndex);
        storage.saveData(tokenizedQuestionList, FILE_NAME);
    }

    /**
     * Load a string of integers from data file and detokenize it into the set of indexes of favorite questions.
     *
     * @throws StorageException If there is error during tokenization
     * @throws TokenizerException If there is error during detokenization
     */
    public void loadQuestionData() throws StorageException, TokenizerException {
        ArrayList<String> loadedQuestionList = storage.loadData(FILE_NAME);
        ArrayList<Set<Integer>> detokenizedQuestionList = reflectionTokenizer.detokenize(loadedQuestionList);
        this.setDataIndex(detokenizedQuestionList);
        this.randomQuestionIndexes = this.dataIndex.get(INDEX_ONE);
    }

    /**
     * Generate a set of 5 distinct random numbers from 0-9 which will then be used as indexes to
     * select 5 random questions.
     */
    public void setRandomQuestionIndexes() throws StorageException {
        this.randomQuestionIndexes = RANDOM_NUMBER_GENERATOR.generateRandomNumbers();
        ArrayList<Set<Integer>> updatedQuestionData = new ArrayList<>();
        Set<Integer> favIndexList = this.dataIndex.get(INDEX_ZERO);
        updatedQuestionData.add(favIndexList);
        updatedQuestionData.add(this.randomQuestionIndexes);
        this.setDataIndex(updatedQuestionData);
        this.storeQuestionData();
    }

    public void setRandomQuestionIndexes(HashSet<Integer> randomQuestionIndexes) {
        this.randomQuestionIndexes = randomQuestionIndexes;
    }

    public void clearRandomQuestionIndexes() {
        this.randomQuestionIndexes.clear();
    }

    public Set<Integer> getRandomQuestionIndexes() {
        return this.randomQuestionIndexes;
    }

    public ArrayList<ReflectionQuestion> getAllQuestions() {
        return questions;
    }

    /**
     * Add the index of a liked question into fav list.<br/>
     * <br/>
     * A valid index will only be added(i.e. passed validateCommand())
     * if the question is not yet in the favorite list.<br/>
     * Indexes of all favorite questions will be stored in data file every time a question is liked.
     *
     * @param indexToAdd The index of the question liked by user
     * @throws StorageException If data fails to be stored properly.
     */
    public void addFavListIndex(int indexToAdd) throws StorageException {
        if (this.dataIndex.get(INDEX_ZERO).contains(indexToAdd)) {
            UI.printOutputMessage(QUOTE + questions.get(indexToAdd).toString() + QUOTE + DUPLICATE_LIKE);
            return;
        }
        this.dataIndex.get(INDEX_ZERO).add(indexToAdd);
        this.storeQuestionData();
        UI.printOutputMessage(ADD_FAV_SUCCESS_ONE + QUOTE + this.questions.get(indexToAdd).toString() + QUOTE
                + ADD_FAV_SUCCESS_TWO);
    }

    /**
     * Remove the index of a liked question from the fav list.<br/>
     * <br/>
     * Indexes of all favorite questions will be stored in data file every time a question is removed.
     *
     * @param indexToRemove The index of question to be removed from fav list.
     * @throws StorageException If data fails to be stored properly.
     */
    public void removeFavListIndex(int indexToRemove) throws StorageException {
        this.dataIndex.get(INDEX_ZERO).remove(indexToRemove);
        this.storeQuestionData();
        UI.printOutputMessage(REMOVE_FAV_SUCCESS_ONE + QUOTE + this.questions.get(indexToRemove).toString() + QUOTE
                + REMOVE_FAV_SUCCESS_TWO);
    }

    /**
     * Check whether a set of random question has been generated by checking the size of the set of question indexes.
     *
     * @return True for non-empty set and false for empty set
     */
    public boolean hasRandomQuestionIndexes() {
        if (this.randomQuestionIndexes.isEmpty()) {
            return NOT_HAS_RANDOM_QUESTIONS;
        } else {
            return HAS_RANDOM_QUESTIONS;
        }
    }

    /**
     * Check whether there is a set of favorite questions by checking the size of the set of favorite question indexes.
     *
     * @return True for non-empty set and false for empty set
     */
    public boolean hasFavQuestions() {
        if (this.dataIndex.get(INDEX_ZERO).isEmpty()) {
            return NOT_HAS_FAV_QUESTIONS;
        } else {
            return HAS_FAV_QUESTIONS;
        }
    }

    /**
     * Get a string of all favorite questions based on the favorite question indexes.
     *
     * @return String of favorite questions
     */
    public String getFavQuestions() throws IndexOutOfBoundsException {
        String questionString = EMPTY_STRING;
        int displayIndex = INDEX_ONE;
        for (int questionIndex : this.dataIndex.get(INDEX_ZERO)) {
            questionString += (displayIndex + DOT + this.questions.get(questionIndex).toString()
                    + System.lineSeparator());
            displayIndex += INCREMENT_ONE;
        }
        return questionString;
    }

    /**
     * Get the previously generated set of questions. *
     * @return String of previously generated questions */
    public String getPrevSetQuestions() throws IndexOutOfBoundsException {
        String questionString = EMPTY_STRING;
        int displayIndex = INDEX_ONE;
        for (int questionIndex : this.dataIndex.get(INDEX_ONE)) {
            questionString += (displayIndex + DOT + this.questions.get(questionIndex).toString()
                    + System.lineSeparator());
            displayIndex += INCREMENT_ONE;
        }
        return questionString;
    }
    //@@author
}

