import java.util.Scanner;
import java.util.ArrayList;
import exceptions.InvalidCommandException;
import exceptions.IncompleteCommandException;
import exceptions.IllegalCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Crystal {
    public static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    private static final int TODO_CHAR_COUNT = 4;
    private static final int DEADLINE_CHAR_COUNT = 8;
    private static final int EVENT_CHAR_COUNT = 5;

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayHello() {
        horizontalLine();
        System.out.println("Hola! I'm Crystal.\n"
            + "What can I do for you today?");
        horizontalLine();
    }

    public static void sayBye() {
        horizontalLine();
        System.out.println("Adios, hasta luego!");
        horizontalLine();
    }

    public static void list() {
        horizontalLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        horizontalLine();
    }

    public static void mark(int taskNumber) {
        try {
            if (taskNumber > taskCount | taskNumber < 1) {
                horizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(taskNumber);
            t.markAsDone();
            horizontalLine();
            System.out.println("YAY!! This task is now marked done:\n" + t);
            horizontalLine();
        } catch (IllegalCommandException e) {
            printExceptionMessage();
        }
    }

    public static void unmark(int taskNumber) {
        try {
            if (taskNumber > taskCount | taskNumber < 1) {
                horizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(taskNumber);
            t.unmark();
            horizontalLine();
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
            horizontalLine();
        } catch (IllegalCommandException e) {
            printExceptionMessage();
        }
    }

    public static void printAddedTaskMessage() {
        horizontalLine();
        System.out.println("Got it! I have added this task:");
        System.out.println(tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        horizontalLine();
    }

    public static void printExceptionMessage() {
        System.out.println(" Can you repeat it again?");
        horizontalLine();
    }

    public static void addTodo(String line) {
        String description = line.substring(TODO_CHAR_COUNT + 1).trim();
        Task t =  new Todo(description);
        tasks.add(t);
        printAddedTaskMessage();
    }

    public static void addDeadline(String line) {
        try {
            String[] twoParts = line.substring(DEADLINE_CHAR_COUNT + 1).trim().split(" /by ");
            if (twoParts.length != 2) {
                horizontalLine();
                throw new IncompleteCommandException("You are missing some parameters!");
            }
            String description = twoParts[0];
            String by = twoParts[1];
            Task t = new Deadline(description, by);
            tasks.add(t);
            printAddedTaskMessage();
        } catch (IncompleteCommandException e) {
            printExceptionMessage();
        }
    }

    public static void addEvent(String line) {
        try {
            String[] threeParts = line.substring(EVENT_CHAR_COUNT + 1).trim().split(" /from | /to ");
            if (threeParts.length != 3) {
                horizontalLine();
                throw new IncompleteCommandException("You are missing some parameters!");
            }
            String description = threeParts[0];
            String from = threeParts[1];
            String to = threeParts[2];
            Task t = new Event(description, from, to);
            tasks.add(t);
            printAddedTaskMessage();
        } catch (IncompleteCommandException e) {
            printExceptionMessage();
        }
    }

    public static void callingCrystal() {
        boolean isBye = false;
        String line;
        String command;
        int taskNumber;
        Scanner in = new Scanner(System.in);
        while (!isBye) {
            line = in.nextLine();
            String[] words = line.split(" ");
            command = words[0];
            try {
                switch (command) {
                case "bye":
                    isBye = true;
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    taskNumber = Integer.parseInt(words[1]);
                    mark(taskNumber);
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(words[1]);
                    unmark(taskNumber);
                    break;
                case "todo":
                    addTodo(line);
                    break;
                case "deadline":
                    addDeadline(line);
                    break;
                case "event":
                    addEvent(line);
                    break;
                default:
                    horizontalLine();
                    throw new InvalidCommandException("Did you misspell or miss out something?");
                }
            } catch (InvalidCommandException e) {
                printExceptionMessage();
            }
        }
    }

    public static void main(String[] args) {
        sayHello();
        callingCrystal();
        sayBye();
    }

}
