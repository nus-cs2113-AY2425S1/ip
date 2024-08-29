import java.util.Scanner;

public class Melchizedek {

    public static TaskList taskList = new TaskList();

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void greetUser() {
        printHorizontalLine();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printHorizontalLine();
    }

    public static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        greetUser();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            printHorizontalLine();
            String[] tokens = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                sayByeToUser();
                return;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printTaskList();
            } else if (tokens[0].equalsIgnoreCase("mark")) {
                taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
            } else if (tokens[0].equalsIgnoreCase("unmark")) {
                taskList.unmarkTaskAsDone(Integer.parseInt(tokens[1]));
            } else {
                taskList.addToTaskList(input);
            }
        }
    }
}
