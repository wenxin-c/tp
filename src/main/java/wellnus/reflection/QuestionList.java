package wellnus.reflection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//@@author wenxin-c
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

    // TODO: To be changed/updated at later stages if question size increases
    private static final int TOTAL_NUM_QUESTIONS = 10;
    private static final int RANDOM_NUMBER_UPPERBOUND = 10;
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INCREMENT_ONE = 1;
    private static final String TOTAL_NUM_QUESTION_ASSERTIONS = "The total number of questions is 10.";
    private static final String ADD_FAV_SUCCESS_ONE = "You have added question: ";
    private static final String ADD_FAV_SUCCESS_TWO = " into favorite list!!";
    private static final String DUPLICATE_LIKE = " is already in the favorite list!";
    private static final String DOT = ".";
    private static final String EMPTY_STRING = "";
    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR =
            new RandomNumberGenerator(RANDOM_NUMBER_UPPERBOUND);
    private static final ReflectUi UI = new ReflectUi();
    private static final boolean HAS_RANDOM_QUESTIONS = true;
    private static final boolean NOT_HAS_RANDOM_QUESTIONS = false;
    private static final boolean HAS_FAV_QUESTIONS = true;
    private static final boolean NOT_HAS_FAV_QUESTIONS = false;
    private ArrayList<ReflectionQuestion> questions = new ArrayList<>();
    private Set<Integer> randomQuestionIndexes;
    private ArrayList<HashSet<Integer>> dataIndex;

    /**
     * Constructor to create a SelfReflection object and set up the questions available.
     */
    public QuestionList() {
        this.randomQuestionIndexes = new HashSet<>();
        // TODO: Load data from file through tokenizer
        this.dataIndex = new ArrayList<>();
        HashSet<Integer> setLike = new HashSet<>();
        // TODO: create a prev set here
        this.dataIndex.add(setLike);
        setUpQuestions();
        assert questions.size() == TOTAL_NUM_QUESTIONS : TOTAL_NUM_QUESTION_ASSERTIONS;
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

    public ArrayList<HashSet<Integer>> getDataIndex() {
        return dataIndex;
    }

    // TODO: add storage method here<br>
    /**
     * Generate a set of 5 distinct random numbers from 0-9 which will then be used as indexes to
     * select 5 random questions.
     */
    public void setRandomQuestionIndexes() {
        this.randomQuestionIndexes = RANDOM_NUMBER_GENERATOR.generateRandomNumbers();
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
     * A valid index will only be added(i.e. passed validateCommand()) if the question is not yet in the favorite list
     *
     * @param indexToAdd The index of the question liked by user
     */
    public void addFavListIndex(int indexToAdd) {
        if (this.dataIndex.get(INDEX_ZERO).contains(indexToAdd)) {
            UI.printOutputMessage(questions.get(indexToAdd).toString() + DUPLICATE_LIKE);
            return;
        }
        this.dataIndex.get(INDEX_ZERO).add(indexToAdd);
        UI.printOutputMessage(ADD_FAV_SUCCESS_ONE + this.questions.get(indexToAdd).toString() + ADD_FAV_SUCCESS_TWO);
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
    public String getFavQuestions() throws ArrayIndexOutOfBoundsException {
        String questionString = EMPTY_STRING;
        int displayIndex = INDEX_ONE;
        for (int questionIndex : this.dataIndex.get(INDEX_ZERO)) {
            questionString += (displayIndex + DOT + this.questions.get(questionIndex).toString()
                    + System.lineSeparator());
            displayIndex += INCREMENT_ONE;
        }
        return questionString;
    }
}

