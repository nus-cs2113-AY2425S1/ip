import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Flash {

    private static final String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void displayTasks() {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String input) {
        tasks[taskCount] = input;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
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
            } else {
                addTask(input);
            }
        }
    }
}
