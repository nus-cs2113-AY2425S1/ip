import java.util.Arrays;
import java.util.Scanner;

public class Mong {

    public static final int LENGTH_DEADLINE = 8;
    public static final int LENGTH_BY = 3;
    public static final int LENGTH_TODO = 4;
    public static final int LENGTH_EVENT = 5;
    public static final int LENGTH_FROM = 4;
    public static final int LENGTH_TO = 2;
    public static final String HORIZONTAL_LINE = "--------------------------------------------------";

    /**
     * Prints a horizontal line with width of 25 characters.
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out an indexed list of commands given to Mong starting from 1.
     */
    public static void printIndexedList(Task[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + 1 + ". " + list[i]);
        }
        System.out.println("MONG my god! You have " + Task.currentIndex + " task(s) in the list!");
    }

    /**
     * Add a Task of type Deadline.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(Task[] list, String input) {
        int endOfCommand = input.indexOf("deadline") + LENGTH_DEADLINE;
        int endOfBy = input.lastIndexOf("/by") + LENGTH_BY;
        int startOfBy = input.indexOf("/by");
        String description = input.substring(endOfCommand, startOfBy);
        String deadline = input.substring(endOfBy + 1);
        list[Task.currentIndex] = new Deadline(description, deadline);
        System.out.println("Mong-ed! This item has been added: ");
        System.out.println(list[Task.currentIndex - 1]);
    }

    /**
     * Add a Task of type Todo.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addTodo(Task[] list, String input) {
        int endOfCommand = input.indexOf("todo") + LENGTH_TODO;
        String description = input.substring(endOfCommand);
        list[Task.currentIndex] = new Todo(description);
        System.out.println("Mong-ed! This item has been added: ");
        System.out.println(list[Task.currentIndex - 1]);
    }

    /**
     * Add a Task of type Event.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addEvent(Task[] list, String input) {
        int endOfCommand = input.indexOf("event") + LENGTH_EVENT;
        int endOfFrom = input.lastIndexOf("/from") + LENGTH_FROM;
        int startOfFrom = input.indexOf("/from");
        int endOfTo = input.lastIndexOf("/to") + LENGTH_TO;
        int startOfTo = input.indexOf("/to");
        String description = input.substring(endOfCommand, startOfFrom);
        String from = input.substring(endOfFrom + 2, startOfTo - 1);
        String to = input.substring(endOfTo + 1);
        list[Task.currentIndex] = new Event(description, from, to);
        System.out.println("Mong-ed! This item has been added: ");
        System.out.println(list[Task.currentIndex - 1]);
    }

    /**
     * Returns the TaskType enum value of the first word in the input.
     */
    public static TaskType decipherTaskType(String command) {
        return TaskType.fromCommand(command);
    }

    /**
     * Adds the command sent by the user into a list.
     * If the command "list" is sent, the list of previous commands will be printed.
     * If the command's first word is "mark", it will set the isCompleted variable of the Task to true.
     * If the command's first word is "unmark", it will set the isCompleted variable of the Task to false.
     * If the command "bye" is sent, the program will exit.
     */
    public static void addByTask() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Task[] list = new Task[100];
        int itemIndex;
        TaskType command = decipherTaskType(input.split(" ")[0]);
        while (command != TaskType.BYE) {
            printHorizontalLine();
            switch(command) {
            case LIST:
                // print items in an indexed list
                printIndexedList(Arrays.copyOf(list, Task.currentIndex));
                break;
            case MARK:
                // the itemIndex is -1 than the input from the user
                itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                list[itemIndex].setCompleted(true);
                break;
            case UNMARK:
                // the itemIndex is -1 than the input from the user
                itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                list[itemIndex].setCompleted(false);
                break;
            case DEADLINE:
                addDeadline(list, input);
                break;
            case TODO:
                addTodo(list, input);
                break;
            case EVENT:
                addEvent(list, input);
                break;
            default:
                System.out.println("MooONG?! Not a valid command...");
                break;
            }
            printHorizontalLine();
            input = in.nextLine();
            command = decipherTaskType(input.split(" ")[0]);
        }
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "\n" +
                "___  ___                  _ \n" +
                "|  \\/  |                 | |\n" +
                "| .  . | ___  _ __   __ _| |\n" +
                "| |\\/| |/ _ \\| '_ \\ / _` | |\n" +
                "| |  | | (_) | | | | (_| |_|\n" +
                "\\_|  |_/\\___/|_| |_|\\__, (_)\n" +
                "                     __/ |  \n" +
                "                    |___/   \n" +
                "\n";
        printHorizontalLine();
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
        addByTask();
        printHorizontalLine();
        System.out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
    }
}
