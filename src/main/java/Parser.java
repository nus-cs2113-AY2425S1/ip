public class Parser {
    private Fenix fenix;

    public Parser(Fenix fenix) {
        this.fenix = fenix;
    }

    public void processUserInput(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        String commandType = words[0];
        String commandInfo = (words.length > 1) ? words[1] : "";

        switch (commandType) {
        case "bye":
            fenix.handleBye();
            break;
        case "list":
            fenix.handleList();
            break;
        case "mark":
            fenix.handleMark(commandInfo);
            break;
        case "unmark":
            fenix.handleUnmark(commandInfo);
            break;
        case "todo":
        case "deadline":
        case "event":
            fenix.handleAddTask(commandType, commandInfo);
            break;
        case "delete":
            fenix.handleDelete(commandInfo);
            break;
        default:
            fenix.handleInvalidCommand();
        }
    }

    public Task returnTaskObject(String type, String information) {
        switch (type) {
        case "todo":
            return new Todo(information);
        case "deadline":
            try {
                return new Deadline(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        case "event":
            try {
                return new Event(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        default:
            return null;
        }
    }

    public Task returnTaskObject(String taskType, String taskStatus, String taskInfo) {
        boolean isDone = (taskStatus.equals("X"));
        return switch (taskType) {
            case "T" -> new Todo(isDone, taskInfo);
            case "D" -> new Deadline(isDone, taskInfo);
            case "E" -> new Event(isDone, taskInfo);
            default -> null;
        };
    }

    public boolean isValidTaskNumber(String taskNumber) {
        try {
            int index = parseInteger(taskNumber);
            return index > 0 && index <= fenix.getSize();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int parseInteger(String inputInteger) {
        return Integer.parseInt(inputInteger);
    }
}
