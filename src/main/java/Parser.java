import java.util.Arrays;

public class Parser {
    private Storage storage;
    private TaskList taskList;

    private String[] wordArray;
    private String description;
    private int position;
    
    private static final String LINE_SEPERATOR = "____________________________________________________________";
    
    // Print 2 line seperators between a block of text for cleaner CLI
    public void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }
    
    public boolean parse(String command) {
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
                taskList.markTask(position, false);
                return true;
            case "delete":
                position = Integer.parseInt(wordArray[1]);
                taskList.deleteTask(position);
                return true;
            case "todo":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addToDo(description, false);
                return true;
            case "deadline":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addDeadline(description, false);
                return true;
            case "event":
                description = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                taskList.addEvent(description, false);
                return true;
            default:
                System.out.println("Try again");
                return true;
            }
        } catch (NumberFormatException error) {
            printBlock("You need to input a valid integer for the task that you want to mark as done");
            return true;
        } catch (InvalidDeadlineException error) {
            printBlock("You did not enter a valid deadline." + 
                    " Remember to add a \"/by\" before a valid deadline.");
            return true;
        }
    }

    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    public Parser() {
    }

}
