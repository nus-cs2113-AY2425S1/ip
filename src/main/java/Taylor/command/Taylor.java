package Taylor.command;

import Taylor.task.*;

import java.util.*;

// Main class to implement a task manager called "Taylor.command.Taylor"
public class Taylor {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        // Initialize a scanner object to take user input
        Scanner sc = new Scanner(System.in);

        // Define a separator line for display purposes
        welcome();

        // Take the first user input
        String input = sc.nextLine();

        // Initialize a list to store tasks
        TaskList tasks = new TaskList();

        // Keep accepting input until the user types "bye"
        while(true) {
            try {
                input = operate(input, tasks, sc);
                if(input.equalsIgnoreCase("bye")){
                    break;
                }
            } catch (TaylorException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            }
        }
    }

    private static String operate(String input, TaskList tasks, Scanner sc) throws TaylorException {

        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        String args = (inputParts.length > 1) ? inputParts[1] : "";
        switch (command){
            case "bye"-> {
                bye();
                System.exit(0);
            }
            case "list"->{
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + "." + tasks.get(i)); // Print tasks with numbering
                }
                System.out.println(line);
                input = sc.nextLine();
                return input;
            }
            case "mark" -> {
                int index = Integer.parseInt(args);
                try {
                    tasks.markTask(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index)); // Display the task marked as done
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    printInvalidIndex();
                }
                input = sc.nextLine();
                return input;
            }
            case "unmark"-> {
                int index = Integer.parseInt(args);
                try {
                    tasks.unmarkTask(index);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index)); // Display the task marked as not done
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    printInvalidIndex();
                }
                input = sc.nextLine();
                return input;
            }
            case "todo" -> {
                try {
                    Task task = new Todo(input.substring(5)); // Create a To_do task with the description
                    input = getString(tasks, task, sc);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                    input = sc.nextLine();
                }
                return input;
            }

            case "event" -> {
                try {
                    Event event = getEvent(input);
                    input = getString(tasks, event, sc);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("Start and end time of the event cannot be empty.");
                    System.out.println(line);
                    input = sc.nextLine();
                }
                return input;
            }

            case "deadline" ->{
                int by = input.indexOf("/by"); // Get the position of "/by"
                try {
                    String description = input.substring(9, by); // Extract task description
                    String _by = input.substring(by + 4); // Extract deadline time
                    Task task = new Deadline(description, _by); // Create a Taylor.task.Deadline task
                    input = getString(tasks, task, sc);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("Time of the deadline cannot be empty.");
                    System.out.println(line);
                    input = sc.nextLine();
                }
                return input;
            }

            default -> throw new TaylorException(line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);

        }
        return null;
    }

    private static Event getEvent(String input) {
        int from = input.indexOf("/from"); // Get the position of "/from"
        int to = input.indexOf("/to"); // Get the position of "/to"
        String description = input.substring(6, from); // Extract task description
        String _from = input.substring(from + 6, to); // Extract event start time
        String _to = input.substring(to + 4); // Extract event end time
        return new Event(description, _from, _to);
    }

    private static void printInvalidIndex() {
        System.out.println(line);
        System.out.println("Invalid index");
        System.out.println(line);
    }

    private static String getString(TaskList tasks, Task task, Scanner sc) {
        String input;
        tasks.add(task); // Add the task to the list
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task); // Display the added event
        System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
        System.out.println(line);
        input = sc.nextLine();
        return input;
    }

    private static void bye() {
        // Exit message when user types "bye"
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private static void welcome() {
        System.out.println(line);
        System.out.println("Hello! I'm Taylor.command.Taylor");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
}