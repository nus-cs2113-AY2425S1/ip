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

    public static void addTask(String input) {
        Task task = new Task(input);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
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
            } else {
                addTask(input);
            }
        }
    }
}
