package chattycharlie.userinteractions;

import chattycharlie.CharlieExceptions;
import chattycharlie.TaskList;
import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage system for saving and loading tasks.
 * The <code>Storage</code> class handles reading from and writing to the file specified by the user.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a <code>Storage</code> object with the specified file path.
     *
     * @param filePath the path of the file used for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into a <code>TaskList</code>.
     *
     * @return a <code>TaskList</code> containing all tasks read from the storage file.
     * @throws CharlieExceptions if an error occurs while loading the tasks.
     */
    public TaskList load() throws CharlieExceptions {
        TaskList list = new TaskList();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String taskText = scanner.nextLine();
                Task task = parseTask(taskText);
                if (task != null) {
                    list.addTask(task);
                }
            }
            scanner.close();
            System.out.println(StringDesign.CHARLIE + "Task have been loaded from: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Oh first time here? Welcome to a life of Productivity, lets start!");
        }

        return list;
    }

    /**
     * Parses a string representing a task and returns the corresponding <code>Task</code> object.
     *
     * @param taskText the string representing a task.
     * @return the corresponding <code>Task</code> object, or <code>null</code> if the task cannot be parsed.
     * @throws IllegalArgumentException if the task type is unknown.
     */
    public Task parseTask(String taskText) {
        taskText = taskText.trim(); //trim away the formatting
        char taskType = taskText.charAt(1); //get the commandType
        boolean isMarked = taskText.charAt(4) == 'X'; //get status for marked
        String description;
        if (taskType == 'T') {
            description = taskText.substring(7).trim();
        } else {
            int parenIndex = taskText.indexOf('(');
            description = taskText.substring(7, parenIndex).trim();  // Get description before '('
        }
        Task task;
        switch (taskType) {
        case 'T':
            task = new Todo(description);
            if (isMarked) {
                task.markTask();
            }
            return task;
        case 'D':
            String by = taskText.substring(taskText.indexOf("(by: ") + 5, taskText.length() - 1).trim();
            task = new Deadline(description, by);  // Add Deadline task
            //check if its marked
            if(isMarked) {
                task.markTask();
            }
            return task;
        case 'E':
            String eventInfo = taskText.substring(taskText.indexOf("(from: ") + 7, taskText.length() - 1).trim();
            String[] eventTimes = eventInfo.split(" to: ");
            if (eventTimes.length == 2) {
                String startTime = eventTimes[0].trim();
                String endTime = eventTimes[1].trim();
                task = new Event(description, startTime, endTime);  // Add Event task
                if (isMarked) {
                    task.markTask();
                }
                return task;
            } else {
                System.out.println("Invalid event time format: " + taskText);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
        return null;
    }

    /**
     * Saves all tasks from the <code>TaskList</code> to the storage file.
     *
     * @param list the <code>TaskList</code> containing tasks to be saved.
     */
    public void saveTasks(TaskList list) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < list.getSize(); i++) {
                Task task = list.getTask(i);
                if (task != null) {
                    writer.write(task.toSaveFormat() + "\n");
                }
            }
            writer.close();
            System.out.println(StringDesign.CHARLIE + "We saved your file in: " + filePath);
        } catch (IOException e) {
            System.out.println("An error has occurred when saving tasks: " + e.getMessage());
        }
    }

}
