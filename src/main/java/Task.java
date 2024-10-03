import exception.InvalidCreateDeadlineException;
import exception.InvalidCreateEventException;
import exception.InvalidCreateTaskException;
import exception.InvalidCreateToDoException;

import java.util.ArrayList;

/**
 * Represents Tasks and contains possible operations done on Tasks.
 * Allows subtypes of tasks to inherit from.
 */

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The task's description.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task based on user input.
     *
     * @param userInput User input specifying the type of task (todo, event, or deadline).
     * @throws InvalidCreateTaskException If the input does not match any valid task type.
     * @throws InvalidCreateToDoException If creating a todo task encounters an issue.
     * @throws InvalidCreateDeadlineException If creating a deadline task encounters an issue.
     * @throws InvalidCreateEventException If creating an event task encounters an issue.
     */

    public static void createNewTask(String userInput) throws InvalidCreateTaskException,
            InvalidCreateToDoException, InvalidCreateDeadlineException, InvalidCreateEventException {
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit[0].equals("todo") && userInput.length() > 4) {
            ToDo.createNewToDo(userInput.substring(5));
        } else if (userInputSplit[0].equals("event") && userInput.length() > 5) {
            Event.createNewEvent(userInput.substring(6));
        } else if (userInputSplit[0].equals("deadline") && userInput.length() > 8) {
            Deadline.createNewDeadline(userInput.substring(9));
        } else {
            throw new InvalidCreateTaskException();
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskIndex Index of the task to be deleted.
     */

    public static void deleteTask(int taskIndex) {
        TaskList.tasks.get(taskIndex).printDelete();
        TaskList.tasks.remove(taskIndex);
    }

    /**
     * Returns the status icon for the task (X if done, space if not done).
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Prints a message indicating that the task has been marked as done.
     */
    public void printMark() {
        UI.printContent("Nice! You have done this task:\n\t" + this.toString());
    }

    /**
     * Prints a message indicating that the task has been unmarked.
     */
    public void printUnmark() {
        UI.printContent("I have unmarked this task:\n\t" + this.toString());
    }

    /**
     * Prints a message indicating that the task has been deleted.
     */
    public void printDelete() {
        UI.printContent("You have deleted this task:\n\t" + this.toString());
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.printMark();
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
        this.printUnmark();
    }

    /**
     * Returns a string representation of the task, including done status and the tasks' fields.
     * Will be overridden in inheriting classes.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns a string representation of the task for DataManager.
     *
     * @return The formatted string for saving.
     */
    public String toSaveString() {
        return this.getStatusIcon() + "//" + this.description;
    }
}
