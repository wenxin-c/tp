package wellnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import wellnus.exception.StorageException;

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

    private ArrayList<String> getDebugStringList() {
        String entry0 = "attr0 p0";
        String entry1 = "attr1 p1 --p2 p3";
        String entry2 = "attr2 --p1 p2 --p3 --p4";
        String entry3 = "attr3";
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(entry0);
        stringList.add(entry1);
        stringList.add(entry2);
        stringList.add(entry3);
        return stringList;
    }

    private String getDebugTokenizedString() {
        return "attr0 p0"
                + Storage.DELIMITER
                + "attr1 p1 --p2 p3"
                + Storage.DELIMITER
                + "attr2 --p1 p2 --p3 --p4"
                + Storage.DELIMITER
                + "attr3"
                + Storage.DELIMITER;
    }

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
    @Order(2)
    public void tokenizeHashmap_test() {
        Storage storage = getStorageInstance();
        assert storage != null;

        ArrayList<String> debugList = getDebugStringList();

        String result = storage.tokenizeStringList(debugList);
        String expected = getDebugTokenizedString();
        assertEquals(expected, result);
    }

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
            fail("Storage failed to save data!");
        }
        // Test loading logic
        ArrayList<String> result = new ArrayList<>();
        try {
            result = storage.loadData(debugFilename);
        } catch (StorageException exception) {
            fail("Storage failed to load data!");
        }
        assertEquals(debugList, result);
        // Cleanup file
        try {
            storage.deleteFile(debugFilename);
        } catch (StorageException exception) {
            fail("Failed to cleanup file!");
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
            fail("deleteFile failed on file not exist!");
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
            fail("loadData failed when loading file that does not exist despite safety checks");
        }
        // Cleanup
        // deleteFile must work as the above tests on deleteFile have passed
        try {
            storage.deleteFile(Storage.FILE_DEBUG);
        } catch (StorageException e) {
            fail("Failed to cleanup file!");
        }
    }

}
