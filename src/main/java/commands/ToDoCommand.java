package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * Represents a command that adds a new Todo task to the list.
 */
public class ToDoCommand extends Command {

    String userInput;

    /**
     * Constructs a ToDoCommand instance with the specified user input.
     *
     * @param userInput the user input for creating a Todo task
     */
    public ToDoCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the command to add a new Todo task to the list.
     *
     * The expected format for user input is
     * 'todo <task>'.
     *
     * @param tasks the list of tasks to which the Todo will be added
     * @return a success message with details of the added task
     * @throws XiaoMeException if the user input format is invalid or the task description is empty
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            // user is creating a new  Task.Todo
            String string = userInput.replace("todo", "").trim();

            if (string.isEmpty()) {
                throw new IllegalArgumentException();
            }

            tasks.add(new Todo(string)); // add task to storage

            Storage.saveFile(tasks);

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.get(tasks.size() - 1) + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create a todo:
                    \tUse 'todo <task>'""");
        }
    }

}
