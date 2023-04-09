package wellnus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.ui.TextUi;

/**
 * Class to tokenize and detokenize the Index for 'like' and 'prev' command in Reflection Feature. <br>
 */
public class ReflectionTokenizer implements Tokenizer<Set<Integer>> {
    private static final String INDEX_DELIMITER = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int LIKE_INDEX = 0;
    private static final int PREV_INDEX = 1;
    private static final int INDEX_NINE = 9;
    private static final int NUM_PREV_INDEX = 5;
    private static final int TOKENIZER_INDEX_ARRAYLIST_SIZE = 2;
    private static final String LIKE_KEY = "like";
    private static final String PREV_KEY = "prev";
    private static final String COLON_CHARACTER = ":";
    private static final int NO_LIMIT = -1;
    private static final String DETOKENIZE_ERROR_MESSAGE = "Invalid reflect %s data '%s' found in storage!";
    private static final String STORAGE_ERROR = "Previous reflect %s data will not be restored.";
    private static final Logger LOGGER = WellNusLogger.getLogger("ReflectTokenizerLogger");
    private String getTokenizedIndexes(String key, Set<Integer> indexesToTokenize) {
        String tokenizedIndexes = key + COLON_CHARACTER;
        for (int index : indexesToTokenize) {
            tokenizedIndexes = tokenizedIndexes + index + INDEX_DELIMITER;
        }
        if (indexesToTokenize.size() != INDEX_ZERO) {
            tokenizedIndexes = tokenizedIndexes.substring(INDEX_ZERO, tokenizedIndexes.length() - INDEX_ONE);
        }
        return tokenizedIndexes;
    }

