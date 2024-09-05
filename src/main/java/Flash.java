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

    public static void markTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = tasks.get(taskNumber);
        task.markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    public static void unMarkTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = tasks.get(taskNumber);
        task.markNotDone();
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    public static void todo(String input){
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void deadline(String input) {
        String[] parts = input.substring(8).split(" /by ");
        String description = parts[0].trim();
        String by = parts[1].trim();
        Task task = new Deadline(description, by);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void event(String input) {
        String[] parts = input.substring(6).split(" /from | /to");
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

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Flash");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String input;
        while(true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (input.startsWith("mark")) {
                markTask(input);
            } else if (input.startsWith("unmark")){
                unMarkTask(input);
            } else if (input.startsWith("todo")) {
                todo(input);
            } else if (input.startsWith("deadline")) {
                deadline(input);
            } else if (input.startsWith("event")) {
                event(input);
            }
        }
    }
}
