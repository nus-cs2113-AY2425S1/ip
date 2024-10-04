package Glendon;

import Glendon.task.Deadline;
import Glendon.task.Event;
import Glendon.task.Task;
import Glendon.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;
    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";
    public UI ui;

    /**
     * Construct a storage to save the files with a given file path and a specified
     * user interface of choice
     *
     * @param filePath the designated filepath to store your saved files
     * @param ui a specified user interface of choice to interact with the user
     */
    Storage(String filePath, UI ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Construct a storage to save the files with a given file path and a general user interface
     *
     * @param filePath the designated filepath to store your saved files
     */
    Storage (String filePath) {
        this.filePath = filePath;
        this.ui = new UI();
    }

    /**
     * Creates an ArrayList with the tasks saved from the previous usage
     *
     * @return an ArrayList with tasks that were previously saved
     * @throws FileNotFoundException Error when the file is not at the designated file path
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        this.loadSavedTasks(taskList);
        return taskList;
    }

    /**
     * Creates a file for saving the task and if there is no such directory it will create one
     */
    public void createTaskFile() {
        File taskFile = new File(this.filePath);
        File parentFile = taskFile.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            new File("./data").mkdirs();
        }
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            ui.fileCreationError(e);
        }
    }

    /**
     * Creates the task individually from the previous saved format and add to the ArrayList
     *
     * @param taskList an ArrayList to store the task after creation
     * @throws FileNotFoundException error when there is no such files being found on the file path
     */
    public void loadSavedTasks(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file filePath
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String savedTask = s.nextLine();
            String[] content = savedTask.split("\\|");
            switch (content[0]) {
            case TODO:
                taskList.add(new Todo(Integer.parseInt(content[1]), content[2]));
                break;
            case DEADLINE:
                taskList.add(new Deadline(Integer.parseInt(content[1]), content[2], content[3]));
                break;
            case EVENT:
                taskList.add(new Event(Integer.parseInt(content[1]), content[2], content[3], content[4]));
            }
        }
    }

    /**
     *  Saves the tasks, that have been added while using the chatbot, to a file
     *
     * @param tasks the list of tasks to be saved
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            ArrayList<Task> currentList = tasks.taskList;
            for (Task task : currentList) {
                if (task == null) {
                    break;
                }
                fw.write(task.saveToFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                ui.fileNotFoundError(filePath);
            } else if (e instanceof AccessDeniedException) {
                ui.fileAccessError(filePath);
            } else {
                ui.fileSavingError(e);
            }
        }
    }
}
