package wellnus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import wellnus.exception.TokenizerException;

/**
 * Class to tokenize and detokenize the Index for 'like' and 'pref' command in Reflection Feature. <br>
 */
public class ReflectionTokenizer implements Tokenizer<Set<Integer>> {
    private static final String INDEX_DELIMITER = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final String LIKE_KEY = "like";
    private static final String PREF_KEY = "pref";
    private static final String DETOKENIZE_ERROR_MESSAGE = "Detokenization failed!"
            + "The file might be corrupted";
    private String splitParameter(String tokenizedRawString, String parameterKey) throws TokenizerException {
        int i = tokenizedRawString.indexOf(' ');
        String parameter = tokenizedRawString.substring(INDEX_ZERO, i);
        if (!parameter.equals(parameterKey)) {
            throw new TokenizerException(DETOKENIZE_ERROR_MESSAGE);
        }
        String tokenizedIndexes = tokenizedRawString.substring(i).trim();
        return tokenizedIndexes;
    }
    private String[] splitTokenizedIndex(String tokenizedIndexes) {
        tokenizedIndexes = tokenizedIndexes.strip();
        int noLimit = -1;
        String[] rawString = tokenizedIndexes.split(INDEX_DELIMITER, noLimit);
        String[] cleanString = new String[rawString.length];
        for (int i = 0; i < rawString.length; ++i) {
            String currentCommand = rawString[i];
            currentCommand = currentCommand.strip();
            cleanString[i] = currentCommand;
        }
        return cleanString;
    }
    private Set<Integer> getSet(String indexToSplit) throws TokenizerException {
        Set<Integer> cleanIndexes = new HashSet<>();
        String[] splittedString = splitTokenizedIndex(indexToSplit);
        try {
            for (String indexString: splittedString) {
                int index = Integer.parseInt(indexString);
                cleanIndexes.add(index);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new TokenizerException(DETOKENIZE_ERROR_MESSAGE);
        }
        return cleanIndexes;
    }
    /**
     * Tokenize ArrayList of Set of Integers into strings that can be stored. <br>
     * ArrayList contains 2 Set of Integers, which corresponds for set of like indexes for the first entry
     *      and set of pref indexes for second entry.<br>
     * Each index will be tokenized with the following format:
     * like [list of comma separated index] <br>
     * pref [list of comma separated index] <br>
     *
     * @param arrayIndexToTokenize ArrayList that contains set of like indexes for the first entry
     *      and set of pref indexes for the second entry. <br>
     * @return ArrayList of Strings representing the tokenized like indexes and pref indexes that we can
     *      write to storage.
     */
    public ArrayList<String> tokenize(ArrayList<Set<Integer>> arrayIndexToTokenize) throws TokenizerException {
        ArrayList<String> tokenizedIndex = new ArrayList<>();
        Set<Integer> likeIndexToTokenize = arrayIndexToTokenize.get(INDEX_ZERO);
        Set<Integer> prefIndexToTokenize = arrayIndexToTokenize.get(INDEX_ONE);
        String tokenizedLike = LIKE_KEY + " ";
        for (int indexLike: likeIndexToTokenize) {
            tokenizedLike = tokenizedLike + indexLike + INDEX_DELIMITER;
        }
        tokenizedLike = tokenizedLike.substring(INDEX_ZERO, tokenizedLike.length() - INDEX_ONE);
        String tokenizedPref = PREF_KEY + " ";
        for (int indexPref: prefIndexToTokenize) {
            tokenizedPref = tokenizedPref + indexPref + INDEX_DELIMITER;
        }
        tokenizedPref = tokenizedPref.substring(INDEX_ZERO, tokenizedPref.length() - INDEX_ONE);
        tokenizedIndex.add(tokenizedLike);
        tokenizedIndex.add(tokenizedPref);
        return tokenizedIndex;
    }
    /**
     * Convert strings of tokenized Indexes into ArrayList that contains set of like indexes for the first entry
     *      and set of pref indexes for the second entry. <br>
     * This method can be called in the constructor of ReflectionManager to detokenize.
     * ArrayList of indexes from storage. <br>
     *
     * @param tokenizedIndex List of tokenized like and pref indexes from the storage.
     * @return ArrayList that contains set of like indexes for the first entry
     *      and set of pref indexes for the second entry <br>
     * @throws TokenizerException when the data can't be detokenized.
     */
    public ArrayList<Set<Integer>> detokenize(ArrayList<String> tokenizedIndex) throws TokenizerException {
        ArrayList<Set<Integer>> detokenizedIndex = new ArrayList<>();
        String rawIndexLike = splitParameter(tokenizedIndex.get(INDEX_ZERO), LIKE_KEY);
        String rawIndexPref = splitParameter(tokenizedIndex.get(INDEX_ONE), PREF_KEY);
        Set detokenizedLike = getSet(rawIndexLike);
        Set detokenizedPref = getSet(rawIndexPref);
        detokenizedIndex.add(detokenizedLike);
        detokenizedIndex.add(detokenizedPref);
        return detokenizedIndex;
    }
}

