package CommandHandler;

import TaskList.TaskList;
import Ui.Ui;
import constants.Utils;
import exceptions.*;

public class CommandHandler {

    private final TaskList taskList;

    public CommandHandler() {
        taskList = new TaskList();
    }

    public void handleCommand(String input, String command, String[] splitInputs) throws IllegalEmptyException, IllegalCommandException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {
        if (command.equals(Utils.LIST)) {
            taskList.printList();
        } else if (command.equals(Utils.MARK)) {
            taskList.markItem(splitInputs);
        } else if (command.equals(Utils.UNMARK)) {
            taskList.unmarkItem(splitInputs);
        } else if (command.equals(Utils.TODO)) {
            taskList.addTodo(input);
        } else if (command.equals(Utils.DEADLINE)) {
            taskList.addDeadline(input);
        } else if (command.equals(Utils.EVENT)) {
            taskList.addEvent(input);
        } else if (command.equals(Utils.DELETE)) {
            taskList.deleteItem(splitInputs);
        } else {
            throw new IllegalCommandException("Please enter a valid command");
        }
    }
}
