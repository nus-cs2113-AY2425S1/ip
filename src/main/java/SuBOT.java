import exception.InvalidArgumentException;
import exception.InvalidCommandException;
import exception.InvalidFileException;
import storage.Storage;
import task.TaskList;
import util.UI;

public class SuBOT {
    private Storage st = null;
    private TaskList taskList = null;


    public SuBOT() {
        this(null);
    }

    public SuBOT(String filePath) {
        try {
            if (filePath != null) {
                st = Storage.create(filePath);
            } else {
                st = Storage.create();
            }
            taskList = st.load();
        } catch (InvalidFileException e) {
            taskList = new TaskList();
            UI.showError("Invalid save file: ", e);
        } catch (Exception e) {
            UI.showError("Unexpected exception: ", e);
        }
    }

    public void run() {
        UI.greeting();
        while (TaskList.isRunning) {
            String command = UI.getCommand();
            System.out.println(UI.SEPARATOR);
            process(command);
            System.out.println(UI.SEPARATOR);
        }
        st.save(taskList);
        UI.goodbye();
    }

    public static void main(String[] args) {
        new SuBOT().run();
    }

    /**
     * Processes user's input command.
     * This method takes the first word in <code>command</code>
     * to determine the action needed.
     * The remaining (if available) will be passed to seperated methods
     * for further processing.
     * @param command Input command
     */
    public void process(String command) {
        //Split `command` into `action` and `argString`
        String[] argv = command.split(" ", 2);
        //argv will have at least length of 1 because that's how split() works
        String action = argv[0];

        String argString;
        int argc = argv.length;
        if (argc == 2) {
            argString = argv[1]; //For commands with 1 or more options
        } else {
            argString = ""; //For 0-option commands (E.g. list)
        }
        try {
            switch (action) {
            case "bye":
                TaskList.isRunning = false; //Halt signal for parent class
                break;
            case "list":
                taskList.listTasks();
                break;
            case "mark":
            case "unmark":
                taskList.markUnmarkTask(action, argString);
                break;
            case "deadline":
                taskList.addDeadline(argString);
                break;
            case "todo":
                taskList.addTodo(argString);
                break;
            case "event":
                taskList.addEvent(argString);
                break;
            case "delete":
                taskList.deleteTask(argString);
                break;
            case "find":
                taskList.findTask(argString);
                break;
            default:
                throw new InvalidCommandException(TaskList.UsageString.DEFAULT);
            }
        } catch (InvalidArgumentException e) {
            UI.showError("Invalid argument(s): ", e);
        } catch (InvalidCommandException e) {
            UI.showError("Invalid command: " + action + "\n", e);
        } catch (Exception e) {
            UI.showError("Unexpected exception: ", e);
            e.printStackTrace();
        }
    }

}
