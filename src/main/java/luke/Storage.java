package luke;

import luke.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Represents the file used to store tasks
 */
public class Storage {


    private final Path FILEPATH;
    public ArrayList<String> saveStrings;
    private Ui ui;

    /**
     * Constructor for Storage class
     * @param filepath Location of saved tasks file
     */
    public Storage(Path filepath) {
        ui = new Ui();
        this.FILEPATH = filepath;
        try {
            loadSavedTasks();
        } catch (IOException e) {
            ui.printReply("Error occurred loading saved tasks");
        }
    }

    /**
     * Loads data from saved tasks file into an ArrayList of strings.
     * @throws IOException if there were errors.
     */
    private void loadSavedTasks() throws IOException {
        if (!Files.exists(FILEPATH.getParent())) {
            Files.createDirectories(FILEPATH.getParent());
        }
        if (!Files.exists(FILEPATH)) {
            Files.createFile(FILEPATH);
        }
        saveStrings = (ArrayList<String>) Files.readAllLines(FILEPATH);
    }

    /**
     * Saves all tasks in the ArrayList provided into a text file
     * @param tasks ArrayList of tasks to save.
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void saveAllTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        String line;
        for (Task t : tasks) {
            line = t.toSaveString();
            fw.write(line);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
