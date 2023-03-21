package wellnus.tokenize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.exception.TokenizerException;
import wellnus.storage.ReflectionTokenizer;

public class ReflectionTokenizerTest {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    @Test
    void tokenizeReflect_checkOutput_success() throws TokenizerException {
        ArrayList<Set<Integer>> IndexToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        Set<Integer> prefTestIndexes = new HashSet<>();
        likeTestIndexes.add(1);
        likeTestIndexes.add(2);
        prefTestIndexes.add(3);
        prefTestIndexes.add(4);
        IndexToTokenize.add(likeTestIndexes);
        IndexToTokenize.add(prefTestIndexes);
        String expectedTokenizedLike = "like 1,2";
        String expectedTokenizedPref = "pref 3,4";
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(expectedTokenizedLike);
        expectedTokenizedIndex.add(expectedTokenizedPref);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(IndexToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }
    @Test
    void detokenizeReflect_checkOutput_success() throws TokenizerException {
        Set expectedDetokenizedLike = new HashSet<>();
        expectedDetokenizedLike.add(1);
        expectedDetokenizedLike.add(2);
        Set expectedDetokenizedPref = new HashSet<>();
        expectedDetokenizedPref.add(3);
        expectedDetokenizedPref.add(4);
        ArrayList<String> stringToDetokenize = new ArrayList<>();
        String tokenizedLikeTest = "like 1,2";
        String tokenizedPrefTest = "pref 3,4";
        stringToDetokenize.add(tokenizedLikeTest);
        stringToDetokenize.add(tokenizedPrefTest);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLike, actualDetokenizedIndex.get(INDEX_ZERO));
        Assertions.assertEquals(expectedDetokenizedPref, actualDetokenizedIndex.get(INDEX_ONE));
    }
}
