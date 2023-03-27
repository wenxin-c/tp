package wellnus.reflection.feature;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

//@@author wenxin-c
/**
 * Generate a set of 5 distinct random integers ranging from 0 to 9<br/>
 * <br/>
 * This set of random numbers will be used as indexes to get a set of random questions.
 */
public class RandomNumberGenerator {
    private static final String NUM_SELECTED_QUESTIONS_ASSERTION = "The number of selected questions should be 5.";
    private static final int LOWER_BOUND = 0;
    private static final int ONE_OFFSET = 1;
    private static final int NUM_OF_RANDOM_NUMBERS = 5;
    private int upperBound;

    /**
     * Constructor with the upper limit of the random number as an argument.
     *
     * @param upperBound The max value of the random number is (upperBound - 1)
     */
    public RandomNumberGenerator(int upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * Generate a set of 5 random numbers.<br/>
     * <br/>
     * Each number num: num >= 0 and num <= (maxSize - 1)
     *
     * @return Set of 5 random numbers
     */
    public Set<Integer> generateRandomNumbers() {
        Set<Integer> randomNumbers = new Random().ints(LOWER_BOUND, this.upperBound - ONE_OFFSET)
                .distinct()
                .limit(NUM_OF_RANDOM_NUMBERS)
                .boxed()
                .collect(Collectors.toSet());
        assert randomNumbers.size() == NUM_OF_RANDOM_NUMBERS : NUM_SELECTED_QUESTIONS_ASSERTION;
        return randomNumbers;
    }
}

