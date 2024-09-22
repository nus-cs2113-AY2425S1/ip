package nell.storage;

import nell.TaskList;
import nell.common.Messages;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles the reading and writing of data between a data file and a list of tasks.
 * A <code>Storage</code> object handles the saving and loading of data between a <code>File</code>
 * and a <code>TaskList</code>
 */
public class Storage {
    private File dataFile;
    private TaskList tasks;

    /**
     * Constructs a new Storage class using the file at the given file path
     *
     * @param filePath The file path of the file
     * @param tasks The list of tasks to which file data is loaded and saved
     */
    public Storage(String filePath, TaskList tasks) {
        try {
            this.dataFile = new File(filePath);
            this.tasks = tasks;
            File dataDirectory = this.dataFile.getParentFile();

            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(Messages.FILE_CREATE_ERROR_MESSAGE);
        }
    }

    /**
     * Parses a task from the file
     *
     * @param taskLine The file line containing the task
     */
    private void loadTask(String taskLine) {
        String[] taskParameters = taskLine.split("\\|");
        String taskType = taskParameters[0];
        String taskDescription = taskParameters[2];

        boolean taskIsDone = taskParameters[1].equals("X");

        switch (taskType) {
        case "T":
            ToDo toDoToAdd = new ToDo(taskDescription, taskIsDone);
            tasks.loadTask(toDoToAdd);
            break;

        case "D":
            LocalDateTime deadlineBy = LocalDateTime.parse(taskParameters[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            Deadline deadlineToAdd = new Deadline(taskDescription, taskIsDone, deadlineBy);
            tasks.loadTask(deadlineToAdd);
            break;

        case "E":
            String eventFrom = taskParameters[3];
            String eventTo = taskParameters[4];
            Event eventToAdd = new Event(taskDescription, taskIsDone, eventFrom, eventTo);
            tasks.loadTask(eventToAdd);
            break;

        default:
            break;
        }
    }

    /**
     * Loads the data in dataFile to tasks
     */
    public void loadFromFile() {
        try {
            Scanner fileScanner = new Scanner(this.dataFile);
            while (fileScanner.hasNext()) {
                String fileTask = fileScanner.nextLine();
                loadTask(fileTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Messages.FILE_READ_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the task list data in a file
     */
    public void saveToFile() {
        try {
            this.tasks.writeListToFile(this.dataFile.getPath());
        } catch (IOException e) {
            System.out.println(Messages.FILE_SAVE_ERROR_MESSAGE);
        }
    }
}
