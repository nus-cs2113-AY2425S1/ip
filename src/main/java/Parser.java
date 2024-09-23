import exception.LeginIndexOutOfBoundsException;
import exception.LeginInvalidCommandException;

public class Parser {
    private Ui ui = new Ui();
    private void checkIndexValidity(TaskList tasklist, int index) throws LeginIndexOutOfBoundsException {
        if (index > tasklist.getTaskCount()) {
            throw new LeginIndexOutOfBoundsException();
        }
    }

    public boolean parseInput(TaskList taskList, String input) {
        String[] wordsOfInput = input.split(" ");
        String command = wordsOfInput[0];
        return selectCommand(taskList, command, wordsOfInput, input);
    }

    boolean selectCommand(TaskList taskList, String command, String[] words, String input) {
        int indexOfTaskToMark;
        try {
            switch (command) {
            case "bye":
                return true;
            case "list":
                taskList.list();
                break;
            case "mark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
                checkIndexValidity(taskList, indexOfTaskToMark);
                taskList.markTask(indexOfTaskToMark);
                break;
            case "unmark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
                checkIndexValidity(taskList, indexOfTaskToMark);
                taskList.unmarkTask(indexOfTaskToMark);
                break;
            case "todo":
                taskList.addTodo(input);
                break;
            case "deadline":
                taskList.addDeadline(input);
                break;
            case "event":
                taskList.addEvent(input);
                break;
            case "delete":
                int indexOfTaskToDelete = Integer.parseInt(words[1]);
                checkIndexValidity(taskList, indexOfTaskToDelete);
                taskList.deleteTask(indexOfTaskToDelete);
                break;
            default:
                throw new LeginInvalidCommandException();
            }
        } catch (LeginInvalidCommandException | LeginIndexOutOfBoundsException e) {
            ui.printExceptionMessage(e);
        }
        return false;
    }
}
