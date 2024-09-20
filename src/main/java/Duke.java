import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
  private static final String FILE_PATH = "./data/duke.txt";
  private static Task[] tasks = new Task[100];
  private static int taskCount = 0;

  public static void main(String[] args) {
    // Print the welcome message
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Ruhi.");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");

    // Load existing tasks from file
    loadTasksFromFile();

    // Initialize a scanner to read user input
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.println("____________________________________________________________");

      if (input.equals("bye")) {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        break;
      } else if (input.equals("list")) {
        // Display the list of tasks
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
        saveTasksToFile(); // Save tasks after marking as done
        System.out.println("____________________________________________________________");
      } else if (input.startsWith("unmark")) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[taskIndex].markAsNotDone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[taskIndex]);
        saveTasksToFile(); // Save tasks after unmarking
        System.out.println("____________________________________________________________");
      } else if (input.startsWith("delete")) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + tasks[taskIndex]);
        for (int i = taskIndex; i < taskCount - 1; i++) {
          tasks[i] = tasks[i + 1];
        }
        tasks[taskCount - 1] = null; // Clear the last task
        taskCount--;
        saveTasksToFile(); // Save tasks after deletion
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
      } else {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("added: " + input);
        saveTasksToFile(); // Save tasks after adding a new one
        System.out.println("____________________________________________________________");
      }
    }

    scanner.close();
  }

  // Method to load tasks from the file
  private static void loadTasksFromFile() {
    try {
      File file = new File(FILE_PATH);
      if (!file.exists()) {
        File dir = new File("./data");
        if (!dir.exists()) {
          dir.mkdirs(); // Create the directory if it doesn't exist
        }
        file.createNewFile(); // Create the file if it doesn't exist
      }

      Scanner fileScanner = new Scanner(file);
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = new Task(description);
        if (isDone) {
          task.markAsDone();
        }
        tasks[taskCount] = task;
        taskCount++;
      }
      fileScanner.close();
    } catch (IOException e) {
      System.out.println("Error loading tasks from file: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Error: Corrupted file, starting with an empty list.");
      taskCount = 0;
    }
  }

  // Method to save tasks to the file
  private static void saveTasksToFile() {
    try {
      FileWriter writer = new FileWriter(FILE_PATH);
      for (int i = 0; i < taskCount; i++) {
        Task task = tasks[i];
        String taskType = "T"; // Default task type
        writer.write(taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.description + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Error saving tasks to file: " + e.getMessage());
    }
  }
}