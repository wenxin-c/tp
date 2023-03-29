package wellnus.gamification.util;

/**
 * Data structure for encapsulating WellNus++ gamification data such as experience
 * points and experience levels. See public methods to understand how to update the
 * gamification data.
 */
public class GamificationData {
    private static final int INITIAL_XP_POINTS = 0;
    private static final String INVALID_EXP_POINTS_TO_ADD_ERROR = "Cannot add non-positive amount of "
            + "experience points: '%d'";
    private static final String INVALID_EXP_POINTS_TO_MINUS_ERROR = "Cannot minus non-positive amount of "
            + "experience points: '%d'";
    private static final int POINTS_PER_LEVEL = 10;
    // Experience points accumulated so far
    private int xp;
    // Experience level based on the experience points
    private int level;

    /**
     * Returns an instance of the GamificationData class.
     */
    public GamificationData() {
        this.xp = INITIAL_XP_POINTS;
        this.level = getLevelFor(this.xp);
    }

    private static int getLevelFor(int xp) {
        return xp / POINTS_PER_LEVEL;
    }

    /**
     * Increases the user's XP points by the given amount.
     * @param pointsToAdd Number of XP points to increase
     * @return Whether the user just levelled up
     */
    public boolean addXp(int pointsToAdd) {
        assert pointsToAdd > 0 : String.format(INVALID_EXP_POINTS_TO_ADD_ERROR, pointsToAdd);
        xp += pointsToAdd;
        int newLevel = getLevelFor(xp);
        boolean hasLevelledUp = newLevel > level;
        level = newLevel;
        return hasLevelledUp;
    }

    /**
     * Returns the XP collected in the user's current level.
     * @return Amount of XP points collected in the current level
     */
    public int getXpForCurrentLevelOnly() {
        return getTotalXp() - (getXpLevel() * POINTS_PER_LEVEL);
    }

    /**
     * Returns the total number of XP points user has collected in WellNUS++.
     * @return Total number of XP points for the current user
     */
    public int getTotalXp() {
        return xp;
    }

    /**
     * Returns the user's current XP level
     * @return User's current XP level
     */
    public int getXpLevel() {
        return level;
    }

    /**
     * Returns the number of XP points required to reach the next level.
     * @return Number of XP points required to level up
     */
    public int getXpToReachNextLevel() {
        return POINTS_PER_LEVEL - getXpForCurrentLevelOnly();
    }

    /**
     * Decreases the user's total XP points by the given amount.
     * @param pointsToMinus Number of XP points to deduct from the user
     * @return Whether the user dropped by one level due to the XP deduction
     */
    public boolean minusXp(int pointsToMinus) {
        assert pointsToMinus > 0 : String.format(INVALID_EXP_POINTS_TO_MINUS_ERROR, pointsToMinus);
        xp -= pointsToMinus;
        int newLevel = getLevelFor(xp);
        boolean hasLevelDropped = newLevel < level;
        level = newLevel;
        return hasLevelDropped;
    }
}
