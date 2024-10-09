package atom.storage;

import atom.tasklist.TaskList;
import atom.exception.AtomException;
import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the txt file to the task list
 * and storing tasks from the task list to the txt file.
 */
public class Storage {

    /** File path of the txt file */
    private File file;
    //filepath = "./data/AtomList.txt"

    public Storage(String filePath) {
        File folder = new File("./data");
        file = new File(filePath);

        //create directory if it does not exist
        if (folder.mkdir()) {
            System.out.println("Folder \"data\" created.");
        } else {
            System.out.println("Folder already exists.");
        }

        //create txt file if it does not exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Oops!! An error occurred.");
        }
    }

    /**
     * Loads all the tasks stored in the txt file to an <code>ArrayList</code>.
     * <p>
     * If the file cannot be located, the <code>FileNotFoundException</code> will be caught
     * in the method.
     *
     * @return <code>ArrayList</code> containing all the tasks that needs to be added
     * to the task list stored in <code>TaskList</code>.
     */
    public ArrayList<Task> load() {
        TaskList tasks = new TaskList();
        //load data (if any) to the list
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String fileLine = fileScanner.nextLine().trim();
                String[] fileLineParams = fileLine.split("\\|");

                String taskType = fileLineParams[0].trim();
                boolean doneStatus = (fileLineParams[1].trim().equals("1"));
                String taskName = fileLineParams[2].trim();

                switch (taskType) {
                case "T":
                    loadTodoTaskToList(tasks, taskName, doneStatus);
                    break;
                case "D":
                    String taskDueDate = fileLineParams[3].trim();
                    loadDeadlineTaskToList(tasks, taskName, doneStatus, taskDueDate);
                    break;
                case "E":
                    String taskDuration = fileLineParams[3].trim();
                    loadEventTaskToList(tasks, taskName, doneStatus, taskDuration);
                    break;
                default:
                    throw new AtomException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found..");
        }
        return tasks.getTasksList();
    }

    /**
     * Loads a <code>todo</code> task to the task list in <code>TaskList</code>.
     *
     * @param tasks Task list containing tasks.
     * @param taskName Name of <code>todo</code> task.
     * @param doneStatus <code>true</code> if task is marked as done, <code>false</code> otherwise.
     */
    private void loadTodoTaskToList(TaskList tasks, String taskName, boolean doneStatus) {
        Todo todo = tasks.addTodoTask(taskName);
        if (doneStatus) {
            todo.markAsDone();
        }
    }

    /**
     * Loads a <code>deadline</code> task to the task list in <code>TaskList</code>.
     *
     * @param tasks Task list containing tasks.
     * @param taskName Name of <code>deadline</code> task.
     * @param doneStatus <code>true</code> if task is marked as done, <code>false</code> otherwise.
     * @param taskDueDate Task due date.
     */
    private void loadDeadlineTaskToList(TaskList tasks, String taskName, boolean doneStatus,
                                       String taskDueDate) {

        Deadline deadline = tasks.addDeadlineTask(taskName, taskDueDate);
        if (doneStatus) {
            deadline.markAsDone();
        }
    }

    /**
     * Loads an <code>event</code> task to the task list in <code>TaskList</code>.
     *
     * @param tasks Task list containing tasks.
     * @param taskName Name of <code>event</code> task.
     * @param doneStatus <code>true</code> if task is marked as done, <code>false</code> otherwise.
     * @param taskDuration Duration of task, from start date to end date.
     */
    private void loadEventTaskToList(TaskList tasks, String taskName, boolean doneStatus,
                                    String taskDuration) {

        String[] eventDurationParams = taskDuration.split("-");
        String eventStartDate = eventDurationParams[0].trim();
        String eventEndDate = eventDurationParams[1].trim();

        Event event = tasks.addEventTask(taskName, eventStartDate, eventEndDate);
        if (doneStatus) {
            event.markAsDone();
        }
    }

    /**
     * Stores all tasks in the task list to the txt file.
     * <p>
     * If there are any errors regarding input/output operations,
     * the <code>IOException</code> will be caught and handled in the method.
     *
     * @param tasks Task list containing tasks.
     */
    public void store(TaskList tasks) {

        ArrayList<Task> list = tasks.getTasksList();

        //write data into AtomList.txt file
        try {
            FileWriter fw = new FileWriter(file);

            for (Task task : list) {
                String separator = " | ";
                String doneStatus = (task.getStatus().equals("X")) ? "1" : "0";

                fw.write(task.setTaskType() + separator + doneStatus
                        + separator + task.getItem());

                if (task.setTaskType().equals("D")) {
                    fw.write(separator + task.getDueDate());
                } else if (task.setTaskType().equals("E")) {
                    fw.write(separator + task.getStartDate() + "-" + task.getEndDate());
                }

                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Oh no!! Something went wrong..");
        }
    }
}
