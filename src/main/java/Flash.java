import java.io.File;
import java.sql.Array;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Flash {

    private static final List<Task> tasks = new ArrayList<>();

    public static void displayTasks() {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(String input) throws FlashException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + task);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public static void unMarkTask(String input) throws FlashException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markNotDone();
            System.out.println("____________________________________________________________");
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(" " + task);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public static void todo(String input) throws FlashException {
        if (input.length() <= 5) {
            throw new FlashException("Uh-oh! Description for Todo Needed!! Cannot be left empty.");
        }

        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new FlashException("Uh-oh! Description for ToDo Needed!! Cannot be left empty.");
        }

        Task task = new ToDo(description);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void deadline(String input) throws FlashException {
        try {
            String[] parts = input.replaceFirst("deadline ", "").split(" /by ");
            String description = parts[0].trim();
            String by = parts[1].trim();
            Task task = new Deadline(description, by);
            tasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for Event Needed!! Cannot be left empty.");
        }
    }

    public static void event(String input) throws FlashException {
        try {
            String[] parts = input.replaceFirst("event ", "").split(" /from | /to ");
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            Task task = new Event(description, from, to);
            tasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for Event Needed!! Cannot be left empty.");
        }
    }

    public static void deleteTask(String input) throws FlashException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber < 0 || taskNumber > tasks.size()) {
                throw new FlashException("Uh-oh! The task number entered is invalid.");
            }
            Task task = tasks.remove(taskNumber);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new FlashException("Uh-oh! The task number should be an integer.");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Flash");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while(true) {
            try {
                String input = in.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    displayTasks();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unMarkTask(input);
                } else if (input.startsWith("todo")) {
                    todo(input);
                } else if (input.startsWith("deadline")) {
                    deadline(input);
                } else if (input.startsWith("event")) {
                    event(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    throw new FlashException("Uh-oh! I don't know what that means.");
                }
            } catch (FlashException e){
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }
}
