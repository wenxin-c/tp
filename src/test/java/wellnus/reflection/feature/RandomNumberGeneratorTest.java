package wellnus.reflection.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Class to test different tests for `RandomNumberGenerator` Class utilising JUnit tests.
 * Test cases will involve expected outputs.
 */
class RandomNumberGeneratorTest {
    private static final int NUM_OF_RANDOM_NUMBERS = 5;
    private static final int UPPER_BOUND = 9;

    /**
     * Test whether a set of 5 integers from 0-9 are generated correctly.
     */
    @Test
    void generateRandomIndexes_checkSetSize_expectFive() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator(UPPER_BOUND);
        Set<Integer> indexes = randomNumberGenerator.generateRandomNumbers();
        assertEquals(NUM_OF_RANDOM_NUMBERS, indexes.size());
    }
}
