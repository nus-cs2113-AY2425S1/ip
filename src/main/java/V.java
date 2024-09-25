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
        
        Storage storage = new Storage(SAVE_FILE_PATH);
        storage.loadSave();
        TaskList taskList = new TaskList(storage);
        Parser parser = new Parser(storage, taskList);

        boolean isOnline = true;

        Scanner input = new Scanner(System.in);
        String command;

        greet();
        
        while (isOnline) {
            try {
                command = input.nextLine();
                isOnline = parser.parse(command);
            } 
            // catch (NumberFormatException error) {
            //     printBlock("You need to input a valid integer for the task that you want to mark as done");
            // } 
            // catch (InvalidDeadlineException error) {
            //     printBlock("You did not enter a valid deadline." + 
            //             " Remember to add a \"/by\" before a valid deadline.");
            // }
        }
        printBlock("See Ya");
    }
}
