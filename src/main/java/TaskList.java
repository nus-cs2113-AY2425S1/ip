import exception.FlashException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks and provides methods to add, delete, and modify tasks.
 */
public class TaskList {
    public List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param input the user's input for the deadline
     * @throws FlashException if the input is invalid or missing details
     */
    public void addDeadline(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseDeadline(input);
            String description = parsedInput[0];
            String by = parsedInput[1];
            Task task = new Deadline(description, by);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param input the user's input for the event
     * @throws FlashException if the input is invalid or missing details
     */
    public void addEvent(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseEvent(input);
            String description = parsedInput[0];
            String from = parsedInput[1];
            String to = parsedInput[2];
            Task task = new Event(description, from, to);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    /**
     * Deletes a task from the list by its number.
     *
     * @param input the user's input for the task number
     * @throws FlashException if the task number is invalid
     */
    public void deleteTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            UI.displayTaskDeleted(tasks, task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FlashException("Uh-oh! Task number is needed for deletion. Enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param input the user's input for the todo
     * @throws FlashException if the todo description is empty
     */
    public void addTodo(String input) throws FlashException {
        if (input.length() <= 5) {
            throw new FlashException("Uh-oh! Description for Todo Needed!! Cannot be left empty.");
        }

        String description = Parser.parseTodo(input);
        if (description.isEmpty()) {
            throw new FlashException("Uh-oh! Description for task.ToDo Needed!! Cannot be left empty.");
        }

        Task task = new ToDo(description);
        tasks.add(task);
        UI.displayTaskAdded(tasks, task);
    }

    /**
     * Marks a task as not done.
     *
     * @param input the user's input for the task number
     * @throws FlashException if the task number is invalid
     */
    public void unMarkTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = this.tasks.get(taskNumber);
            task.markNotDone();
            UI.displayTaskUnmarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input the user's input for the task number
     * @throws FlashException if the task number is invalid
     */
    public void markTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = this.tasks.get(taskNumber);
            task.markDone();
            UI.displayTaskMarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Lists all tasks that match a keyword.
     *
     * @param input the user's input for the search keyword
     * @throws FlashException if the input is invalid
     */
    public void listMatchedTasks(String input) throws FlashException {
        try {
            String keyword = Parser.parseKeyword(input);
            List<Task> matchedTasks = new ArrayList<>();
            for (Task task : this.tasks) {
                if (task.getDescription().contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
            UI.displayMatchedTasks(matchedTasks);
        } catch (Exception e) {
            throw new FlashException("Invalid Keyword");
        }
    }
}
