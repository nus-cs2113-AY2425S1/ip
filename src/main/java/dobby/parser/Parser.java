package dobby.parser;

import dobby.exceptions.MissingDescriptionException;
import dobby.exceptions.EmptyListException;
import dobby.exceptions.IllegalInputException;
import dobby.exceptions.TaskAlreadyMarkedException;
import dobby.exceptions.TaskAlreadyUnmarkedException;
import dobby.storage.Storage;
import dobby.tasklist.TaskList;
import dobby.ui.Ui;

/**
 * The Parser class is responsible for interpreting and processing user commands.
 * It directs commands to the appropriate methods in the TaskList and Ui classes.
 */
public class Parser {

    /**
     * Processes the user's input command and invokes the corresponding methods in TaskList or Ui.
     *
     * @param line The input command entered by the user.
     * @param taskList The list of tasks managed in the session.
     * @param ui The UI object responsible for interacting with the user.
     * @param storage The storage object for saving and loading tasks.
     * @throws MissingDescriptionException If a task description is missing.
     * @throws IllegalInputException If the input command is not recognized.
     * @throws TaskAlreadyMarkedException If the task is already marked as done.
     * @throws TaskAlreadyUnmarkedException If the task is already marked as incomplete.
     * @throws EmptyListException If an operation is attempted on an empty task list.
     */
    public static void processCommand (String line, TaskList taskList, Ui ui, Storage storage)
            throws MissingDescriptionException, IllegalInputException, TaskAlreadyMarkedException,
                    TaskAlreadyUnmarkedException, EmptyListException {

        if (line.equals("list")) {
            ui.printList(taskList);
        } else if (line.startsWith("find ")){
            taskList.findTasks(line, ui);
        } else if (line.startsWith("mark ")) {
            taskList.markTaskAsDone(line, ui, storage);
        } else if (line.startsWith("unmark ")) {
            taskList.unmarkTaskAsDone(line, ui, storage);
        } else if (isValidAddTaskCommand(line)) {
            taskList.addTaskFromCommand(line, ui, storage);
        } else if (line.startsWith("delete ")) {
            taskList.deleteTask(line, ui);
        } else {
            throw new IllegalInputException();
        }
    }

    /**
     * Validates if the given input command is a valid task addition command (e.g., todo, deadline, event).
     *
     * @param line The input command.
     * @return true if the command is valid for adding a task; false otherwise.
     */
    private static boolean isValidAddTaskCommand(String line) {
        return line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event");
    }
}
