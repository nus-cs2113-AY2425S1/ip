package jeff.helper;

import jeff.exception.InvalidFormatException;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The <code>Storage</code> class is responsible for loading and saving tasks
 * to and from a specified text file. It handles the parsing of task data
 * and ensures that tasks are correctly formatted before they are added
 * to the task list.
 */
public class Storage {

    private String filePath;

    private static final int TODO_FILE_FIELD_LENGTH = 3;
    private static final int DEADLINE_FILE_FIELD_LENGTH = 4;
    private static final int EVENT_FILE_FIELD_LENGTH = 5;

    private static final int FILE_TASK_TASK_INDEX = 0;
    private static final int FILE_TASK_MARK_INDEX = 1;
    private static final int FILE_TASK_DESC_INDEX = 2;
    private static final int FILE_TASK_FIELD1_INDEX = 3;
    private static final int FILE_TASK_FIELD2_INDEX = 4;

    /**
     * Constructs a <code>Storage</code> object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Processes a Todo task from the given details.
            *
            * @param taskDetails An array containing the details of the Todo task.
            * @return A <code>Todo</code> object representing the task.
            * @throws InvalidFormatException If the task details are invalid.
     */
    private Task processFileTodo(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == TODO_FILE_FIELD_LENGTH) {
            return new Todo(taskDetails[FILE_TASK_DESC_INDEX]);
        } else {
            throw new InvalidFormatException("todo");
        }
    }

    /**
     * Processes a Deadline task from the given details.
     *
     * @param taskDetails An array containing the details of the Deadline task.
     * @return A <code>Deadline</code> object representing the task.
     * @throws InvalidFormatException If the task details are invalid.
     */
    private Task processFileDeadline(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == DEADLINE_FILE_FIELD_LENGTH) {
             return new Deadline(taskDetails[FILE_TASK_DESC_INDEX], taskDetails[FILE_TASK_FIELD1_INDEX]);
        } else {
            throw new InvalidFormatException("deadline");
        }
    }

    /**
     * Processes an Event task from the given details.
     *
     * @param taskDetails An array containing the details of the Event task.
     * @return An <code>Event</code> object representing the task.
     * @throws InvalidFormatException If the task details are invalid.
     */
    private Task processFileEvent(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == EVENT_FILE_FIELD_LENGTH) {
            return new Event(taskDetails[FILE_TASK_DESC_INDEX], taskDetails[FILE_TASK_FIELD1_INDEX],
                    taskDetails[FILE_TASK_FIELD2_INDEX]);
        } else {
            throw new InvalidFormatException("event");
        }
    }

    /**
     * Processes different task types based on the provided details.
     *
     * @param taskDetails An array containing the details of the task.
     * @return A <code>Task</code> object representing the task.
     * @throws InvalidFormatException If the task type is unknown or invalid.
     */
    private Task processFileTaskTypes(String[] taskDetails) throws InvalidFormatException {
        String taskType = taskDetails[FILE_TASK_TASK_INDEX];

        switch (taskType) {
        case "T":
            return processFileTodo(taskDetails);
        case "D":
            return processFileDeadline(taskDetails);
        case "E":
            return processFileEvent(taskDetails);
        default:
            throw new InvalidFormatException("Unknown task type");
        }
    }

    /**
     * Splits a task line into its various fields and validates them.
     * They are valid if they are not empty.
     *
     * @param taskLine The line representing a task in the text file.
     * @return An array of strings containing the task details.
     * @throws InvalidFormatException If any field is empty or the format is invalid.
     */
    private String[] getFileTaskDetails(String taskLine) throws InvalidFormatException {
        String[] taskDetails = taskLine.split("\\|");

        //Remove starting and trailing spaces, and check if the fields are empty
        for(int i = 0; i < taskDetails.length; i++){
            taskDetails[i] = taskDetails[i].trim();
            if(taskDetails[i].isEmpty()){
                throw new InvalidFormatException("Empty task field");
            }
        }
        return taskDetails;
    }

    /**
     * Processes a task line and adds the task to the provided task list if valid.
     *
     * @param taskLine The line representing a task in the text file.
     * @param taskList The list to which the task will be added.
     * @throws InvalidFormatException If the task format is invalid.
     */
    private void processFileTasks(String taskLine, ArrayList<Task> taskList) throws InvalidFormatException {
        try {
            //Splits the taskDetails and ensures the fields are not empty
            String[] taskDetails = getFileTaskDetails(taskLine);

            //Processes different the different task types and add them to taskList if valid
            taskList.add(processFileTaskTypes(taskDetails));

            if (taskDetails[FILE_TASK_MARK_INDEX].equals("1")) {
                taskList.get(taskList.size() - 1).setIsDone(true);
            }
        } catch (InvalidFormatException errorMessage) {
            throw new InvalidFormatException(
                    "Error: " + errorMessage.getMessage() + System.lineSeparator() +
                    "Text File input: " + taskLine);
        }
    }

    /**
     * Creates the task file if it does not exist.
     *
     * @param taskFile The file to be created.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    private void createTaskFile(File taskFile) throws IOException {
        taskFile.createNewFile();
    }

    /**
     * Creates the data directory if it does not exist.
     *
     * @param taskFile The file for which the directory will be created.
     */
    private void createDataDirectory(File taskFile) {
        File directory = taskFile.getParentFile();
        //Check if directory exists
        if(!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Loads tasks from the specified text file into a list.
     *
     * @return An <code>ArrayList</code> of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws InvalidFormatException If the file format is invalid.
     */
    public ArrayList<Task> loadTaskList() throws IOException, InvalidFormatException {
        File taskFile = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        //Creates directory if it does not yet exist. Otherwise, it does nothing.
        createDataDirectory(taskFile);

        //Creates task file if it does not yet exist. Otherwise, it does nothing.
        createTaskFile(taskFile);

        Scanner fileScanner = new Scanner(taskFile);
        //Scans through all the lines in the txt file, and adds them to the taskList
        while (fileScanner.hasNext()) {
            processFileTasks(fileScanner.nextLine(), taskList);
        }
        return taskList;
    }

    /**
     * Writes the current task list to the specified file.
     * This method is called every time a task is added, deleted, marked or unmarked.
     *
     * @param tasks The task list to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeFileTask(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder fileContents = new StringBuilder();
        for (int i = 1; i <= tasks.getCount(); i++) {
            fileContents.append(tasks.getTask(i - 1).fileContent()).append(System.lineSeparator());
        }
        fw.write(fileContents.toString());
        fw.close();
    }
}
