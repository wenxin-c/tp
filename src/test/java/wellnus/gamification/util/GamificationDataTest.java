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

    @Test
    public void addXp_validIncrease_success() {
        GamificationData gamificationData = new GamificationData();
        try {
            gamificationData.addXp(ADD_XP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            fail();
        }
        assertEquals(gamificationData.getTotalXp(), ADD_XP_SUCCESS_AMOUNT);
    }

    @Test
    public void addXp_negativeIncrement_exceptionThrown() {
        GamificationData gamificationData = new GamificationData();
        assertThrows(AssertionError.class, () -> gamificationData.addXp(ADD_XP_EXCEPTION_AMOUNT));
    }

    @Test
    public void addXp_levelUp_success() {
        GamificationData gamificationData = new GamificationData();
        boolean isLevelUp = false;
        try {
            isLevelUp = gamificationData.addXp(LEVEL_UP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            fail();
        }
        assertEquals(gamificationData.getXpLevel(), LEVEL_UP_HOW_MANY_LEVELS);
        boolean levelledUp = true;
        assertEquals(isLevelUp, levelledUp);
    }

    @Test
    public void getXpForCurrentLevelOnly_remainingXp_success() {
        GamificationData gamificationData = new GamificationData();
        int remainingXp = 2;
        try {
            gamificationData.addXp(GamificationData.POINTS_PER_LEVEL + remainingXp);
        } catch (StorageException storageException) {
            fail();
        }
        assertEquals(gamificationData.getXpForCurrentLevelOnly(), remainingXp);
    }

    @Test
    public void getXpToReachNextLevel_lessThanOneLevel_success() {
        GamificationData gamificationData = new GamificationData();
        int testXpPoints = 4;
        try {
            gamificationData.addXp(testXpPoints);
        } catch (StorageException storageException) {
            fail();
        }
        int xpToReachNextLevel = GamificationData.POINTS_PER_LEVEL - testXpPoints;
        assertEquals(gamificationData.getXpToReachNextLevel(), xpToReachNextLevel);
    }

    @Test
    public void minusXp_validDecrease_success() {
        GamificationData gamificationData = new GamificationData();
        try {
            gamificationData.addXp(ADD_XP_SUCCESS_AMOUNT);
            gamificationData.minusXp(MINUS_XP_SUCCESS_AMOUNT);
        } catch (StorageException storageException) {
            fail();
        }
        int expectedRemaining = ADD_XP_SUCCESS_AMOUNT - MINUS_XP_SUCCESS_AMOUNT;
        assertEquals(gamificationData.getTotalXp(), expectedRemaining);
    }

    @Test
    public void minusXp_negativeDecrement_exceptionThrown() {
        GamificationData gamificationData = new GamificationData();
        assertThrows(AssertionError.class, () -> gamificationData.minusXp(MINUS_XP_EXCEPTION_AMOUNT));
    }
}
