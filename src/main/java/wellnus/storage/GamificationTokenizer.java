package wellnus.storage;

import java.util.ArrayList;

import wellnus.exception.TokenizerException;
import wellnus.gamification.util.GamificationData;

/**
 * Handles the conversion of GamificationData objects -> String and vice versa to allow
 * storage and retrieval of gamification statistics.
 */
public class GamificationTokenizer implements Tokenizer<GamificationData> {
    private static final String INVALID_STORED_DATA_MESSAGE = "Invalid gamification data '%s' found in storage!";
    private static final int MIN_XP = 0;

    /**
     * Converts the attributes of the <code>GamificationManager</code> into a String representation to be
     * saved to storage.
     *
     * @param dataObjects List of GamificationData Objects we want to convert into a String representation
     * @return ArrayList of Strings representing the GamificationData objects that we can write to storage
     */
    @Override
    public ArrayList<String> tokenize(ArrayList<GamificationData> dataObjects) {
        ArrayList<String> tokenizedObjects = new ArrayList<>();
        for (GamificationData data : dataObjects) {
            int xp = data.getTotalXp();
            String tokenizedObject = "" + xp;
            tokenizedObjects.add(tokenizedObject);
        }
        return tokenizedObjects;
    }

    /**
     * Converts the String representation of the <code>GamificationManager</code>'s state back into an
     * <code>ArrayList</code> of GamificationData that can be used to restore the gamification feature's
     * previous state.
     *
     * @param tokenizedDataObjects String representation of the GamificationData Objects whose state we want to restore
     * @return ArrayList containing all the gamification data from the gamification feature's previously saved state
     * @throws TokenizerException If detokenizing fails and stored gamification statistics cannot be restored
     */
    @Override
    public ArrayList<GamificationData> detokenize(ArrayList<String> tokenizedDataObjects)
            throws TokenizerException {
        ArrayList<GamificationData> dataObjects = new ArrayList<>();
        for (String tokenizedDataObject : tokenizedDataObjects) {
            // Data file contains blank lines
            if (tokenizedDataObject.isBlank()) {
                // Ignore the blank line and check other lines in the data file
                continue;
            }
            int totalXp;
            try {
                totalXp = Integer.parseInt(tokenizedDataObject.trim());
            } catch (NumberFormatException numberFormatException) {
                throw new TokenizerException(String.format(INVALID_STORED_DATA_MESSAGE, tokenizedDataObject));
            }
            if (totalXp < MIN_XP) {
                throw new TokenizerException(String.format(INVALID_STORED_DATA_MESSAGE, totalXp + ""));
            }
            GamificationData data = new GamificationData(totalXp);
            dataObjects.add(data);
        }
        return dataObjects;
    }
}
