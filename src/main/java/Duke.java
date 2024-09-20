import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    // Print the welcome message
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Ruhi.");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");

    // Initialize a scanner to read user input and an array to store tasks
    Scanner scanner = new Scanner(System.in);
    Task[] tasks = new Task[100]; // Array to store up to 100 tasks
    int taskCount = 0; // Counter to track the number of tasks added

    while (true) {
      String input = scanner.nextLine();
      System.out.println("____________________________________________________________");

      if (input.equals("bye")) {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        break; // Exit the loop if the user types "bye"
      } else if (input.equals("list")) {
        // Display the list of tasks
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
          System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
      } else if (input.startsWith("mark")) {
        // Mark the task as done
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[taskIndex].markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[taskIndex]);
        System.out.println("____________________________________________________________");
      } else if (input.startsWith("unmark")) {
        // Mark the task as not done
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[taskIndex].markAsNotDone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[taskIndex]);
        System.out.println("____________________________________________________________");
      } else if (input.startsWith("delete")) {
        // Delete the task
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + tasks[taskIndex]);
        // Shift tasks to remove the deleted task
        for (int i = taskIndex; i < taskCount - 1; i++) {
          tasks[i] = tasks[i + 1];
        }
        tasks[taskCount - 1] = null; // Clear the last task
        taskCount--; // Decrease the task count
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
      } else {
        // Add the new task to the array and confirm addition
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
      }
    }

    // Close the scanner
    scanner.close();
  }
}