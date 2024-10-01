package hsien.tasklist;

import hsien.task.Task;
import hsien.task.Deadline;
import hsien.task.Event;
import hsien.task.Todo;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * It has the relevant operations to add/delete tasks from the list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object and initializes the list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return an ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /**
     * Prints the current list of tasks.
     *
     * @param tasks the list of tasks to print
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List is currently empty. Please add a task!");
            return;
        }
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%d. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    /**
     * Adds a new task to the task list based on the command and description.
     *
     * @param command  the command that specifies the type of task
     * @param desc     the description of the task
     * @param fromDate the start date of the task (if applicable)
     * @param toDate   the end date of the task (if applicable)
     * @param byDate   the due date of the task (if applicable)
     */

    public void addTask(String command, String desc, String fromDate, String toDate, String byDate) {
        Task newTask = null;

        if (command.equals("todo")) {
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            newTask = new Deadline(desc, byDate);
        } else {
            newTask = new Event(desc, fromDate, toDate);
        }

        tasks.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.printf("Now you have [%d] tasks in the list.%n", tasks.size());
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index the index of the task to be deleted
     */
    public void deleteTask(int index) {
        try {
            System.out.println("Removing tasks...");
            System.out.println(tasks.get(index).getStatusDescription());
            tasks.remove(index);
            System.out.println("Noted I have remove above tasks");
            System.out.printf("Now you have %d tasks in the list%n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            printList(tasks);
            System.out.println("Open your eyes and delete only if the task exists");
        }
    }

    /**
     * Marks or unmarks a task based on the command and description.
     *
     * @param command the command indicating whether to mark or unmark the task
     * @param desc    the index of the task to be marked/unmarked
     */
    public void handleMark(String command, String desc) {
        int index = Integer.parseInt(desc);
        boolean isMarking = command.equals("mark");

        if (isMarking) {
            tasks.get(index - 1).mark();
            System.out.println("You marked " + index + " as marked");
        } else {
            tasks.get(index - 1).unmark();
            System.out.println("You marked " + index + " as unmarked");
        }

        System.out.println(tasks.get(index - 1).getStatusDescription());
    }


    /**
     * Finds and prints tasks that contain the specified query in their description.
     *
     * @param query the string to search for in the task descriptions
     */
    public void findTask(String query) {
        int counter = 1;
        boolean foundMatch = false;

        for (Task t : tasks) {
            if (t.getDescription().contains(query)) {
                if (!foundMatch) {
                    System.out.println("These are the matching tasks in your list:");
                    foundMatch = true;
                }
                System.out.printf("%d. %s%n", counter, t.getStatusDescription());
                counter++;
            }
        }

        // No results
        if (!foundMatch) {
            System.out.println("No matching tasks found");
        }
    }
}
