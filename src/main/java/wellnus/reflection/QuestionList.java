package wellnus.reflection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains the list of questions available in reflect feature,
 * and the list of indexes of favorite questions liked by the user.<br/>
 * <br/>
 * This class calls methods to load the list of indexes of favorite questions from data file,
 * and save the updated data into data file.<br/>
 * <br/>
 * It also stores the indexes of the previous set of questions(i.e. set of 5 random indexes)
 * which will then be used for other commands.
 *
 * @@author wenxin-c
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
    private static final String TOTAL_NUM_QUESTION_ASSERTIONS = "The total number of questions is 10.";
    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR =
            new RandomNumberGenerator(RANDOM_NUMBER_UPPERBOUND);
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
            ReflectionQuestion newQuestion = new ReflectionQuestion(question);
            addReflectQuestion(newQuestion);
        }
    }

    public void addReflectQuestion(ReflectionQuestion question) {
        questions.add(question);
    }

    public ArrayList<HashSet<Integer>> getDataIndex() {
        return dataIndex;
    }

    // TODO: add storage method here<br>
    // Whenever dataIndex is updated, it should be saved into data file
    public void setDataIndex(ArrayList<HashSet<Integer>> dataIndex) {
        this.dataIndex = dataIndex;
    }

    /**
     * Generate a set of 5 distinct random numbers from 0-9
     * which will then be used as indexes to select 5 random questions.
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
}

