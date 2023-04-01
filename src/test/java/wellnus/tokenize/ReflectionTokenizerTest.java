package wellnus.tokenize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.storage.ReflectionTokenizer;

public class ReflectionTokenizerTest {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    @Test
    void tokenizeReflect_checkOutput_success() {
        ArrayList<Set<Integer>> indexesToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        likeTestIndexes.add(1);
        likeTestIndexes.add(2);
        Set<Integer> prevTestIndexes = new HashSet<>();
        prevTestIndexes.add(3);
        prevTestIndexes.add(4);
        indexesToTokenize.add(likeTestIndexes);
        indexesToTokenize.add(prevTestIndexes);
        String expectedTokenizedLike = "like:1,2";
        String expectedTokenizedPrev = "prev:3,4";
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(expectedTokenizedLike);
        expectedTokenizedIndex.add(expectedTokenizedPrev);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(indexesToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }

    @Test
    void tokenizeReflect_checkOutputEmptyIndex_success() {
        ArrayList<Set<Integer>> indexesToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        Set<Integer> prevTestIndexes = new HashSet<>();
        indexesToTokenize.add(likeTestIndexes);
        indexesToTokenize.add(prevTestIndexes);
        String expectedTokenizedLike = "like:";
        String expectedTokenizedPrev = "prev:";
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(expectedTokenizedLike);
        expectedTokenizedIndex.add(expectedTokenizedPrev);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(indexesToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }

    @Test
    void detokenizeReflect_checkOutput_success() {
        Set<Integer> expectedDetokenizedLikes = new HashSet<>();
        expectedDetokenizedLikes.add(1);
        expectedDetokenizedLikes.add(2);
        Set<Integer> expectedDetokenizedPrevs = new HashSet<>();
        expectedDetokenizedPrevs.add(3);
        expectedDetokenizedPrevs.add(4);
        ArrayList<String> stringsToDetokenize = new ArrayList<>();
        String tokenizedLikeTest = "like:1,2";
        String tokenizedPrevTest = "prev:3,4";
        stringsToDetokenize.add(tokenizedLikeTest);
        stringsToDetokenize.add(tokenizedPrevTest);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringsToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLikes, actualDetokenizedIndex.get(INDEX_ZERO));
        Assertions.assertEquals(expectedDetokenizedPrevs, actualDetokenizedIndex.get(INDEX_ONE));
    }

    @Test
    void detokenizeReflect_checkOutputEmptyString_success() {
        Set<Integer> expectedDetokenizedLikes = new HashSet<>();
        Set<Integer> expectedDetokenizedPrevs = new HashSet<>();
        ArrayList<String> stringsToDetokenize = new ArrayList<>();
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringsToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLikes, actualDetokenizedIndex.get(INDEX_ZERO));
        Assertions.assertEquals(expectedDetokenizedPrevs, actualDetokenizedIndex.get(INDEX_ONE));
    }
}
