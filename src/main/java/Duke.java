import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm V.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Array to store up to 100 tasks
        int taskCount = 0; // Counter to track the number of tasks added

        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo")) {
                String taskDescription = input.substring(5);
                tasks[taskCount] = new Task(taskDescription, "T");
                taskCount++;
                System.out.println("added: " + taskDescription);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline")) {
                String taskDescription = input.substring(9);
                tasks[taskCount] = new Task(taskDescription, "D");
                taskCount++;
                System.out.println("added: " + taskDescription);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event")) {
                String taskDescription = input.substring(6);
                tasks[taskCount] = new Task(taskDescription, "E");
                taskCount++;
                System.out.println("added: " + taskDescription);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
