package dobby.parser;

import dobby.exceptions.MissingDescriptionException;
import dobby.exceptions.EmptyListException;
import dobby.exceptions.IllegalInputException;
import dobby.exceptions.TaskAlreadyMarkedException;
import dobby.exceptions.TaskAlreadyUnmarkedException;
import dobby.storage.Storage;
import dobby.tasklist.TaskList;
import dobby.ui.Ui;

public class Parser {

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

    private static boolean isValidAddTaskCommand(String line) {
        return line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event");
    }
}
