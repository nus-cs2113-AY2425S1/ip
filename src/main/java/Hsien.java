import java.util.Scanner;
import java.util.ArrayList;

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
        for (Task task : tasks) {
            System.out.printf("%s. %s%n", counter, task.getStatusDescription());
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
            Task newTask = new Task(command);
            if (command.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                break;
            } else if (command.equals("list")) {
                printList(messages);
            } else if (command.contains("mark")) {
                // Get the task index
                String[] temp = command.split(" ");
                int index = Integer.parseInt(temp[temp.length-1]);
                if (command.contains("unmark")) {
                    System.out.println("You marked " + index + " as unmarked");
                    messages.get(index-1).unmark();
                } else {
                    System.out.println("You marked " + index + " as marked");
                    messages.get(index-1).mark();
                }
                System.out.println(messages.get(index-1).getStatusDescription());
            } else {
                messages.add(newTask);
                System.out.println("Added command: " + command);
            }
            printLine();
        }

        in.close();
    }
}
