import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods
 * to manipulate and display the tasks.
 */
public class TaskList {

    
    public static UI ui = new UI();
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the task list and displays the updated list count.
     * @param task the task to be added
     */
    public static void addTaskToList(Task task) {
        tasks.add(task);
        ui.showLine();
        System.out.println("added: ");
        System.out.println("    " + task);
        System.out.println("you have " + tasks.size() + " quaggin tasks to do! get to work!");
        ui.showLine();
    }

    /**
     * Adds a new Todo task to the task list and saves the list.
     * @param description the description of the Todo task
     */
    public static void addTodoToList(String description) {
        Todo todo = new Todo(description);
        addTaskToList(todo);
        Storage.saveToFile();
    }

    /**
     * Adds a new Deadline task to the task list and saves the list.
     * @param description the description of the Deadline task
     * @param by the due date and time of the Deadline task
     */
    public static void addDeadlineToList(String description, LocalDateTime by) {
        Deadline deadline = new Deadline(description, by);
        addTaskToList(deadline);
        Storage.saveToFile();
    }

    /**
     * Adds a new Event task to the task list and saves the list.
     * @param description the description of the Event task
     * @param from the start date and time of the Event
     * @param to the end date and time of the Event
     */
    public static void addEventToList(String description, LocalDateTime from, LocalDateTime to) {
        Event event = new Event(description, from, to);
        addTaskToList(event);
        Storage.saveToFile();
    }

    /**
     * Displays all tasks in the task list.
     * If the list is empty, it prints a message.
     */
    public static void displayList() {
        if (!tasks.isEmpty()) {
            ui.showLine();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            ui.showLine();
        } else {
            System.out.println("nothing in list! quag");
        }
    }

    /**
     * Marks a task as done based on the task index and saves the task list.
     * If task is already marked, it prints a message
     * @param index the index of the task in the list (1-based)
     */
    public static void markAsDone(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isDone) {
                ui.showLine();
                ui.alreadyMarked();
                System.out.println("  " + task);
                ui.showLine();
            } else {
                task.markAsDone();
                Storage.saveToFile();
                ui.showLine();
                System.out.println("quag! you're done with this task :");
                System.out.println("  " + task);
                ui.showLine();
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    /**
     * Marks a task as not done based on the task index and saves the task list.
     * If task has not been done yet, it prints a message.
     * @param index the index of the task in the list (1-based)
     */
    public static void markAsNotDone(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (!task.isDone) {
                ui.showLine();
                ui.alreadyUnmarked();
                System.out.println("  " + task);
                ui.showLine();
            } else {
                task.markAsNotDone();
                Storage.saveToFile();
                ui.showLine();
                System.out.println("quag! you're NOT done with this task :");
                System.out.println("  " + task);
                ui.showLine();
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    /**
     * Deletes a task from the task list based on the task index and saves the task list.
     * @param taskIndex the index of the task to delete (1-based)
     */
    public static void deleteTask(int taskIndex) {
        ui.showLine();
        System.out.println("quag! deleted this task :");
        System.out.println("  " + tasks.get(taskIndex - 1));
        tasks.remove(taskIndex - 1);
        System.out.println("You have " + tasks.size() + " quaggin tasks to do! get to work!");
        ui.showLine();
        Storage.saveToFile();
    }

    /**
     * Prints all tasks that have deadlines or events occurring on a specific date.
     * @param dueDate the date for which tasks are to be printed
     */
    public static void printTasksOnDate( LocalDate dueDate) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.by.toLocalDate().equals(dueDate)) {
                    System.out.println("  " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.from.toLocalDate().equals(dueDate)) {
                    System.out.println("  " + task);
                }
            }
        }
    }

    /**
     * Prints all tasks that contain a specific keyword in their description.
     * @param keyword the keyword to search for in task descriptions
     */
    public static void printTaskWithKeyword(String keyword) {
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + task);
            }
        }
    }
}

