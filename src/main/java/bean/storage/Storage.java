package bean.storage;

import bean.task.Deadline;
import bean.task.Event;
import bean.task.Task;
import bean.task.Todo;
import bean.tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static bean.constants.Constants.DATA_FILE_PATH;
import static bean.constants.Constants.DELIMITER;

public class Storage {

    public final String path;

    public Storage() {
        this.path = DATA_FILE_PATH;
    }

    /**
     * Checks if data file exists and creates one if none exist.
     *
     * @throws IOException if an I/O error occurs while creating the file or its parent directory.
     */
    public static void initialiseDataFile() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);

        if (!dataFile.exists()) {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        }
    }

    /**
     * Read bean.txt to retrieve tasks from memory on start.
     *
     * @param taskList The task list to be populated.
     * @throws IOException  if an I/O error occurs while reading the file.
     */
    public static void retrieveFromDataFile(TaskList taskList) throws IOException {
        File f = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            deserialise(line, taskList);
        }
    }

    // Overwrite contents of bean.txt with updated task info when tasks are updated
    public static void overwriteDataFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);
        for (Task task : tasks) {
            fw.write(task.serialise() + "\n");
        }
        fw.close();
    }

    public static void appendNextLineToFile(String nextLine) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH, true); // create a FileWriter in append mode
        fw.write(nextLine + "\n");
        fw.close();
    }

    public static void deserialise(String toDeserialise, TaskList tasklist) throws IOException {
        if (toDeserialise == null) {
            throw new RuntimeException("Could not load saved data.");
        }

        ArrayList<Task> tasks = tasklist.getTasks();

        // 'T||<1/0>||<description>'
        // 'D||<1/0>||<description>||<by>'
        // 'E||<1/0>||<description>||<from>||<to>'
        String[] parts = toDeserialise.split(DELIMITER);
        String taskClass = parts[0];
        Boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (taskClass.equals("T")) {
            // Todo
            TaskList.addTask(new Todo(description, isDone));

        } else if (taskClass.equals("D")) {
            // Deadline
            String by = parts[3];
            TaskList.addTask(new Deadline(description, by, isDone));

        } else if (taskClass.equals("E")) {
            // Event
            String from = parts[3];
            String to = parts[4];
            TaskList.addTask(new Event(description, from, to, isDone));

        } else {
            throw new RuntimeException("Could not load saved data. File corrupted.");
        }
    }

}
