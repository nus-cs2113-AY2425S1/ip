import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Andy {

    private static List<Task> itemList = new ArrayList<>(); // List to store tasks

    // Method to greet the user
    private static void greetUser() {
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm ANDY");
        System.out.println(" ");
        System.out.println("     _    _    _    _ ");
        System.out.println("    / \\  / \\  / \\  / \\ ");
        System.out.println("   ( A )( N )( D )( Y )");
        System.out.println("    \\_/  \\_/  \\_/  \\_/ ");
        System.out.println(" ");
        System.out.println("What can I do for you?");
        System.out.println("Type 'todo <task>' to add a todo item.");
        System.out.println("Type 'deadline <task> /by <time>' to add a deadline.");
        System.out.println("Type 'event <task> /from <start time> /to <end time>' to add an event.");
        System.out.println("Type 'list' followed by an item to add it to the list.");
        System.out.println("Type 'list show' to display all items in the list.");
        System.out.println("Type 'mark <number>' to mark a task as done.");
        System.out.println("Type 'unmark <number>' to mark a task as not done.");
        System.out.println("_______________________________________");
    }

    // Main method
    public static void main(String[] args) {
        greetUser(); // Greet the user

        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Main loop for handling user input
        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0]; // Get the first word as the command

                switch (command) {


                case "delete":
                    TaskDeleter.deleteTask(itemList, input);
                    break;
                case "list":
                    handleListInput(input);
                    break;

                case "mark":
                    markTaskAsDone(input);
                    break;

                case "unmark":
                    markTaskAsNotDone(input);
                    break;

                case "todo":
                    if (input.substring(4).trim().isEmpty()) {
                        throw new AndyException("Task description cannot be empty!");
                    }
                    addItemToList(new TodoTask(input.substring(5).trim()));
                    break;

                case "deadline":
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new AndyException("Invalid deadline format. Use: deadline <task> /by <time>");
                    }
                    addItemToList(new DeadlineTask(parts[0].trim(), parts[1].trim()));
                    break;

                case "event":
                    parts = input.substring(6).split(" /from ");
                    if (parts.length != 2) {
                        throw new AndyException("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                    }
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length != 2) {
                        throw new AndyException("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                    }
                    addItemToList(new EventTask(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
                    break;

                default:
                    throw new AndyException("I'm sorry, I don't understand that command.");
                }

            } catch (AndyException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        exit();
        scanner.close(); // To avoid resource leaks.
    }

    // Method to handle list input
    private static void handleListInput(String input) {
        String[] parts = input.split(" ", 2); // Split the input into command and item

        if (parts.length > 1) {
            String command = parts[1];

            if (command.equals("show")) {
                showList(); // Correctly handle 'list show' to display the list
            } else {
                addItemToList(new TodoTask(command)); // Add the item to the list as a TodoTask
            }
        } else {
            System.out.println("Please provide a valid list command or item.");
        }
    }

    // Method to mark a task as done
    private static void markTaskAsDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            itemList.get(taskNumber).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + itemList.get(taskNumber).getDescription());
        } catch (Exception e) {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to mark a task as not done
    private static void markTaskAsNotDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            itemList.get(taskNumber).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + itemList.get(taskNumber).getDescription());
        } catch (Exception e) {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Echo method to repeat input
    private static void echo(String input) {
        System.out.println("You said: " + input);
    }

    // Method to exit the program
    private static void exit() {
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }

    // Method to add a task to the list
    private static void addItemToList(Task task) {
        itemList.add(task);
        System.out.println("Item added to the list: " + task.getDescription());
    }

    // Method to display the list
    private static void showList() {
        System.out.println("_______________________________________");
        System.out.println("Items in your list:");
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + "." + itemList.get(i));
            }
        }
        System.out.println("_______________________________________");
    }

}