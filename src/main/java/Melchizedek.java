import java.util.Arrays;
import java.util.Scanner;

public class Melchizedek {

    public static TaskList taskList = new TaskList();
    public static final String SEPARATOR = "\t____________________________________________________________";

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void sayHelloToUser() {
        printSeparator();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printSeparator();
    }

    public static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printSeparator();
    }

    public static void listCommands() {
        System.out.println("\tHere is a list of commands:");
        System.out.println("\tto add a todo: todo *description*");
        System.out.println("\tto add a deadline: deadline *description* /by *time*");
        System.out.println("\tto add an event: event *description* /from *time* /to *time*");
        System.out.println("\tto mark a task as done: mark *task number*");
        System.out.println("\tto unmark a task as done: unmark *task number*");
        System.out.println("\tto display all tasks on the list: list");
        System.out.println("\tto exit: bye");
    }

    public static void main(String[] args) {
        sayHelloToUser();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");

            printSeparator();

            switch (tokens[0].toLowerCase()) {
            case "bye":
                sayByeToUser();
                return;

            case "list":
                taskList.printTaskList();
                break;

            case "mark":
                try {
                    taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOh no! Please specify which task number to mark.");
                    System.out.println("\tExample: mark 3");
                }
                break;

            case "unmark":
                try {
                    taskList.unmarkTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOh no! Please specify which task number to unmark.");
                    System.out.println("\tExample: unmark 2");
                }
                break;

            case "todo":
                try {
                    taskList.addTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOops! You cannot add a todo with no description!");
                    System.out.println("\tExample: todo coding assignment");
                }
                break;

            case "deadline":
                try {
                    taskList.addDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOops! You cannot add a deadline with no description!");
                    System.out.println("\tExample: deadline coding assignment /by 12pm");
                }
                break;

            case "event":
                try {
                    taskList.addEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOops! You cannot add an event with no description!");
                    System.out.println("\tExample: event coding lecture /from 2pm /to 4pm");
                }
                break;

            default:
                System.out.println("\tSorry but that is not a valid command!");
                listCommands();
                break;
            }

            printSeparator();
        }
    }
}
