package wellnus.gamification.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import wellnus.exception.StorageException;


public class GamificationDataTest {
    private static final int ADD_XP_SUCCESS_AMOUNT = 5;
    private static final int ADD_XP_EXCEPTION_AMOUNT = -1;
    private static final int LEVEL_UP_HOW_MANY_LEVELS = 1;
    private static final int LEVEL_UP_SUCCESS_AMOUNT = GamificationData.POINTS_PER_LEVEL;
    private static final int MINUS_XP_SUCCESS_AMOUNT = 2;
    private static final int MINUS_XP_EXCEPTION_AMOUNT = -1;
    private static final String UNEXPECTED_EXCEPTION_MESSAGE = "Not expecting '%s' when testing %s.";

    /**
     * Check that addXp() increments XP by given amount of XP correctly.
     * @see GamificationData#addXp(int)
     */
    @Test
    public void addXp_validIncrease_success() {
        GamificationData gamificationData = new GamificationData();
        try {
            gamificationData.addXp(ADD_XP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            String testCase = "addXp() with valid input";
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, exceptionName, testCase));
        }
        assertEquals(gamificationData.getTotalXp(), ADD_XP_SUCCESS_AMOUNT);
    }

    /**
     * Check that addXp() performs input validation and throws the correct
     * assertion when given invalid input.
     * @see GamificationData#addXp(int)
     */
    @Test
    public void addXp_negativeIncrement_exceptionThrown() {
        GamificationData gamificationData = new GamificationData();
        assertThrows(AssertionError.class, () -> gamificationData.addXp(ADD_XP_EXCEPTION_AMOUNT));
    }

    /**
     * Check that addXp() correctly levels up when incremented by
     * sufficient amount of XP points.
     * @see GamificationData#addXp(int)
     */
    @Test
    public void addXp_levelUp_success() {
        GamificationData gamificationData = new GamificationData();
        boolean isLevelUp = false;
        try {
            isLevelUp = gamificationData.addXp(LEVEL_UP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            String testCase = "addXp() level up with valid input";
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, exceptionName, testCase));
        }
        assertEquals(gamificationData.getXpLevel(), LEVEL_UP_HOW_MANY_LEVELS);
        boolean levelledUp = true;
        assertEquals(isLevelUp, levelledUp);
    }

    /**
     * Check that getXpForCurrentLevelOnly() calculates the correct amount
     * of remaining XP for the current level.
     * @see GamificationData#getXpForCurrentLevelOnly()
     */
    @Test
    public void getXpForCurrentLevelOnly_remainingXp_success() {
        GamificationData gamificationData = new GamificationData();
        int remainingXp = 2;
        try {
            gamificationData.addXp(GamificationData.POINTS_PER_LEVEL + remainingXp);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            String testCase = "getXpForCurrentLevelOnly() with valid input";
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, exceptionName, testCase));
        }
        assertEquals(gamificationData.getXpForCurrentLevelOnly(), remainingXp);
    }

    /**
     * Check that getXpToReachNextLevel() calculates the correct amount of
     * XP to level up when the total XP is less than one XP level.
     * @see GamificationData#getXpToReachNextLevel()
     */
    @Test
    public void getXpToReachNextLevel_lessThanOneLevel_success() {
        GamificationData gamificationData = new GamificationData();
        int testXpPoints = 4;
        try {
            gamificationData.addXp(testXpPoints);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            String testCase = "getXpToReachNextLevel() with valid input";
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, exceptionName, testCase));
        }
        int xpToReachNextLevel = GamificationData.POINTS_PER_LEVEL - testXpPoints;
        assertEquals(gamificationData.getXpToReachNextLevel(), xpToReachNextLevel);
    }

    /**
     * Check that minusXp() correctly decrements XP points by given amount.
     * @see GamificationData#minusXp(int)
     */
    @Test
    public void minusXp_validDecrease_success() {
        GamificationData gamificationData = new GamificationData();
        try {
            gamificationData.addXp(ADD_XP_SUCCESS_AMOUNT);
            gamificationData.minusXp(MINUS_XP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            String exceptionName = "StorageException";
            String testCase = "minusXp() level up with valid input";
            fail(String.format(UNEXPECTED_EXCEPTION_MESSAGE, exceptionName, testCase));
        }
        int expectedRemaining = ADD_XP_SUCCESS_AMOUNT - MINUS_XP_SUCCESS_AMOUNT;
        assertEquals(gamificationData.getTotalXp(), expectedRemaining);
    }

    /**
     * Check that minusXp() performs input validation and throws an
     * assertion when given invalid input.
     * @see GamificationData#minusXp(int)
     */
    @Test
    public void minusXp_negativeDecrement_exceptionThrown() {
        GamificationData gamificationData = new GamificationData();
        assertThrows(AssertionError.class, () -> gamificationData.minusXp(MINUS_XP_EXCEPTION_AMOUNT));
    }
}
