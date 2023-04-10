package wellnus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.exception.TokenizerException;

/**
 * This class provides tests for the ReflectionTokenizer class. It tests the functionality of the methods of the
 * ReflectionTokenizer class using various inputs.
 */
public class ReflectionTokenizerTest {
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_TWO = 2;
    private static final int NUMBER_THREE = 3;
    private static final int NUMBER_FOUR = 4;
    private static final int NUMBER_FIVE = 5;
    private static final String EXPECTED_TOKENIZED_LIKE = "like:1,2";
    private static final String EXPECTED_TOKENIZED_PREV = "prev:3,4";
    private static final String EXPECTED_TOKENIZED_LIKE_EMPTY = "like:";
    private static final String EXPECTED_TOKENIZED_PREV_EMPTY = "prev:";
    private static final String TOKENIZED_LIKE_TEST = "like:1,2";
    private static final String TOKENIZED_PREV_TEST = "prev:1,2,3,4,5";

    /**
     * Tests the {@link ReflectionTokenizer#tokenize(ArrayList)} method to ensure that it correctly
     * tokenizes a set of like, prev indexes.
     */
    @Test
    void tokenizeReflect_checkOutput_success() {
        ArrayList<Set<Integer>> indexesToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        likeTestIndexes.add(NUMBER_ONE);
        likeTestIndexes.add(NUMBER_TWO);
        Set<Integer> prevTestIndexes = new HashSet<>();
        prevTestIndexes.add(NUMBER_THREE);
        prevTestIndexes.add(NUMBER_FOUR);
        indexesToTokenize.add(likeTestIndexes);
        indexesToTokenize.add(prevTestIndexes);
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(EXPECTED_TOKENIZED_LIKE);
        expectedTokenizedIndex.add(EXPECTED_TOKENIZED_PREV);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(indexesToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }

    /**
     * Tests the {@link ReflectionTokenizer#tokenize(ArrayList)} method to ensure that it correctly
     * tokenizes a set of like, prev indexes when it is empty.
     */
    @Test
    void tokenizeReflect_checkOutputEmptyIndex_success() {
        ArrayList<Set<Integer>> indexesToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        Set<Integer> prevTestIndexes = new HashSet<>();
        indexesToTokenize.add(likeTestIndexes);
        indexesToTokenize.add(prevTestIndexes);
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(EXPECTED_TOKENIZED_LIKE_EMPTY);
        expectedTokenizedIndex.add(EXPECTED_TOKENIZED_PREV_EMPTY);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(indexesToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }

    /**
     * Tests the {@link ReflectionTokenizer#detokenize(ArrayList)} method to ensure that it correctly
     * detokenizes a list of tokenized like and prev indexes.
     *
     * @throws TokenizerException if an error occurs during tokenization.
     */
    @Test
    void detokenizeReflect_checkOutput_success() throws TokenizerException {
        Set<Integer> expectedDetokenizedLikes = new HashSet<>();
        expectedDetokenizedLikes.add(NUMBER_ONE);
        expectedDetokenizedLikes.add(NUMBER_TWO);
        Set<Integer> expectedDetokenizedPrevs = new HashSet<>();
        expectedDetokenizedPrevs.add(NUMBER_ONE);
        expectedDetokenizedPrevs.add(NUMBER_TWO);
        expectedDetokenizedPrevs.add(NUMBER_THREE);
        expectedDetokenizedPrevs.add(NUMBER_FOUR);
        expectedDetokenizedPrevs.add(NUMBER_FIVE);
        ArrayList<String> stringsToDetokenize = new ArrayList<>();
        stringsToDetokenize.add(TOKENIZED_LIKE_TEST);
        stringsToDetokenize.add(TOKENIZED_PREV_TEST);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringsToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLikes, actualDetokenizedIndex.get(NUMBER_ZERO));
        Assertions.assertEquals(expectedDetokenizedPrevs, actualDetokenizedIndex.get(NUMBER_ONE));
    }

    /**
     * Tests the {@link ReflectionTokenizer#detokenize(ArrayList)} method to ensure that it correctly
     * detokenizes a list of tokenized like and prev indexes when it is empty.
     *
     * @throws TokenizerException if an error occurs during tokenization.
     */
    @Test
    void detokenizeReflect_checkOutputEmptyString_success() throws TokenizerException {
        Set<Integer> expectedDetokenizedLikes = new HashSet<>();
        Set<Integer> expectedDetokenizedPrevs = new HashSet<>();
        ArrayList<String> stringsToDetokenize = new ArrayList<>();
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringsToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLikes, actualDetokenizedIndex.get(NUMBER_ZERO));
        Assertions.assertEquals(expectedDetokenizedPrevs, actualDetokenizedIndex.get(NUMBER_ONE));
    }
}
