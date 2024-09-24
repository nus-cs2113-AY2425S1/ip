package CodyChen.Command;
import CodyChen.*;
import CodyChen.Task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * The AddDeadlineCommand class represents a command to add a deadline task
 * to a task list. It processes user input to extract the task name and the
 * deadline date, and executes the command to add the task.
 */
public class AddDeadlineCommand extends Command {
    private String taskName;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with the specified user input.
     * The task name and deadline date are extracted from the user input string.
     *
     * @param userInput The input string from the user, expected to contain "deadline ".
     */

    public AddDeadlineCommand(String userInput) {
        taskName = userInput.substring("deadline ".length(), userInput.indexOf("/by "));
        by = userInput.substring(userInput.indexOf("/by ") + "/by ".length());
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * It creates a new Deadline object using the specified date, adds it to the task list,
     * displays a message to the user, and saves the updated task list to storage.
     * If the date is invalid, it creates the task with the original string.
     *
     * @param tasks The TaskList object to which the task will be added.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the task list.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate date = LocalDate.parse(by, formatter);
            Task task = new Deadline(taskName, date);
            tasks.addTask(task);
        } catch (DateTimeParseException e) {
            Task task = new Deadline(taskName, by);
            tasks.addTask(task);
        }
        ui.showDeadlineAdded(tasks);
        storage.save(tasks);
    }
}
