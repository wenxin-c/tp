package wellnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import wellnus.exception.StorageException;

//@@author nichyjt

/**
 * Test that Storage's public functions work as intended.
 */
public class StorageTest {

    private static final String INVALID_FILENAME = "foobar";
    private static final String ERROR_FAIL_STORAGE_INSTANCE = "Failed to create instance of storage";
    private static final String EXPECTED_EXCEPTION_FILENAME = "Expected exception to be thrown for invalid filename";
    private static final String ERROR_CLEANUP_FILE_FAIL = "Failed to cleanup file!";
    private static final String ERROR_LOAD_FAIL = "loadData failed when loading file that does not exist"
            + " despite safety checks";
    private static final String ERROR_DELETE_FILE_NOT_EXIST_FAIL = "deleteFile failed on file not exist!";
    private static final String ERROR_STORAGE_FAIL_LOAD = "Storage failed to load data!";
    private static final String ERROR_STORAGE_FAIL_SAVE = "Storage failed to save data!";
    private static final String ERROR_STORAGE_FAIL_DELETE = "Failed to delete file!";
    private static final String ERROR_STORAGE_FAIL_CREATE = "Failed to create and get new file!";
    private static final String DEBUG_PAYLOAD_0 = "attr0 p0";
    private static final String DEBUG_PAYLOAD_1 = "attr1 p1 --p2 p3";
    private static final String DEBUG_PAYLOAD_2 = "attr2 --p1 p2 --p3 --p4";
    private static final String DEBUG_PAYLOAD_3 = "attr3";

    private Storage getStorageInstance() {

        Storage storage;
        try {
            storage = new Storage();
        } catch (StorageException exception) {
            fail(ERROR_FAIL_STORAGE_INSTANCE);
            return null;
        }
        return storage;
    }

