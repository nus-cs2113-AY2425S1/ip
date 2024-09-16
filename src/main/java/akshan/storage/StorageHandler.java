package akshan.storage;

import java.io.File;
import java.io.IOException;

public class StorageHandler {
    private static final String STORAGE_FOLDER_PATH = "./data";
    private static final String STORAGE_PATH = "./data/akshan.txt";

    public StorageHandler() throws IOException {
        // Create data folder if it does not exist
        File storageFolder = new File(STORAGE_FOLDER_PATH);
        if (!storageFolder.exists()) {
            if (!storageFolder.mkdir()) {
                throw new IOException("Failed to create storage folder");
            }
        }

        // Create data file if it does not exist
        File storage = new File(STORAGE_PATH);
        if (!storage.exists()) {
            if (!storage.createNewFile()) {
                throw new IOException("Failed to create storage file");
            }
        }
    }
}