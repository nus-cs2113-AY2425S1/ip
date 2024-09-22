package november.parser;

import november.tasks.Task;

import java.util.ArrayList;

import static november.messages.Messages.DEADLINE_COMMAND;
import static november.messages.Messages.DELETE_COMMAND;
import static november.messages.Messages.EVENT_COMMAND;
import static november.messages.Messages.FIND_COMMAND;
import static november.messages.Messages.LIST_COMMAND;
import static november.messages.Messages.MARK_COMMAND;
import static november.messages.Messages.TODO_COMMAND;
import static november.messages.Messages.UNMARK_COMMAND;
import static november.tasklist.TaskList.addNewDeadline;
import static november.tasklist.TaskList.addNewEvent;
import static november.tasklist.TaskList.addNewTodo;
import static november.tasklist.TaskList.deleteTask;
import static november.tasklist.TaskList.findTask;
import static november.tasklist.TaskList.markTask;
import static november.tasklist.TaskList.unmarkTask;
import static november.ui.Ui.printTaskList;
import static november.ui.Ui.printUnrecognizedInputMessage;

public class Parser {
    public static void parse (String input, ArrayList<Task> taskList){
        String[] sentence = {input, input};
        String command = input;
        String description = "";

        // Split the input into command and description if applicable
        if (input.contains(" ")) {
            sentence = input.split(" ", 2);
            command = sentence[0];
            description = sentence[1];
        }

        switch (command) {
            case MARK_COMMAND:
                markTask(description, taskList, true); // Mark a task as complete
                break;
            case UNMARK_COMMAND:
                unmarkTask(description, taskList); // Unmark a task as incomplete
                break;
            case LIST_COMMAND:
                printTaskList(taskList); // Print the list of tasks
                break;
            case TODO_COMMAND:
                addNewTodo(description, taskList); // Add a new TODO task
                break;
            case DEADLINE_COMMAND:
                addNewDeadline(description, taskList); // Add a new DEADLINE task
                break;
            case EVENT_COMMAND:
                addNewEvent(description, taskList); // Add a new EVENT task
                break;
            case FIND_COMMAND:
                findTask(description, taskList);
                break;
            case DELETE_COMMAND:
                deleteTask(sentence, taskList); // Delete a task
                break;
            default:
                printUnrecognizedInputMessage(); // Response to unrecognized inputs
                break;
        }

    }
}
