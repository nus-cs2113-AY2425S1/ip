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

    public Storage(String filePath){
        this.filePath = filePath;
    }

    //Adds todo from txt file to taskList
    private Task processFileTodo(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == TODO_FILE_FIELD_LENGTH) {
            return new Todo(taskDetails[FILE_TASK_DESC_INDEX]);
        } else {
            throw new InvalidFormatException("todo");
        }
    }

    //Adds Deadline from txt file to taskList
    private Task processFileDeadline(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == DEADLINE_FILE_FIELD_LENGTH) {
             return new Deadline(taskDetails[FILE_TASK_DESC_INDEX], taskDetails[FILE_TASK_FIELD1_INDEX]);
        } else {
            throw new InvalidFormatException("deadline");
        }
    }

    //Adds Event from txt file to taskList
    private Task processFileEvent(String[] taskDetails) throws InvalidFormatException {
        if (taskDetails.length == EVENT_FILE_FIELD_LENGTH) {
            return new Event(taskDetails[FILE_TASK_DESC_INDEX], taskDetails[FILE_TASK_FIELD1_INDEX],
                    taskDetails[FILE_TASK_FIELD2_INDEX]);
        } else {
            throw new InvalidFormatException("event");
        }
    }

    //Processes the different task types from txt file
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

    //Splits taskLine into the various fields
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

    //Adds the task into the taskList if valid
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

    //Creates txt task file if it doesn't exist, do nothing if it does
    private void createTaskFile(File taskFile) throws IOException {
        taskFile.createNewFile();
    }

    //Creates 'data' directory in the root address if it doesn't exist, do nothing if it does
    private void createDataDirectory(File taskFile) {
        File directory = taskFile.getParentFile();
        //Check if directory exists
        if(!directory.exists()) {
            directory.mkdirs();
        }
    }

    //Load the tasks from the txt file into list here
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

    //Rewrites taskList to hard drive, runs every time a task is added, or marked/unmarked
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
