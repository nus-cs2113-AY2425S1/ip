import exceptions.InvalidCommandException;

public class Parser {

    /**
     * Parse the input into substrings where it selects the case by the command
     *
     * @param input                     the input users enter
     * @param tasklist                  the list of methods where specific ones are called
     *                                  according to its command
     * @throws InvalidCommandException  if input does not have the specifc commands
     *                                  in the cases
     */
    public static boolean parse(String input, TaskList tasklist) {
        String[] words = input.split(" ");
        String command = words[0];
        try {
            Ui.printHorizontalLine();
            switch (command) {
            case "bye":
                return true;
            case "list":
                tasklist.listTask();
                break;
            case "mark":
                int taskNumber = Integer.parseInt(words[1]);
                tasklist.markTask(taskNumber);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(words[1]);
                tasklist.unmarkTask(taskNumber);
                break;
            case "todo":
                tasklist.addTodo(input);
                break;
            case "deadline":
                tasklist.addDeadline(input);
                break;
            case "event":
                tasklist.addEvent(input);
                break;
            case "delete":
                taskNumber = Integer.parseInt(words[1]);
                tasklist.deleteTask(taskNumber);
                break;
            case "find":
                tasklist.findTask(input);
                break;
            case "help":
                Ui.askHelp();
                break;
            default:
                Ui.printHorizontalLine();
                throw new InvalidCommandException("Did you misspell or miss out something? ");
            }
        } catch (InvalidCommandException e) {
            Ui.printExceptionMessage(e);
        }
        return false;
    }

}
