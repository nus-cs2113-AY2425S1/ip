import exceptions.InvalidCommandException;

public class Parser {

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
