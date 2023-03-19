package wellnus.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    private Storage getStorageInstance() {
        Storage storage;
        try {
            storage = new Storage();
        } catch (StorageException exception) {
            fail("Failed to create instance of storage!");
            return null;
        }
        return storage;
    }

    private HashMap<String, String> createValidDummyMap() {
        String key0 = "attr0";
        String val0 = "p0";
        String key1 = "attr1";
        String val1 = "p1 p2 p3";
        String key2 = "attr2";
        String val2 = "";
        HashMap<String, String> dummyMap = new HashMap<>();
        dummyMap.put(key0, val0);
        dummyMap.put(key1, val1);
        dummyMap.put(key2, val2);
        return dummyMap;
    }


    @Test
    public void createAndDeleteFile_test() {
        Storage storage = getStorageInstance();
        assert storage != null;
        String debugFilename = Storage.FILE_DEBUG;
        // Create test
        File debugFile;
        try {
            debugFile = storage.getFile(debugFilename);
        } catch (StorageException exception) {
            fail("Failed to create and get new file!");
            return;
        }
        // Remove test
        try {
            storage.deleteFile(debugFilename);
        } catch (StorageException exception) {
            fail("Failed to delete file!");
            return;
        }
        // Sanity check that file actually is deleted
        assertFalse(debugFile.exists());
    }

    @Test
    public void tokenizeHashmap_test() {
        Storage storage = getStorageInstance();
        if (storage == null) {
            fail("Storage is null!");
        }

        HashMap<String, String> testMap = new HashMap<>();
        String key0 = "attr0";
        String val0 = "";
        String key1 = "attr1";
        String val1 = "p1 p2 p3";

        testMap.put(key0, val0);
        testMap.put(key1, val1);
        String result = storage.tokenizeHashmap(testMap);
        String expected = " --attr0 \n"
                + " --attr1 p1 p2 p3\n";
        assertEquals(expected, result);
    }

    @Test
    public void detokenizeDataString_test() {
        Storage storage = getStorageInstance();
        if (storage == null) {
            fail("Storage is null!");
        }
        String dataString = " --attr0 p\n"
                + " --attr1 p1 p2 p3\n"
                + " --attr2";
        String key0 = "attr0";
        String val0 = "p";
        String key1 = "attr1";
        String val1 = "p1 p2 p3";
        String key2 = "attr2";
        String val2 = "";
        HashMap<String, String> result = storage.detokenizeDataString(dataString);

        if (result.containsKey(key0)) {
            assertEquals(val0, result.get(key0));
        } else {
            fail("Missing key0");
        }

        if (result.containsKey(key1)) {
            assertEquals(val1, result.get(key1));
        } else {
            fail("Missing key1");
        }

        if (result.containsKey(key2)) {
            assertEquals(val2, result.get(key2));
        } else {
            fail("Missing key2");
        }
    }

    /**
     * Tests the end-to-end of saving and loading
     */
    @Test
    public void saveAndLoadData_test() {
        Storage storage = getStorageInstance();
        String debugFilename = Storage.FILE_DEBUG;
        if (storage == null) {
            fail("Storage is null!");
        }
        // Test saving logic
        HashMap<String, String> dataMap = createValidDummyMap();
        try {
            storage.saveData(dataMap, debugFilename);
        } catch (StorageException exception) {
            fail("Storage failed to save data!");
        }
        // Test loading logic
        HashMap<String, String> result = new HashMap<>();
        try {
            result = storage.loadData(debugFilename);
        } catch (StorageException exception) {
            fail("Storage failed to load data!");
        }
        assertEquals(result, dataMap);
        // Cleanup file
        try {
            storage.deleteFile(debugFilename);
        } catch (StorageException exception) {
            fail("Failed to cleanup file!");
        }
    }
}
