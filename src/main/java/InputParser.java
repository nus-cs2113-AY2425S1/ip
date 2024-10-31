import commands.*;
import tasklist.TaskList;

import java.util.Arrays;

public class InputParser {

    private String getCommand(String line) {
        if (!line.contains(" ")){
            return line;
        }
        return line.substring(0, line.indexOf(" "));
    }

    /**
     * Performs the appropriate action for a given user input and a task list
     * @param line One line of user input
     * @param taskList The task list to perform command on
     */
    public void handleInput(String line, TaskList taskList) {
        line = line.trim();
        if (line.isEmpty()){
            return;
        }

        String command = getCommand(line);

        switch (command) {
        case "list":
            taskList.printTaskList();
            break;
        case "bye":
            break;
        case "help":
            new HelpCommand().execute();
            break;
        case "mark":
            new MarkCommand().execute(taskList,line);
            break;
        case "unmark":
            new UnmarkCommand().execute(taskList,line);
            break;
        case "delete":
            new DeleteCommand().execute(taskList,line);
            break;
        case "find":
            new FindCommand().execute(taskList,line);
            break;
        case "todo":
            new TodoCommand().execute(taskList,line);
            break;
        case "deadline":
            new DeadlineCommand().execute(taskList,line);
            break;
        case "event":
            new EventCommand().execute(taskList,line);
            break;
        default:
            System.out.println("Please input a valid command. Type help to receive a list of commands.");
            break;
        }
    }

}
