package wellnus.gamification.util;

import java.util.ArrayList;

import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.storage.GamificationTokenizer;
import wellnus.storage.Storage;

/**
 * Manages the storage and retrieval of gamification data to and from storage.
 */
public class GamificationStorage {
    private static final int EMPTY = 0;
    private static final int FIRST_INDEX = 0;
    private final Storage storage;
    private final GamificationTokenizer tokenizer;

    /**
     * Returns an instance of GamificationStorage.
     * @throws StorageException If Storage class cannot be initialised successfully
     */
    public GamificationStorage() throws StorageException {
        this.storage = new Storage();
        this.tokenizer = new GamificationTokenizer();
    }

    /**
     * Cleans the gamification data file, in cases such as when the data file is corrupted.
     * @throws StorageException If data file cannot be overwritten successfully
     */
    public void cleanDataFile() throws StorageException {
        String emptyData = "";
        ArrayList<String> emptyDatas = new ArrayList<>();
        emptyDatas.add(emptyData);
        storage.saveData(emptyDatas, Storage.FILE_GAMIFICATION);
    }

    /**
     * Loads tokenized gamification data from storage and detokenizes them back into
     * a GamificationData object.
     * @return GamificationData object representing the previously saved gamification statistics
     * @throws StorageException If data cannot be fetched from storage successfully
     * @throws TokenizerException If tokenized data cannot be detokenized into a GamificationData object
     */
    public GamificationData loadData() throws StorageException, TokenizerException {
        if (storage.checkFileExists(Storage.FILE_GAMIFICATION)) {
            ArrayList<String> tokenizedObjects = storage.loadData(Storage.FILE_GAMIFICATION);
            ArrayList<GamificationData> dataObjects = tokenizer.detokenize(tokenizedObjects);
            if (dataObjects.size() == EMPTY) {
                return new GamificationData();
            }
            return dataObjects.get(FIRST_INDEX);
        }
        return new GamificationData();
    }

    /**
     * Stores the given GamificationData object in local storage.
     * GamificationData is first converted into a String before being written to storage.
     * @param data GamificationData object representing the current gamification statistics we're saving
     * @throws StorageException If gamification statistics cannot be saved in storage successfully
     */
    public void store(GamificationData data) throws StorageException {
        ArrayList<GamificationData> objectsToStore = new ArrayList<>();
        objectsToStore.add(data);
        ArrayList<String> tokenizedObjects = tokenizer.tokenize(objectsToStore);
        storage.saveData(tokenizedObjects, Storage.FILE_GAMIFICATION);
    }
}
