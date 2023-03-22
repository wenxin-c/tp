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
        ArrayList<Set<Integer>> indexesToTokenize = new ArrayList<>();
        Set<Integer> likeTestIndexes = new HashSet<>();
        Set<Integer> prefTestIndexes = new HashSet<>();
        likeTestIndexes.add(1);
        likeTestIndexes.add(2);
        prefTestIndexes.add(3);
        prefTestIndexes.add(4);
        indexesToTokenize.add(likeTestIndexes);
        indexesToTokenize.add(prefTestIndexes);
        String expectedTokenizedLike = "like 1,2";
        String expectedTokenizedPref = "pref 3,4";
        ArrayList<String> expectedTokenizedIndex = new ArrayList<>();
        expectedTokenizedIndex.add(expectedTokenizedLike);
        expectedTokenizedIndex.add(expectedTokenizedPref);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<String> actualTokenizedIndex = reflectionTokenizer.tokenize(indexesToTokenize);
        Assertions.assertEquals(expectedTokenizedIndex, actualTokenizedIndex);
    }
    @Test
    void detokenizeReflect_checkOutput_success() throws TokenizerException {
        Set<Integer> expectedDetokenizedLikes = new HashSet<>();
        expectedDetokenizedLikes.add(1);
        expectedDetokenizedLikes.add(2);
        Set<Integer> expectedDetokenizedPrefs = new HashSet<>();
        expectedDetokenizedPrefs.add(3);
        expectedDetokenizedPrefs.add(4);
        ArrayList<String> stringsToDetokenize = new ArrayList<>();
        String tokenizedLikeTest = "like 1,2";
        String tokenizedPrefTest = "pref 3,4";
        stringsToDetokenize.add(tokenizedLikeTest);
        stringsToDetokenize.add(tokenizedPrefTest);
        ReflectionTokenizer reflectionTokenizer = new ReflectionTokenizer();
        ArrayList<Set<Integer>> actualDetokenizedIndex = reflectionTokenizer.detokenize(stringsToDetokenize);
        Assertions.assertEquals(expectedDetokenizedLikes, actualDetokenizedIndex.get(INDEX_ZERO));
        Assertions.assertEquals(expectedDetokenizedPrefs, actualDetokenizedIndex.get(INDEX_ONE));
    }
}
