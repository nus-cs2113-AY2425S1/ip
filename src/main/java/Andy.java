import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Andy {

    private static List<Task> itemList = new ArrayList<>(); // List to store tasks

    public static void main(String[] args) {
        greetUser(); // Method to greet the user

        Scanner scanner = new Scanner(System.in); // Use the imported Scanner class
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.startsWith("list")) {
                handleListInput(input);
            } else if (input.startsWith("mark ")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark ")) {
                markTaskAsNotDone(input);
            } else if (input.startsWith("todo ")) {
                addItemToList(new TodoTask(input.substring(5).trim()));
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                if (parts.length == 2) {
                    addItemToList(new DeadlineTask(parts[0].trim(), parts[1].trim()));
                } else {
                    System.out.println("Invalid deadline format. Use: deadline <task> /by <time>");
                }
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ");
                if (parts.length == 2) {
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length == 2) {
                        addItemToList(new EventTask(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
                    } else {
                        System.out.println("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                    }
                } else {
                    System.out.println("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                }
            } else {
                echo(input); // Call echo method to repeat the input
            }
        }

        exit();
    }

    private static void greetUser() {
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm ANDY");
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

    private static void addItemToList(Task task) {
        itemList.add(task);
        System.out.println("Item added to the list: " + task.getDescription());
    }

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

    private static void echo(String input) {
        System.out.println("You said: " + input);
    }

    private static void exit() {
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }

    // Base Task class to represent each task in the list
    static abstract class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isDone() {
            return isDone;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        @Override
        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + description;
        }
    }

    // TodoTask class for tasks without deadlines or times
    static class TodoTask extends Task {
        public TodoTask(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    // DeadlineTask class for tasks with a deadline
    static class DeadlineTask extends Task {
        private String by;

        public DeadlineTask(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    // EventTask class for events with start and end times
    static class EventTask extends Task {
        private String from;
        private String to;

        public EventTask(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }
}