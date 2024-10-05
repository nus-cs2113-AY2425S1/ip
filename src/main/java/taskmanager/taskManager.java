package taskmanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import filemanager.Storage;

/**
 * The Storage class manages the task list and handles the interaction
 * between the in-memory task list and the file where tasks are saved.
 * It provides methods to insert, delete, mark, unmark, list, and clear tasks,
 * as well as safe and load tasks to and from a file using the FileManager.
 */

public class taskManager {

    Storage fileManager;
    private ArrayList<Task> taskList;

    public taskManager() {
        taskList = new ArrayList<>();
        fileManager = new Storage("C:\\Users\\ASUS\\Documents\\NUS\\Yr 2 Sem 1\\CS2113\\ip\\.\\data\\tasks.txt");
        taskList = fileManager.loadTasks();
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The list of tasks.
     */

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Saves the current list of tasks to the file.
     */

    public void saveTasks() {
        fileManager.saveTasksToFile(taskList);
    }

    /**
     * Inserts a task into the task list, saves the updated list to the file,
     * and displays the added task and the total number of tasks.
     *
     * @param task The task to be inserted into the list.
     */

    public void storageInsert(Task task) {
        taskList.add(task);
        saveTasks();
        int numOfTasks = taskList.size();

        System.out.println("Got it. I've added this task:");
        System.out.print(task.getStatusIcon() + " " + task.getContents());

        if (task instanceof Deadline) {
            System.out.print(" (by: " + ((Deadline) task).getDeadline() + ")");
        } else if (task instanceof Event) {
            System.out.print(" (from: " + ((Event) task).getStart() + " to: " + ((Event) task).getEnd() + ")");
        }

        System.out.println();
        System.out.println("Now you have " + numOfTasks + " tasks in your list.");
    }

    /**
     * Deletes a task at the specified index from the task list, saves the updated list,
     * and throws an InvalidCommandException if the index is out of bounds.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidCommandException If the task at the given index does not exist.
     */

    public void storageDelete(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.remove(index - 1);
        saveTasks();
    }

    /**
     * Marks a task at the specified index as completed, saves the updated list,
     * and throws an InvalidCommandException if the index is out of bounds.
     *
     * @param index The index of the task to be marked.
     * @throws InvalidCommandException If the task at the given index does not exist.
     */

    public void storageMark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).setStatus();
        saveTasks();
    }

    /**
     * Unmarks a task at the specified index as completed, saves the updated list,
     * and throws an InvalidCommandException if the index is out of bounds.
     *
     * @param index The index of the task to be marked.
     * @throws InvalidCommandException If the task at the given index does not exist.
     */

    public void storageUnmark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).unsetStatus();
        saveTasks();
    }

    /**
     * Prints the whole list.
     */

    public void storageList() {
        if (taskList.isEmpty()){
            System.out.println("Your task list is empty!");
        }else {
            System.out.println("Here is your current list: ");
        }

        int index = 0;

        for (Task task : taskList) {
            if (task instanceof Deadline) {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task
                        + " (by: " + ((Deadline) task).getDeadline() + ")");

            } else if (task instanceof Event) {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task
                        + " (from: " + ((Event) task).getStart()
                        + " to: " + ((Event) task).getEnd() + ")");

            } else {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task);
            }
            index++;
        }
    }

    /**
     * Prints the task at the specified index as a string
     *
     * @param index The index of the task to be printed.
     */

    public void storagePrintTask(int index){
        Task task = taskList.get(index - 1);
        if (task instanceof Deadline) {
            System.out.println((index) + "." + task.getStatusIcon() + " " + task
                    + " (by: " + ((Deadline) task).getDeadline() + ")");

        }else if (task instanceof Event) {
            System.out.println((index) + "." + task.getStatusIcon() + " " + task
                    + " (from: " + ((Event) task).getStart()
                    + " to: " + ((Event) task).getEnd() + ")");

        }else{
            System.out.println((index) + "." + task.getStatusIcon() + " " + task);
        }
    }

    /**
     * Clears the whole list and makes it empty.
     */

    public void storageClear(){
        taskList.clear();
        saveTasks();
    }
}

