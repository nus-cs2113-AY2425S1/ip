import exception.EmptyDateFieldException;
import exception.EmptyDescriptionException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * Manages the list of tasks. This class provides functionality to
 * add tasks (events, todos, deadlines) and mark or delete tasks
 * in the task list.
 */
public class TaskList {

    /**
     * Adds an event task to the provided task list.
     * The event contains a description, start date, and end date.
     *
     * @param itemArrayList The list of tasks to add the event to.
     * @param line The user input containing the event description, start date, and end date.
     * @throws EmptyDescriptionException If the event description is missing.
     * @throws EmptyDateFieldException If the event start or end date is missing.
     */
    public static void addEvent(ArrayList<Task> itemArrayList, String line) {
        try {
            String eventDescription = Parser.extractEventDescription(line);
            String eventStartDate = Parser.extractEventStartDate(line);
            String eventEndDate = Parser.extractEventEndDate(line);
            Event newEvent = new Event(eventDescription, eventStartDate, eventEndDate);
            itemArrayList.add(newEvent);
            Ui.printAddedMessage(itemArrayList, newEvent);
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    /**
     * Adds a todo task to the provided task list.
     * The todo contains only a description.
     *
     * @param itemArrayList The list of tasks to add the todo to.
     * @param line The user input containing the todo description.
     * @throws EmptyDescriptionException If the todo description is missing.
     */
    public static void addTodo(ArrayList<Task> itemArrayList, String line) {
        try {
            String todoDescription = Parser.extractTodoDescription(line);
            Todo newTodo = new Todo(todoDescription);
            itemArrayList.add(newTodo);
            Ui.printAddedMessage(itemArrayList, newTodo);
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        }
    }

    /**
     * Adds a deadline task to the provided task list.
     * The deadline contains a description and a due date.
     *
     * @param itemArrayList The list of tasks to add the deadline to.
     * @param line The user input containing the deadline description and date.
     * @throws EmptyDescriptionException If the deadline description is missing.
     * @throws EmptyDateFieldException If the deadline date is missing.
     */
    public static void addDeadline(ArrayList<Task> itemArrayList, String line) {
        try {
            String deadlineDescription = Parser.extractDeadlineDescription(line);
            String deadlineDate = Parser.extractDeadlineDate(line);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            itemArrayList.add(newDeadline);
            Ui.printAddedMessage(itemArrayList, newDeadline);
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param itemArrayList The list of tasks.
     * @param itemNum The task number to be marked as done.
     */
    public static void markListItemAsDone(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.get(itemNum - 1).markAsDone();
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param itemArrayList The list of tasks.
     * @param itemNum The task number to be marked as not done.
     */
    public static void markListItemAsUnDone(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.get(itemNum - 1).markAsUnDone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param itemArrayList The list of tasks.
     * @param itemNum The task number to be deleted.
     */
    public static void deleteListItem(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.remove(itemNum - 1);
    }
}