    private String splitParameter(String tokenizedRawString, String categoryKey) throws TokenizerException {
        int indexSplit = tokenizedRawString.indexOf(COLON_CHARACTER);
        String parameter;
        String tokenizedIndexes;
        try {
            parameter = tokenizedRawString.substring(INDEX_ZERO, indexSplit);
            if (!parameter.equals(categoryKey)) {
                throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, categoryKey, tokenizedRawString));
            }
            tokenizedIndexes = tokenizedRawString.substring(indexSplit + INDEX_ONE).trim();
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, categoryKey, tokenizedRawString));
        }
        return tokenizedIndexes;
    }

    private String[] splitTokenizedIndex(String tokenizedIndexes) {
        tokenizedIndexes = tokenizedIndexes.strip();
        String[] inputStrings = tokenizedIndexes.split(INDEX_DELIMITER, NO_LIMIT);
        String[] outputStrings = new String[inputStrings.length];
        for (int i = 0; i < inputStrings.length; ++i) {
            String currentCommand = inputStrings[i];
            currentCommand = currentCommand.strip();
            outputStrings[i] = currentCommand;
        }
        return outputStrings;
    }

    private Set<Integer> validateTokenizedIndexFormat(ArrayList<String> tokenizedIndex,
                                                      int categoryIndex, String categoryKey) {
        Set<Integer> validatedSet = new HashSet<>();
        try {
            String tokenizedIndexesByCategory = tokenizedIndex.get(categoryIndex);
            if (!tokenizedIndexesByCategory.isBlank()) {
                String rawIndex = splitParameter(tokenizedIndexesByCategory, categoryKey);
                validatedSet = getSet(rawIndex, categoryKey);
            }
        } catch (TokenizerException tokenizerException) {
            TextUi textUi = new TextUi();
            textUi.printErrorFor(tokenizerException, String.format(STORAGE_ERROR, categoryKey));
            validatedSet = new HashSet<>();
            LOGGER.log(Level.INFO, DETOKENIZE_ERROR_MESSAGE);
        }
        return validatedSet;
    }

    private Set<Integer> getSet(String indexToSplit, String categoryKey) throws TokenizerException {
        Set<Integer> outputIndexes = new HashSet<>();
        if (indexToSplit.isBlank()) {
            return outputIndexes;
        }
        String[] splittedString = splitTokenizedIndex(indexToSplit);
        try {
            for (String indexString : splittedString) {
                int index = Integer.parseInt(indexString);
                if (index < INDEX_ZERO || index > INDEX_NINE) {
                    throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, categoryKey, indexToSplit));
                }
                outputIndexes.add(index);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, categoryKey, indexToSplit));
        }
        if (categoryKey.equals(PREV_KEY) && outputIndexes.size() != NUM_PREV_INDEX) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, categoryKey, indexToSplit));
        }
        return outputIndexes;
    }

    private void storeDetokenizedIndexes(ArrayList<Set<Integer>> detokenizedIndexes) throws StorageException {
        Storage storage = new Storage();
        ArrayList<String> tokenizedIndexes = tokenize(detokenizedIndexes);
        storage.saveData(tokenizedIndexes, Storage.FILE_REFLECT);
    }

    /**
     * Tokenize ArrayList of Set of Integers into strings that can be stored. <br>
     * ArrayList contains 2 Set of Integers, which corresponds for set of like indexes for the first entry
     *      and set of prev indexes for second entry.<br>
     * Each index will be tokenized with the following format:
     * like:[list of comma separated index] <br>
     * prev:[list of comma separated index] <br>
     *
     * @param arrayIndexToTokenize ArrayList that contains set of like indexes for the first entry
     *      and set of prev indexes for the second entry. <br>
     * @return ArrayList of Strings representing the tokenized like indexes and prev indexes that we can
     *      write to storage.
     */
    public ArrayList<String> tokenize(ArrayList<Set<Integer>> arrayIndexToTokenize) {
        ArrayList<String> tokenizedIndexes = new ArrayList<>();
        Set<Integer> likeIndexToTokenize = arrayIndexToTokenize.get(INDEX_ZERO);
        Set<Integer> prevIndexToTokenize = arrayIndexToTokenize.get(INDEX_ONE);
        String tokenizedLike = getTokenizedIndexes(LIKE_KEY, likeIndexToTokenize);
        String tokenizedPrev = getTokenizedIndexes(PREV_KEY, prevIndexToTokenize);
        tokenizedIndexes.add(tokenizedLike);
        tokenizedIndexes.add(tokenizedPrev);
        return tokenizedIndexes;
    }

    /**
     * Convert strings of tokenized Indexes into ArrayList that contains set of like indexes for the first entry
     *      and set of prev indexes for the second entry. <br>
     * This method can be called in the constructor of ReflectionManager to detokenize.
     * ArrayList of indexes from storage. <br>
     *
     * @param tokenizedIndex List of tokenized like and prev indexes from the storage.
     * @return ArrayList that contains set of like indexes for the first entry
     *      and set of prev indexes for the second entry <br>
     * @throws TokenizerException when the data can't be detokenized.
     */
    public ArrayList<Set<Integer>> detokenize(ArrayList<String> tokenizedIndex) throws TokenizerException {
        ArrayList<Set<Integer>> detokenizedIndexes = new ArrayList<>();
        Set<Integer> detokenizedLike = new HashSet<>();
        Set<Integer> detokenizedPrev = new HashSet<>();
        if (tokenizedIndex.size() == TOKENIZER_INDEX_ARRAYLIST_SIZE) {
            detokenizedLike = validateTokenizedIndexFormat(tokenizedIndex, LIKE_INDEX, LIKE_KEY);
            detokenizedPrev = validateTokenizedIndexFormat(tokenizedIndex, PREV_INDEX, PREV_KEY);
        }
        detokenizedIndexes.add(detokenizedLike);
        detokenizedIndexes.add(detokenizedPrev);
        try {
            storeDetokenizedIndexes(detokenizedIndexes);
        } catch (StorageException storageException) {
            throw new TokenizerException(DETOKENIZE_ERROR_MESSAGE);
        }
        return detokenizedIndexes;
    }
}

