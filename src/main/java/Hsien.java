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
        System.out.println("Hello! I am Hsien, your personal chatbot...");
        printLine();

        ArrayList<Task> messages = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("Please enter a command/add task (type 'bye' to exit): ");
            String command = in.nextLine();

            String[] parts = command.split(" ");
            String desc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

            Task newTask = null;

            if (command.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                isRunning = false;
                continue;
            } else if (command.equals("list")) {
                printList(messages);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {

                // Get the task index
                int index = Integer.parseInt(desc);
                boolean isMarking = command.startsWith("mark");

                // Out of bounds
                if (index == 0 || index > messages.size())  {
                    System.out.println("Index out of bounds");
                    continue;
                }

                // Perform marking or unmarking
                if (isMarking) {
                    messages.get(index - 1).mark();
                    System.out.println("You marked " + index + " as marked");
                } else {
                    messages.get(index - 1).unmark();
                    System.out.println("You marked " + index + " as unmarked");
                }

                // Print status of the task
                System.out.println(messages.get(index - 1).getStatusDescription());
            } else {
                String tempDesc;

                // Create Task object based on action
                if (command.startsWith("todo")) {
                    newTask = new Todo(desc);
                } else if (command.startsWith("deadline")) {
                    tempDesc = desc.split("/by")[0].trim();
                    String byDate = desc.split("/by")[1].trim();
                    newTask = new Deadline(tempDesc, byDate);
                } else if (command.startsWith("event")) {
                    tempDesc = desc.split("/from")[0].trim();
                    String[] dates = desc.split("/from")[1].split("/to");
                    String fromDate = dates[0].trim();
                    String toDate = dates[1].trim();
                    newTask = new Event(tempDesc, fromDate, toDate);
                } else {
                    newTask = new Task(command);
                }
                messages.add(newTask);
                System.out.println("Added task: " + newTask.getDescription());
                System.out.println(String.format("Now you have [%d] tasks in the list.", messages.size()));
            }
            printLine();
        }
        in.close();
    }
}
