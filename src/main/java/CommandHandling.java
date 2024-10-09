import customexceptions.DeadlineConstructorException;
import customexceptions.EventConstructorException;
import customexceptions.ToDoConstructorException;
import taskpackage.Deadline;
import taskpackage.Event;
import taskpackage.TaskList;
import taskpackage.ToDo;

public class CommandHandling {
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete ";
    public static final String MARK_COMMAND = "mark ";
    public static final String UNMARK_COMMAND = "unmark ";
    public static final String TODO_COMMAND = "todo ";
    public static final String DEADLINE_COMMAND = "deadline ";
    public static final String EVENT_COMMAND = "EVENT ";

    public static void deleteCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace("delete ", "")) - 1; // Parse the task index
            tasks.deleteTask(taskIndex); // Mark the task as done
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid index or format exceptions
            ui.porFavorMessage("DELETE EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            // Handle null task case
            ui.porFavorMessage("DELETE EXCEPTION: NULL TASK INDEX");
        }
    }

    public static void markCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace("mark ", "")) - 1; // Parse the task index
            tasks.mark(taskIndex); // Mark the task as done
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid index or format exceptions
            ui.porFavorMessage("MARK EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            // Handle null task case
            ui.porFavorMessage("MARK EXCEPTION: NULL TASK INDEX");
        }
    }

    public static void unmarkCommand(TaskList tasks, String line, UI ui) {
        try {
            int taskIndex = Integer.parseInt(line.replace("unmark ", "")) - 1; // Parse the task index
            tasks.unmark(taskIndex); // Unmark the task
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid index or format exceptions
            ui.porFavorMessage("UNMARK EXCEPTION: INVALID TASK INDEX");
        } catch (NullPointerException e) {
            // Handle null task case
            ui.porFavorMessage("UNMARK EXCEPTION: NULL TASK INDEX");
        }
    }

    public static void addTodoCommand(TaskList tasks, String line, UI ui) {
        try {
            new ToDo(line, tasks, true); // Create a new ToDo object
        } catch (ToDoConstructorException e) {
            // Handle custom ToDo exception
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }

    public static void addDeadlineCommand(TaskList tasks, String line, UI ui) {
        try {
            new Deadline(line, tasks, true); // Create a new Deadline object
        } catch (DeadlineConstructorException e) {
            // Handle custom Deadline exception
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }

    public static void addEventCommand(TaskList tasks, String line, UI ui) {
        try {
            new Event(line, tasks, true); // Create a new Event object
        } catch (EventConstructorException e) {
            // Handle custom Event exception
            ui.porFavorMessage(e.getMessage());
            tasks.deleteLatestTask();
        }
    }

}

