import java.util.Scanner;

public class Melchizedek {

    public static TaskList taskList = new TaskList();

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void greet() {
        printHorizontalLine();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printHorizontalLine();
    }

    public static void exit() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println("\t" + input);
        printHorizontalLine();
    }

    public static void markTask(String input) {
        //String[] tokens = input.split(" ");
        int taskID = Integer.parseInt(input);
        taskList.markTaskDoneByID(taskID);
    }

    public static void unmarkTask(String input) {
        //String[] tokens = input.split(" ");
        int taskID = Integer.parseInt(input);
        taskList.unmarkTaskDoneByID(taskID);
    }

    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            printHorizontalLine();
            String[] tokens = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                exit();
                return;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printTaskList();
            } else if (tokens[0].equalsIgnoreCase("mark")) {
                taskList.markTaskDoneByID(Integer.parseInt(tokens[1]));
            } else if (tokens[0].equalsIgnoreCase("unmark")) {
                taskList.unmarkTaskDoneByID(Integer.parseInt(tokens[1]));
            } else {
                taskList.addToTaskList(input);
            }
        }
    }
}
