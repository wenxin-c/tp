package wellnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.gamification.util.GamificationData;

/**
 * Tests the important behaviours of the <code>GamificationTokenizer</code> class
 * to ensure it functions as intended and expected.
 */
public class GamificationTokenizerTest {
    private static final int ADD_XP_AMOUNT = 5;
    private static final int FIRST_ELEMENT = 0;
    private static final int INITIAL_XP = 0;
    private static final int NEGATIVE_XP = -1;
    private static final int NO_LEVEL_XP = 5;
    private static final int LEVEL_UP_XP = 10;
    private static final String UNEXPECTED_EXCEPTION_MESSAGE = "TokenizerException not supposed to be thrown for valid "
            + "input '%d'.";
    private static final String WRONG_EXCEPTION_MESSAGE = "'%s' thrown when testing valid input '%d'. Not even the "
            + "relevant Exception category.";
    private static final int XP_LEVEL_ZERO = 0;
    private static final int XP_LEVEL_ONE = 1;

    //@@author haoyangw
    /**
     * Check that <code>GamificationTokenizer</code> can tokenize a newly-initialised
     * <code>GamificationData</code> correctly.
     */
    @Test
    public void tokenize_initialGamificationData_success() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        GamificationData gamificationData = new GamificationData();
        ArrayList<GamificationData> testDatas = new ArrayList<>();
        testDatas.add(gamificationData);
        ArrayList<String> tokenizedDatas = gamificationTokenizer.tokenize(testDatas);
        String tokenizedData = tokenizedDatas.get(FIRST_ELEMENT);
        String expectedTokenizedData = INITIAL_XP + "";
        assertEquals(tokenizedData, expectedTokenizedData);
    }

    /**
     * Check that <code>GamificationTokenizer</code> can tokenize a
     * <code>GamificationData</code> already containing some XP correctly.
     */
    @Test
    public void tokenize_hasXpGamificationData_success() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        GamificationData gamificationData = new GamificationData();
        try {
            gamificationData.addXp(ADD_XP_AMOUNT);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            fail(String.format(WRONG_EXCEPTION_MESSAGE, exceptionName, ADD_XP_AMOUNT));
        }
        ArrayList<GamificationData> testDatas = new ArrayList<>();
        testDatas.add(gamificationData);
        ArrayList<String> tokenizedDatas = gamificationTokenizer.tokenize(testDatas);
        String tokenizedData = tokenizedDatas.get(FIRST_ELEMENT);
        String expectedTokenizedData = ADD_XP_AMOUNT + "";
        assertEquals(tokenizedData, expectedTokenizedData);
    }

    /**
     * Check that <code>GamificationTokenizer</code> can parse and detokenize what should be
     * a default-state <code>GamificationData</code>'s String.
     */
    @Test
    public void detokenize_initialGamificationData_success() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        String tokenizedData = INITIAL_XP + "";
        ArrayList<String> tokenizedDatas = new ArrayList<>();
        tokenizedDatas.add(tokenizedData);
        ArrayList<GamificationData> detokenizedDatas = null;
        try {
            detokenizedDatas = gamificationTokenizer.detokenize(tokenizedDatas);
        } catch (TokenizerException tokenizerException) {
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, INITIAL_XP));
        }
        assertNotNull(detokenizedDatas);
        GamificationData detokenizedData = detokenizedDatas.get(FIRST_ELEMENT);
        assertEquals(detokenizedData.getTotalXp(), INITIAL_XP);
    }

    /**
     * Check that <code>GamificationTokenizer</code> can detokenize what should be
     * a <code>GamificationData</code> with 0 XP levels but some XP points correctly.
     */
    @Test
    public void detokenize_noXpLevels_success() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        String tokenizedData = NO_LEVEL_XP + "";
        ArrayList<String> tokenizedDatas = new ArrayList<>();
        tokenizedDatas.add(tokenizedData);
        ArrayList<GamificationData> detokenizedDatas = null;
        try {
            detokenizedDatas = gamificationTokenizer.detokenize(tokenizedDatas);
        } catch (TokenizerException tokenizerException) {
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, NO_LEVEL_XP));
        }
        assertNotNull(detokenizedDatas);
        GamificationData detokenizedData = detokenizedDatas.get(FIRST_ELEMENT);
        assertEquals(detokenizedData.getTotalXp(), NO_LEVEL_XP);
        assertEquals(detokenizedData.getXpLevel(), XP_LEVEL_ZERO);
    }

    /**
     * Check that <code>GamificationTokenizer</code> can detokenize what should be
     * a <code>GamificationData</code> with some XP levels correctly.
     */
    @Test
    public void detokenize_hasXpLevels_success() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        String tokenizedData = LEVEL_UP_XP + "";
        ArrayList<String> tokenizedDatas = new ArrayList<>();
        tokenizedDatas.add(tokenizedData);
        ArrayList<GamificationData> detokenizedDatas = null;
        try {
            detokenizedDatas = gamificationTokenizer.detokenize(tokenizedDatas);
        } catch (TokenizerException tokenizerException) {
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, LEVEL_UP_XP));
        }
        assertNotNull(detokenizedDatas);
        GamificationData detokenizedData = detokenizedDatas.get(FIRST_ELEMENT);
        assertEquals(detokenizedData.getTotalXp(), LEVEL_UP_XP);
        assertEquals(detokenizedData.getXpLevel(), XP_LEVEL_ONE);
    }

    /**
     * Check that <code>GamificationTokenizer</code> can detect corrupted data
     * and throw the correct <code>TokenizerException</code>.
     */
    @Test
    public void detokenize_notIntData_exceptionThrown() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        String garbageTokenizedData = "definitely not data";
        ArrayList<String> tokenizedDatas = new ArrayList<>();
        tokenizedDatas.add(garbageTokenizedData);
        assertThrows(TokenizerException.class, () -> gamificationTokenizer.detokenize(tokenizedDatas));
    }

    /**
     * Check that <code>GamificationTokenizer</code> can detect invalid XP points read from storage
     * and throw the correct <code>TokenizerException</code>.
     */
    @Test
    public void detokenize_negativeIntData_exceptionThrown() {
        GamificationTokenizer gamificationTokenizer = new GamificationTokenizer();
        String garbageTokenizedData = NEGATIVE_XP + "";
        ArrayList<String> tokenizedDatas = new ArrayList<>();
        tokenizedDatas.add(garbageTokenizedData);
        assertThrows(TokenizerException.class, () -> gamificationTokenizer.detokenize(tokenizedDatas));
    }
}
