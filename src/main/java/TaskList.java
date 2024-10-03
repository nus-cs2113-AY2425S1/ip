import exceptions.IllegalCommandException;
import exceptions.IncompleteCommandException;
import tasks.*;

import java.util.ArrayList;

/**
 * TaskList is a class that should contain all possible methods that the different
 * commands would call for.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;
    private static final int TODO_CHAR_COUNT = 4;
    private static final int DEADLINE_CHAR_COUNT = 8;
    private static final int EVENT_CHAR_COUNT = 5;

    public void listTask() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.printHorizontalLine();
    }

    /**
     * Changing their status by marking the task with respect to the taskNumber
     * specified
     *
     * @param taskNumber                is the index of the desired task shown on the list
     * @throws IllegalCommandException  when input number does not exist on the
     *                                  existing list
     */
    public void markTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.markAsDone();
            Ui.printMarkedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Changing their status by removing the mark of the task with respect to
     * the taskNumber specified
     *
     * @param taskNumber                is the index of the desired task shown on the list
     * @throws IllegalCommandException  when input number does not exist on the
     *                                  existing list
     */
    public void unmarkTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.unmark();
            Ui.printUnmarkedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Adding a task of type Todo into the list of tasks.
     * List is saved after its addition.
     *
     * @param line is the input containing information on the task
     *
     */
    public void addTodo(String line) {
        String description = line.substring(TODO_CHAR_COUNT + 1).trim();
        Task t =  new Todo(description);
        tasks.add(t);
        Ui.printAddedTaskMessage();
        Storage.saveTaskList(tasks);
    }

    /**
     * Adding a task of type Deadline into the list of tasks.
     * List is saved after its addition.
     *
     * @param line                          is the input containing information on the task
     * @throws IncompleteCommandException   when number of parameters needed does not
     *                                      match the amount given.
     *
     */
    public void addDeadline(String line) {
        try {
            String[] twoParts = line.substring(DEADLINE_CHAR_COUNT + 1).trim().split(" by ");
            if (twoParts.length != 2) {
                Ui.printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = twoParts[0];
            String by = twoParts[1];
            Task t = new Deadline(description, by);
            tasks.add(t);
            Ui.printAddedTaskMessage();
            Storage.saveTaskList(tasks);
        } catch (IncompleteCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Adding a task of type Event into the list of tasks.
     * List is saved after its addition.
     *
     * @param line                          is the input containing information on the task
     * @throws IncompleteCommandException   when number of parameters needed does not
     *                                      match the amount given.
     *
     */
    public void addEvent(String line) {
        try {
            String[] threeParts = line.substring(EVENT_CHAR_COUNT + 1).trim().split(" from | to ");
            if (threeParts.length != 3) {
                Ui.printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = threeParts[0];
            String from = threeParts[1];
            String to = threeParts[2];
            Task t = new Event(description, from, to);
            tasks.add(t);
            Ui.printAddedTaskMessage();
            Storage.saveTaskList(tasks);
        } catch (IncompleteCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Deleting a task from the list of tasks. Type does not matter.
     * List is saved after its addition.
     *
     * @param taskNumber                    is the index of the desired task shown on the list
     * @throws IncompleteCommandException   when number of parameters needed does not
     *                                      match the amount given.
     *
     */
    public void deleteTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            taskCount--;
            Ui.printDeletedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

}
