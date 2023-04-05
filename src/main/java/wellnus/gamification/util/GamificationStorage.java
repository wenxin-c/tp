package wellnus.gamification.util;

import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.storage.Storage;

import java.util.ArrayList;

/**
 * Manages the storage and retrieval of gamification data to and from storage.
 */
public class GamificationStorage {
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
     * Loads tokenized gamification data from storage and detokenizes them back into
     * a GamificationData object.
     * @return GamificationData object representing the previously saved gamification statistics
     * @throws StorageException If data cannot be fetched from storage successfully
     * @throws TokenizerException If tokenized data cannot be detokenized into a GamificationData object
     */
    public GamificationData loadData() throws StorageException, TokenizerException {
        ArrayList<String> tokenizedObjects = storage.loadData(Storage.FILE_GAMIFICATION);
        ArrayList<GamificationData> dataObjects = tokenizer.detokenize(tokenizedObjects);
        int dataObjectIndex = 0;
        return dataObjects.get(dataObjectIndex);
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
