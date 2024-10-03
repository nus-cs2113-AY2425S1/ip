package freedom.user;

import freedom.exceptions.InvalidCommand;
import freedom.tasks.TaskList;
import freedom.ui.Ui;

/**
 * Class to interpret user commands and execute the appropriate methods.
 */
public class Parser {
    private static Ui ui = new Ui();

    private static boolean isEnd;

    /**
     * Interprets commands from the user input and executes the appropriate methods.
     *
     * @param taskList task list for chatbot.
     * @param storage instance of Storage class
     * @param input full user input
     */
    public static void handleInput(TaskList taskList, Storage storage, String input) {
        final int COMMAND_INDEX = 0;
        String[] words = input.split(" ");

        try {
            switch (words[COMMAND_INDEX]) {
            case "bye":
                setEnd(true);
                return;
            case "list":
                taskList.printList(taskList.getTasks());
                return;
            case "mark":
            case "unmark":
            case "delete":
                taskList.editTask(words);
                storage.saveData(taskList);
                return;
            case "todo":
                taskList.addTask("todo", input);
                break;
            case "deadline":
                taskList.addTask("deadline", input);
                break;
            case "event":
                taskList.addTask("event", input);
                break;
            case "find":
                taskList.findTasks(input);
                break;
            default:
                throw new InvalidCommand();

            }
            storage.saveData(taskList);
        } catch (InvalidCommand e) {
            ui.printInvalidCommand();
        } catch (Exception e) {
            ui.printPlaceholder();
        }
    }

    public static boolean isEnd() {
        return isEnd;
    }

    public static void setEnd(boolean end) {
        isEnd = end;
    }
}
