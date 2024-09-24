package Parser;

import Task.TaskList;
import TaskList.ListCommand;
import TaskList.MarkCommand;
import TaskList.UnmarkCommand;
import TaskList.ToDoCommand;
import TaskList.DeadlineCommand;
import TaskList.EventCommand;
import TaskList.DeleteCommand;

import java.util.Scanner;

public class Parser {
    private static final String SEPARATOR = "_".repeat(30);
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput, TaskList taskList) {
        if (userInput.startsWith("list")) {
            new ListCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("mark")) {
            new MarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("unmark")) {
            new UnmarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("todo")) {
            new ToDoCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("deadline")) {
            new DeadlineCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("event")) {
            new EventCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("delete")) {
            new DeleteCommand().execute(taskList, userInput);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid input, please try again.");
            System.out.println(SEPARATOR);
        }
    }
}
