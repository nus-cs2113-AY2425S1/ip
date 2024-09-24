import java.util.Scanner;
import java.util.Arrays;

public class V {

    private static final String LINE_SEPERATOR = "____________________________________________________________";
    private static final String SAVE_FILE_PATH = "./V.txt";

    // Print 2 line seperators between a block of text for cleaner CLI
    public static void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    public static void greet() {
        final String LOGO = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(LOGO);
        printBlock("Hi I'm V\nWhat can I do for you?");
    }

    public static void main(String[] args) {
        
        Storage storage = new Storage();
        storage.loadSave(SAVE_FILE_PATH);
        TaskList taskList = new TaskList(storage);

        boolean isOnline = true;
        
        String description;
        String line;
        String[] lineArr;
        int position;
        Scanner input = new Scanner(System.in);

        greet();
        
        while (isOnline) {
            try {
                line = input.nextLine();
                lineArr = line.trim().split(" ");
                switch (lineArr[0].toLowerCase()) {
                case "bye":
                    input.close();
                    isOnline = false;
                    storage.createSave(taskList, SAVE_FILE_PATH);
                    // taskList.saveTasks(SAVE_FILE_PATH);
                    break;
                case "list":
                    taskList.displayList();
                    break;
                case "mark":
                    position = Integer.parseInt(lineArr[1]);
                    taskList.markTask(position, false);
                    break;
                case "delete":
                    position = Integer.parseInt(lineArr[1]);
                    taskList.deleteTask(position);
                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    taskList.addToDo(description, false);
                    break;
                case "deadline":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    taskList.addDeadline(description, false);
                    break;
                case "event":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    taskList.addEvent(description, false);
                    break;
                default:
                    System.out.println("Try again");
                    break;
                }
            } catch (NumberFormatException error) {
                printBlock("You need to input a valid integer for the task that you want to mark as done");
            } catch (InvalidDeadlineException error) {
                printBlock("You did not enter a valid deadline." + 
                        " Remember to add a \"/by\" before a valid deadline.");
            }
        }
        printBlock("See Ya");
    }
}
