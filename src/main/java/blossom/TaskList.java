package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.util.ArrayList;


/**
 * A <code>TaskList</code> object corresponds to a list of tasks including
 * adding, marking, unmarking, and deleting tasks.
 * For all task-related operations within Blossom.
 */
public class TaskList {
    private final int lengthOfTodo = 5;
    private final int lengthOfDeadline = 9;
    private final int lengthOfEvent = 6;
    private final ArrayList<Task> listOfTasks;
    private final String horizontalLine = "____________________________________________________________";
    private Ui ui;

    /**
     * Constructs a new TaskList along with the Ui for user interactions.
     *
     * @param ui The Ui object used for displaying information to the user.
     */
    TaskList(Ui ui) {
        this.listOfTasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return listOfTasks;
    }

    /**
     * Marks or unmarks a task based on the given action.
     *
     * @param itemIndex The index of the task to be marked or unmarked.
     * @param action The action to perform ('mark' or 'unmark').
     */
    public void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = this.listOfTasks.get(itemIndex);
        if (item != null) {
            System.out.println(horizontalLine);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item);
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item);
            }
            System.out.println(horizontalLine);
        }
    }

    /**
     * Adds a new task based on the input.
     *
     * @param input The input string containing task details and type.
     * @throws BlossomException if there is an error in creating the task.
     */
    public void addTask (String input) throws BlossomException {
        System.out.println(horizontalLine);
        catchInputErrors(input);
        Task item = createTask(input);
        System.out.println("Ok!! I've added this task *\\ (>o<) /*");
        System.out.println(item.toString());
        this.listOfTasks.add(item);
        ui.printNumberOfTasks(this.listOfTasks);
        System.out.println(horizontalLine);
    }

    /**
     * Creates a Task object from an input string
     *
     * @param input The input string describing the task.
     * @return the created Task object.
     * @throws BlossomException if the input format is incorrect or missing necessary data.
     */
    public Task createTask(String input) throws BlossomException {
        Task item;
        if(input.contains("todo")) {
            item = new Todo(input.substring(lengthOfTodo));
        } else if (input.contains("deadline")) {
            // Split input into description and due date "by"
            String[] parts = input.substring(lengthOfDeadline).split(" /by ");
            item = new Deadline(parts[0], parts[1]);
        } else if (input.contains("event")) {
            // Split input into description and "to" and "from" date
            String[] parts = input.substring(lengthOfEvent).split(" /from | /to ");
            if (parts.length < 3) {
                throw new BlossomException("Event must include both start and end times.");
            }
            item = new Event(parts[0], parts[1], parts[2]);
        } else {
            throw new BlossomException("Sorry~~ idk what that means ( • ᴖ • ｡)");
        }
        return item;
    }

    /**
     * Checks for input errors such as empty or incorrect formats.
     *
     * @param input The input string to be checked.
     * @throws BlossomException if checks for errors fail.
     */
    public void catchInputErrors(String input) throws BlossomException {
        if (input == null || input.trim().isEmpty()) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a task cannot be empty~~ ");
        } else if (input.trim().equals("todo")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a todo cannot be empty~~ ");
        } else if (input.trim().equals("deadline")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a deadline cannot be empty~~ ");
        } else if (input.trim().equals("event")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of an event cannot be empty~~ ");
        }
    }

    /**
     * Deletes a task from the list based on the specified index.
     *
     * @param indexOfTask The index of the task to be removed.
     */
    public void deleteTask(int indexOfTask) {
        System.out.println(horizontalLine);
        System.out.println("Removing this task now! ᕙ(  •̀ ᗜ •́  )ᕗ ");
        System.out.println(this.listOfTasks.get(indexOfTask-1).toString());
        System.out.println(horizontalLine);
        this.listOfTasks.remove(indexOfTask-1);
        ui.printNumberOfTasks(this.listOfTasks);
    }

    public void findTask(String keyword) {
        System.out.println(horizontalLine);
        int orderInList = 1;
        boolean found = false;
        for(Task item : listOfTasks) {
           if(item.toString().contains(keyword)) {
               if(!found) {
                   System.out.println("Here are the matching tasks in your list! ૮ ˶ᵔ ᵕ ᵔ˶ ა");
                   found = true;
               }
               System.out.println(orderInList+ ". " + item);
           }
           orderInList++;
        }
        if(!found) {
            System.out.println("Oops, no tasks found! . ‸ .");
        }
        System.out.println(horizontalLine);
    }
}
