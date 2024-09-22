package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Blossom {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final ArrayList<Task> LIST_OF_TASKS = new ArrayList<Task>();
    private static final int LENGTH_OF_TODO = 5;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final int LENGTH_OF_EVENT = 6;
    private static Storage storage = new Storage("./data/blossom.txt", LIST_OF_TASKS);
    private static Ui ui = new Ui();

    public static void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = LIST_OF_TASKS.get(itemIndex);
        if (item != null) {
            System.out.println(HORIZONTAL_LINE);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item.toString());
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item.toString());
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void addTask (String input) throws BlossomException {
        System.out.println(HORIZONTAL_LINE);
        catchInputErrors(input);
        Task item = createTask(input);
        System.out.println("Ok!! I've added this task *\\ (>o<) /*");
        System.out.println(item.toString());
        LIST_OF_TASKS.add(item);
        ui.printNumberOfTasks(LIST_OF_TASKS);
        System.out.println(HORIZONTAL_LINE);
    }

    public static Task createTask(String input) throws BlossomException {
        Task item = new Task(input); // Default item if unspecified task type
        if(input.contains("todo")) {
            item = new Todo(input.substring(LENGTH_OF_TODO));
        } else if (input.contains("deadline")) {
            String[] parts = input.substring(LENGTH_OF_DEADLINE).split(" /by ");
            item = new Deadline(parts[0], parts[1]);
        } else if (input.contains("event")) {
            String[] parts = input.substring(LENGTH_OF_EVENT).split(" /from | /to ");
            if(parts.length < 3) throw new BlossomException("Event must include both start and end times.");
            item = new Event(parts[0], parts[1], parts[2]);
        } else {
            throw new BlossomException("Sorry~~ idk what that means ( • ᴖ • ｡)");
        }
        return item;
    }

    public static void catchInputErrors(String input) throws BlossomException {
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

    public static void deleteTask(int indexOfTask) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Removing this task now! ᕙ(  •̀ ᗜ •́  )ᕗ ");
        System.out.println(LIST_OF_TASKS.get(indexOfTask-1).toString());
        System.out.println(HORIZONTAL_LINE);
        LIST_OF_TASKS.remove(indexOfTask-1);
        ui.printNumberOfTasks(LIST_OF_TASKS);
    }

    public static void main(String[] args) {
        ui.printIntro();
        try {
            storage.loadTasks();
        } catch (BlossomException e) {
            throw new RuntimeException(e);
        }
        Scanner input = new Scanner(System.in);
        // Repeatedly takes in input until it's a key word
        while(input.hasNext()) {
            String line = input.nextLine();
            if(!line.equalsIgnoreCase("bye")) {
                if(line.equalsIgnoreCase("list")) {
                    ui.printItems(LIST_OF_TASKS);
                }
                else if(line.contains("mark") || line.contains("unmark")) {
                    // Call the unmark and mark function
                    String[] parsedLine = line.split(" ");
                    markAndUnmarkItem(Integer.parseInt(parsedLine[1]), parsedLine[0]);
                }
                else if (line.contains("delete")) {
                    String[] parsedLine = line.split(" ");
                    deleteTask(Integer.parseInt(parsedLine[1]));
                }
                else {
                    try {
                        addTask(line);
                    } catch (BlossomException e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println(e.getMessage());
                        System.out.println(HORIZONTAL_LINE);
                    }
                }
            } else {
                System.out.println("Bye~~~ Come visit me soon! (๑>◡<๑)");
                System.out.println(HORIZONTAL_LINE);
                input.close();
                storage.saveTasks();
                System.exit(0);
            }
        }
    }
}
