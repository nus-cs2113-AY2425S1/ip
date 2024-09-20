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
      try {
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
          handleMarkCommand(input);
        } else if (input.startsWith("unmark")) {
          handleUnmarkCommand(input);
        } else if (input.startsWith("delete")) {
          handleDeleteCommand(input);
        } else if (input.trim().isEmpty()) {
          throw new DukeException("Oops! Task description cannot be empty.");
        } else {
          handleAddCommand(input);
        }
      } catch (DukeException e) {
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
      } catch (Exception e) {
        System.out.println("Oopsy daisy! Something went wrong, check again.");
        System.out.println("____________________________________________________________");
      }
    }
 
    scanner.close();
  }

  // Methods for handling different commands

  private static void handleMarkCommand(String input) throws DukeException {
    try {
      int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
      if (taskIndex < 0 || taskIndex >= taskCount) {
        throw new DukeException("Invalid task number. Please provide a valid task number to mark.");
      }
      tasks[taskIndex].markAsDone();
      System.out.println(" Nice! I've marked this task as done:");
      System.out.println("   " + tasks[taskIndex]);
      saveTasksToFile();
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new DukeException("Please specify a valid task number after 'mark'.");
    }
  }

  private static void handleUnmarkCommand(String input) throws DukeException {
    try {
      int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
      if (taskIndex < 0 || taskIndex >= taskCount) {
        throw new DukeException("Invalid task number. Please provide a valid task number to unmark.");
      }
      tasks[taskIndex].markAsNotDone();
      System.out.println(" OK, I've marked this task as not done yet:");
      System.out.println("   " + tasks[taskIndex]);
      saveTasksToFile();
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new DukeException("Please specify a valid task number after 'unmark'.");
    }
  }

  private static void handleDeleteCommand(String input) throws DukeException {
    try {
      int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
      if (taskIndex < 0 || taskIndex >= taskCount) {
        throw new DukeException("Invalid task number. Please provide a valid task number to delete.");
      }
      System.out.println(" Noted. I've removed this task:");
      System.out.println("   " + tasks[taskIndex]);
      for (int i = taskIndex; i < taskCount - 1; i++) {
        tasks[i] = tasks[i + 1];
      }
      tasks[taskCount - 1] = null;
      taskCount--;
      saveTasksToFile();
      System.out.println(" Now you have " + taskCount + " tasks in the list.");
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new DukeException("Please specify a valid task number after 'delete'.");
    }
  }

  private static void handleAddCommand(String input) throws DukeException {
    if (input.trim().isEmpty()) {
      throw new DukeException("The description of a task cannot be empty.");
    }
    tasks[taskCount] = new Task(input);
    taskCount++;
    System.out.println("added: " + input);
    saveTasksToFile();
  }

  // Load and Save Tasks
  private static void loadTasksFromFile() {
    try {
      File file = new File(FILE_PATH);
      if (!file.exists()) {
        File dir = new File("./data");
        if (!dir.exists()) {
          dir.mkdirs();
        }
        file.createNewFile();
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
    }
  }

  private static void saveTasksToFile() {
    try {
      FileWriter writer = new FileWriter(FILE_PATH);
      for (int i = 0; i < taskCount; i++) {
        Task task = tasks[i];
        writer.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Error saving tasks to file: " + e.getMessage());
    }
  }
}