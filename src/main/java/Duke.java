/**
 * Represents the main application for the task management chatbot called Ruhi. This class handles
 * the main flow of Ruhi, including reading user commands and processing them accordingly.
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Duke {
  //class attributes
  /**
   * Inititalises Ruhi with specified file path for load and store tasks
   */
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Duke(String filePath) {
    //constructor implementation
    /**
     filePath is the path of files where tasks are stored.
     */
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = storage.load();
    } catch (IOException e) {
      ui.showLoadingError();
      tasks = new TaskList();
    }
  }

  public void run() {
    //method implementation
    /**
     * Handles the parsed user commands and executes the appropriate
     * actions based on the command type.
     */
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine();
        String[] parsedCommand = Parser.parse(fullCommand);
        handleCommand(parsedCommand);
        if (parsedCommand[0].equals("bye")) {
          isExit = true;
          System.out.println(" Bye. Hope to see you again soon!");
        }
      } catch (Exception e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }

  private void handleCommand(String[] parsedCommand) {
    //method implementation
    /**
     *  Handles the parsed user commands and executes the appropriate
     * actions based on the command type.
     */
    switch (parsedCommand[0]) {
      case "list":
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
          System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        break;
      case "mark":
        int markIndex = Integer.parseInt(parsedCommand[1]) - 1;
        tasks.getTask(markIndex).markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.getTask(markIndex));
        break;
      case "unmark":
        int unmarkIndex = Integer.parseInt(parsedCommand[1]) - 1;
        tasks.getTask(unmarkIndex).markAsNotDone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.getTask(unmarkIndex));
        break;
      case "delete":
        int deleteIndex = Integer.parseInt(parsedCommand[1]) - 1;
        tasks.deleteTask(deleteIndex);
        System.out.println(" Noted. I've removed this task:");
        break;
      case "add":
        tasks.addTask(new Task(parsedCommand[1]));
        System.out.println(" Added: " + parsedCommand[1]);
        break;
      case "deadline":
        try {
          String description = parsedCommand[1];
          String dateTimeStr = parsedCommand[3];
          LocalDateTime deadline = parseDateTime(dateTimeStr);
          tasks.addTask(new Task(description, deadline));
          System.out.println(" Added: " + description + " (by: " + deadline + ")");
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println(" Please provide a description and a date.");

        } catch (DateTimeParseException e) {
          System.out.println(" Please provide a valid date format (yyyy-mm-dd HH:mm).");
        }
        break;
      case "find":
        String keyword = parsedCommand[1]; // Get the search keyword
        List<Task> foundTasks = tasks.findTasks(keyword); // Find tasks containing the keyword
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
          System.out.println((i + 1) + "." + foundTasks.get(i));
        }
        break;
      default:
        throw new RuntimeException("Unknown command.");
    }
  }
  
  private LocalDateTime parseDateTime(String dateTimeStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(dateTimeStr, formatter);
  }

  public static void main(String[] args) {
    new Duke("data/duke.txt").run();
  }
}