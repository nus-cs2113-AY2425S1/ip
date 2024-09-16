package hsien;

import hsien.task.*;
import hsien.exception.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        if (tasks.isEmpty()) {
            System.out.println("List is currently empty. Please add a task!");
            return;
        }
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%s. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    public static void printCommands(List<String> validCommands) {
        System.out.println("These are the possible commands:");
        for (int i=1; i<= validCommands.size(); i+=1) {
            System.out.printf("%d. %s\n", i, validCommands.get(i - 1));
        }
    }

    public static void deleteTask(ArrayList<Task> messages, int index) {
        System.out.println("Noted I have removed this task");
        System.out.println(messages.get(index).getStatusDescription());
        messages.remove(index);
        System.out.printf("Now you have %d tasks in the list\n", messages.size());
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot...");
        printLine();

        List<String> validCommands = Arrays.asList("bye", "list", "mark", "unmark", "delete", "todo", "deadline", "event");
        ArrayList<Task> messages = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            printCommands(validCommands);
            System.out.print("Please enter a command/add task (type 'bye' to exit): ");
            String command = in.nextLine();

            String[] parts = command.split(" ");
            String desc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
            // Extract out the exact command
            command = parts[0];

            try {
                // Check if valid commmand
                if (!validCommands.contains(command)) {
                    throw new HsienException();
                }
            } catch (HsienException e) {
                System.out.println("Please enter a valid command from the list! ");
                printLine();
                continue;
            }

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
                boolean isMarking = command.equals("mark");

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
            } else if (command.equals("delete")) {
                try {
                    deleteTask(messages, Integer.parseInt(desc)-1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format");
                }
            } else {
                // Empty task
                try {
                    if (desc.isEmpty()) {
                        throw new HsienException();
                    }
                } catch (HsienException e) {
                    System.out.println("Description cannot be left empty");
                    printLine();
                    continue;
                }

                String tempDesc;

                // Create Task object based on action
                if (command.equals("todo")) {
                    newTask = new Todo(desc);
                } else if (command.equals("deadline")) {
                    tempDesc = desc.split("/by")[0].trim();
                    String byDate = desc.split("/by")[1].trim();
                    newTask = new Deadline(tempDesc, byDate);
                } else {
                    tempDesc = desc.split("/from")[0].trim();
                    String[] dates = desc.split("/from")[1].split("/to");
                    String fromDate = dates[0].trim();
                    String toDate = dates[1].trim();
                    newTask = new Event(tempDesc, fromDate, toDate);
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
