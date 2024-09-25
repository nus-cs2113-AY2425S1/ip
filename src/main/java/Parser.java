import java.util.Arrays;

/**
 * Represents a parser that parses user input to to give the correct command.
 */
public class Parser {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private String[] wordArray;
    private String description;
    private int position;
    
    /**
     * Execute the function depending on the command and then returns the status of V.
     * Return false if command "bye" was used, else returns true.
     * @param command command from user
     * @param isFromSaveFile true if command is read from a save file, else false
     * @return status of V after a command
     */
    public boolean parse(String command, boolean isFromSaveFile) {
        try {
            wordArray = command.trim().split(" ");
            switch (wordArray[0].toLowerCase()) {
            case "bye":
                storage.createSave(taskList);
                return false;
            case "list":
                taskList.displayList();
                return true;
            case "mark":
                position = Integer.parseInt(wordArray[1]);
                taskList.markTask(position, isFromSaveFile);
                return true;
            case "delete":
                position = Integer.parseInt(wordArray[1]);
                taskList.deleteTask(position);
                return true;
            case "todo":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addToDo(description, isFromSaveFile);
                return true;
            case "deadline":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addDeadline(description, isFromSaveFile);
                return true;
            case "event":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addEvent(description, isFromSaveFile);
                return true;
            case "find":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.find(description);
                return true;
            default:
                System.out.println("Try again");
                return true;
            }
        } catch (NumberFormatException error) {
            this.ui.printBlock("You need to input a valid integer for the task that you want to mark as done");
            return true;
        } catch (InvalidDeadlineException error) {
            this.ui.printBlock("You did not enter a valid deadline." + 
                    " Remember to add a \"/by\" before a valid deadline.");
            return true;
        }
    }

    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public Parser() {
    }

}
