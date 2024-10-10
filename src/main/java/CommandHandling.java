import customexceptions.DeadlineConstructorException;
import customexceptions.EventConstructorException;
import customexceptions.ToDoConstructorException;
import taskpackage.Deadline;
import taskpackage.Event;
import taskpackage.TaskList;
import taskpackage.ToDo;

/**
 * Handles various user commands related to tasks such as adding, deleting, finding, and marking tasks.
 */
public class CommandHandling {
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete ";
    public static final String FIND_COMMAND = "find ";
    public static final String MARK_COMMAND = "mark ";
    public static final String UNMARK_COMMAND = "unmark ";
    public static final String TODO_COMMAND = "todo ";
    public static final String DEADLINE_COMMAND = "deadline ";
    public static final String EVENT_COMMAND = "event ";

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param tasks The task list from which to delete the task.
     * @param line  The user input line.
     * @param ui    The user interface to display messages.
     */
    public static void deleteCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace(DELETE_COMMAND, "")) - 1; // Parse the task index
            tasks.deleteTask(taskIndex); // Delete the task
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.porFavorMessage("DELETE EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            ui.porFavorMessage("DELETE EXCEPTION: NULL TASK INDEX");
        }
    }

    /**
     * Finds tasks in the task list that match the search string.
     *
     * @param tasks The task list to search in.
     * @param line  The user input line containing the search string.
     * @param ui    The user interface to display messages.
     */
    public static void findCommand(TaskList tasks, String line, UI ui) {
        String findString = line.replace(FIND_COMMAND, "");
        if (findString.isEmpty()) {
            ui.porFavorMessage("FIND EXCEPTION: INVALID TASK INDEX");
            return;
        }
        tasks.findTasksList(findString);
    }

    /**
     * Marks a task as done in the task list based on user input.
     *
     * @param tasks The task list containing the task to be marked.
     * @param line  The user input line specifying the task index.
     * @param ui    The user interface to display messages.
     */
    public static void markCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace(MARK_COMMAND, "")) - 1; // Parse the task index
            tasks.mark(taskIndex); // Mark the task as done
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.porFavorMessage("MARK EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            ui.porFavorMessage("MARK EXCEPTION: NULL TASK INDEX");
        }
    }

    /**
     * Unmarks a task as not done in the task list based on user input.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param line  The user input line specifying the task index.
     * @param ui    The user interface to display messages.
     */
    public static void unmarkCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace(UNMARK_COMMAND, "")) - 1; // Parse the task index
            tasks.unmark(taskIndex); // Unmark the task
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.porFavorMessage("UNMARK EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            ui.porFavorMessage("UNMARK EXCEPTION: NULL TASK INDEX");
        }
    }

    /**
     * Adds a new to-do task to the task list based on user input.
     *
     * @param tasks The task list to add the to-do task to.
     * @param line  The user input line containing the to-do details.
     * @param ui    The user interface to display messages.
     */
    public static void addTodoCommand(TaskList tasks, String line, UI ui) {
        try {
            new ToDo(line.replace(TODO_COMMAND, ""), tasks, true); // Create a new ToDo object
        } catch (ToDoConstructorException e) {
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }

    /**
     * Adds a new deadline task to the task list based on user input.
     *
     * @param tasks The task list to add the deadline task to.
     * @param line  The user input line containing the deadline details.
     * @param ui    The user interface to display messages.
     */
    public static void addDeadlineCommand(TaskList tasks, String line, UI ui) {
        try {
            new Deadline(line.replace(DEADLINE_COMMAND, ""), tasks, true); // Create a new Deadline object
        } catch (DeadlineConstructorException e) {
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }

    /**
     * Adds a new event task to the task list based on user input.
     *
     * @param tasks The task list to add the event task to.
     * @param line  The user input line containing the event details.
     * @param ui    The user interface to display messages.
     */
    public static void addEventCommand(TaskList tasks, String line, UI ui) {
        try {
            new Event(line.replace(EVENT_COMMAND, ""), tasks, true); // Create a new Event object
        } catch (EventConstructorException e) {
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }
}
