package november.parser;

import november.tasks.Task;

import java.util.ArrayList;

import static november.messages.Messages.*;
import static november.tasklist.TaskList.*;
import static november.ui.Ui.*;

/**
 * The Parser class is responsible for interpreting user input commands
 * and triggering the appropriate actions on the task list.
 */
public class Parser {

    /**
     * Parses the user's input and calls the corresponding method based on the command.
     *
     * @param input    The input string entered by the user.
     * @param taskList The list of tasks to be manipulated based on the command.
     */
    public static void parse(String input, ArrayList<Task> taskList) {
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
            findTask(description, taskList); // Find a task by description
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