    private ArrayList<String> getDebugStringList() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(DEBUG_PAYLOAD_0);
        stringList.add(DEBUG_PAYLOAD_1);
        stringList.add(DEBUG_PAYLOAD_2);
        stringList.add(DEBUG_PAYLOAD_3);
        return stringList;
    }

    private String getDebugTokenizedString() {
        return DEBUG_PAYLOAD_0
                + Storage.DELIMITER
                + DEBUG_PAYLOAD_1
                + Storage.DELIMITER
                + DEBUG_PAYLOAD_2
                + Storage.DELIMITER
                + DEBUG_PAYLOAD_3
                + Storage.DELIMITER;
    }

    /**
     * Test that creating and deleting a file works
     */
    @Test
    @Order(1)
    public void createAndDeleteFile_test() {
        Storage storage = getStorageInstance();
        assert storage != null;
        String debugFilename = Storage.FILE_DEBUG;
        // Create test
        File debugFile;
        try {
            debugFile = storage.getFile(debugFilename);
        } catch (StorageException exception) {
            fail(ERROR_STORAGE_FAIL_CREATE);
            return;
        }
        // Remove test
        try {
            storage.deleteFile(debugFilename);
        } catch (StorageException exception) {
            fail(ERROR_STORAGE_FAIL_DELETE);
            return;
        }
        // Sanity check that file actually is deleted
        assertFalse(debugFile.exists());
    }

    /**
     * Test that tokenizing a list data string works
     */
    @Test
    @Order(2)
    public void tokenizeHashmap_test() {
        Storage storage = getStorageInstance();
        assert storage != null;

        ArrayList<String> debugList = getDebugStringList();

        String result = storage.tokenizeStringList(debugList);
        String expected = getDebugTokenizedString();
        assertEquals(expected, result);
    }

    /**
     * Test that detokenizing data string works for a valid string
     */
    @Test
    @Order(3)
    public void detokenizeDataString_test() {
        Storage storage = getStorageInstance();
        assert storage != null;

        String dataString = getDebugTokenizedString();
        ArrayList<String> expectedList = getDebugStringList();
        ArrayList<String> result = storage.detokenizeDataString(dataString);
        assertEquals(result, expectedList);
    }

    /**
     * Tests the end-to-end of saving and loading.
     */
    @Test
    @Order(4)
    public void saveAndLoadData_test() {
        Storage storage = getStorageInstance();
        assert storage != null;

        String debugFilename = Storage.FILE_DEBUG;
        // Test saving logic
        ArrayList<String> debugList = getDebugStringList();
        try {
            storage.saveData(getDebugStringList(), debugFilename);
        } catch (StorageException exception) {
            fail(ERROR_STORAGE_FAIL_SAVE);
        }
        // Test loading logic
        ArrayList<String> result = new ArrayList<>();
        try {
            result = storage.loadData(debugFilename);
        } catch (StorageException exception) {
            fail(ERROR_STORAGE_FAIL_LOAD);
        }
        assertEquals(debugList, result);
        // Cleanup file
        try {
            storage.deleteFile(debugFilename);
        } catch (StorageException exception) {
            fail(ERROR_CLEANUP_FILE_FAIL);
        }
    }

    /**
     * Ensures that deleting a file that does not exist due to developer error does not crash WellNUS++
     */
    @Test
    @Order(5)
    public void deleteFile_fileNotExist_success() {
        Storage storage = getStorageInstance();
        assert storage != null;
        try {
            storage.deleteFile(Storage.FILE_DEBUG);
        } catch (StorageException exception) {
            fail(ERROR_DELETE_FILE_NOT_EXIST_FAIL);
        }
    }

    /**
     * Ensure that loading an un-instantiated file automatically creates the file as safety behaviour
     */
    @Test
    @Order(6)
    public void loadFile_fileNotExist() {
        Storage storage = getStorageInstance();
        assert storage != null;
        try {
            storage.loadData(Storage.FILE_DEBUG);
        } catch (StorageException exception) {
            fail(ERROR_LOAD_FAIL);
        }
        // Cleanup the debug file that was created as part of safety measures
        // deleteFile must work as the above tests on deleteFile have passed
        try {
            storage.deleteFile(Storage.FILE_DEBUG);
        } catch (StorageException exception) {
            fail(ERROR_CLEANUP_FILE_FAIL);
        }
    }

    /**
     * Test that invalid file name throws an exception on getFile
     */
    @Test
    @Order(7)
    public void getFile_invalidFileName_exceptionThrown() {
        Storage storage = getStorageInstance();
        assert storage != null;
        assertThrows(StorageException.class, () -> {
            storage.getFile(INVALID_FILENAME);
        }, EXPECTED_EXCEPTION_FILENAME);
    }

    /**
     * Test that invalid file name throws an exception on saveData
     */
    @Test
    @Order(8)
    public void saveData_invalidFileName_exceptionThrown() {
        Storage storage = getStorageInstance();
        assert storage != null;
        ArrayList<String> payload = getDebugStringList();
        assertThrows(StorageException.class, () -> {
            storage.saveData(payload, INVALID_FILENAME);
        }, EXPECTED_EXCEPTION_FILENAME);
    }

    /**
     * Test that invalid file name throws an exception on loadData
     */
    @Test
    @Order(9)
    public void loadData_invalidFileName_exceptionThrown() {
        Storage storage = getStorageInstance();
        assert storage != null;
        assertThrows(StorageException.class, () -> {
            storage.loadData(INVALID_FILENAME);
        }, EXPECTED_EXCEPTION_FILENAME);
    }

    /**
     * Test that invalid file name throws an exception on deleteFile
     */
    @Test
    @Order(10)
    public void deleteFile_invalidFileName_exceptionThrown() {
        Storage storage = getStorageInstance();
        assert storage != null;
        assertThrows(StorageException.class, () -> {
            storage.deleteFile(INVALID_FILENAME);
        }, EXPECTED_EXCEPTION_FILENAME);
    }

}
