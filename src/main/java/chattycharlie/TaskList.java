package chattycharlie;

import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;
import java.util.ArrayList;

/**
 * Represents a list of tasks that the user inputs. A <code>TaskList</code> object allows
 * users to add, delete, mark, unmark, and view tasks, providing the core functionality for the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Contructs an empty <code>TaskList</code> object with no task
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        size = 0;
    }

    /**
     * Constructs a <code>TaskList</code> object based on an existing <code>TaskList</code> object.
     * Copies the tasks in the provided list into this new list.
     *
     * @param list is the existing <code>TaskList</code> to copy tasks from
     */
    public TaskList(TaskList list){
        this.tasks = list.tasks;
        this.size = list.size;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        size++;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An <code>ArrayList</code> containing all the task in the list.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Retrieves a specific task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Return the number of task currently in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Marks a task as done, if the index is valid.
     * If the index is not valid, it displays an error message.
     *
     * @param index The index of the task to be marked
     */
    public void markTask(int index) {
        Ui ui = new Ui();
        if (index >= 0 && index < size) {
            tasks.get(index).markTask();
            int remainingTask = countUnmarkedTasks();
            ui.displayMarkingText("yay, 1 down! ", remainingTask);
        } else {
            ui.displayError("Invalid task number.");
        }
    }

    /**
     * Unmarks a task as not done, if the index is valid.
     * If the index is not valid, it displays an error message.
     *
     * @param index The index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        Ui ui = new Ui();
        if (index >= 0 && index < size) {
            tasks.get(index).unmarkTask();
            int remainingTask = countUnmarkedTasks();
            ui.displayMarkingText("Hmmm, not quite done yet, ", remainingTask);
        } else {
            ui.displayError("Invalid task number.");
        }
    }

    /**
     * Deletes a task, if the index is valid.
     * If the index is not valid, it displays an error message.
     * Once deleted, it displays the count of remaining task left.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Ui ui = new Ui();
        if (index >= 0 && index < size) {
            int remainingTask;
            if(tasks.get(index).getIsDoneStatus()) {
                remainingTask = countUnmarkedTasks();
            } else {
                remainingTask = countUnmarkedTasks() -1;
            }
            ui.displayDeletedTask("Task is removed. Pending task: ", remainingTask);
            ui.displayTask(tasks.get(index));
            tasks.remove(index);
            size--;
        }
    }

    /**
     * Prints all task in the task list, with all the details.
     * Displays a list header, followed by the number of task left unmarked and then the list of tasks.
     *
     */
    public void printList() {
        int remainingTask = countUnmarkedTasks();
        Ui ui = new Ui();
        ui.displayListHeader(remainingTask);
        for (int i = 0; i < size; i++) {
            int number = i+1;
            Task task = tasks.get(i);
            switch (task.getType()) {
            case TODO:
                Todo todoTask = (Todo) task;
                ui.displayTaskInList(todoTask, number);
                break;
            case DEADLINE:
                Deadline deadlineTask = (Deadline) task;
                ui.displayTaskInList(deadlineTask, number);
                break;
            case EVENT:
                Event eventTask = (Event) task;
                ui.displayTaskInList(eventTask, number);
                break;
            default:
            break;
            }
        }
        ui.displayLine();
    }

    /**
     * Counts the number of tasks that are currently unmarked.
     *
     * @return The number of unmarked tasks.
     */
    public int countUnmarkedTasks() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!tasks.get(i).getIsDoneStatus()) {
                count++;
            }
        }
        return count;
    }
}