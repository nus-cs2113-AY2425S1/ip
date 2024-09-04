import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Hsien {

    public static void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    public static void printLine() {
        System.out.println("\n" + "-".repeat(50) + "\n");
    }

    public static void printList(ArrayList<Task> tasks) {
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%s. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot");
        printLine();

        ArrayList<Task> messages = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter a commnd/add task: ");
            String command = in.nextLine();

            String[] parts = command.split(" ");
            String action = parts[0];
            String desc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

            Task newTask = null;

            // Create Task object based on action
            if (action.equals("todo")) {
                newTask = new Todo(desc);
            } else if (action.equals("deadline")) {
                newTask = new Deadline(desc);
            } else if (action.equals("event")) {
                newTask = new Event(desc);
            } else {
                newTask = new Task(command);
            }

            if (action.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                break;
            } else if (action.equals("list")) {
                printList(messages);
            } else if (action.contains("mark")) {
                // Get the task index
                int index = Integer.parseInt(desc);
                if (action.equals("unmark")) {
                    System.out.println("You marked " + index + " as unmarked");
                    messages.get(index-1).unmark();
                } else {
                    System.out.println("You marked " + index + " as marked");
                    messages.get(index-1).mark();
                }
                System.out.println(messages.get(index-1).getStatusDescription());
            } else {
                messages.add(newTask);
                System.out.println("Added command: " + newTask.getDescription());
                System.out.println(String.format("Now you have [%d] tasks in the list.", messages.size()));
            }
            printLine();
        }

        in.close();
    }
}
