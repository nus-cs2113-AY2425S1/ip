import java.io.IOException;
import java.util.Scanner;

public class Duke {
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Duke(String filePath) {
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
      default:
        throw new RuntimeException("Unknown command.");
    }
  }

  public static void main(String[] args) {
    new Duke("data/duke.txt").run();
  }
}