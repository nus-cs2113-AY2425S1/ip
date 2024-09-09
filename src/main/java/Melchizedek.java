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
                taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
                break;

            case "unmark":
                taskList.unmarkTaskAsDone(Integer.parseInt(tokens[1]));
                break;

            case "todo":
                taskList.addTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;

            case "deadline":
                taskList.addDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;

            case "event":
                taskList.addEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;

            default:
                break;
            }
        }
    }
}
