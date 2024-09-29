import java.util.Scanner;

/**
 * The UI class handles all input and output operations.
 * It provides methods to read commands from the user, display lines,
 * welcome and exit messages, error messages, and a list of available commands.
 */
public class UI {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads and returns the next line of input from the user.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_____________________________________________________________________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm QUAG!");
        System.out.println("This is your current list of tasks! quag");
        showLine();
    }

    public void showExit() {
        showLine();
        System.out.println("See you soon! quag quag");
        showLine();
    }

    public void notNumberEntered() {
        System.out.println("That's not a quaggin number!!");
    }

    public void noNumberEntered() {
        System.out.println("There's no quaggin number!!");
    }

    public void invalidFormat() {

        System.out.println("That's not a valid quaggin format!!");
        showLine();
        showCommandList();
    }

    public void alreadyMarked() {
        System.out.println("quag! you're done with this task :");
    }

    public void alreadyUnmarked() {
        System.out.println("quag! you ain't even done w this yet:");
    }

    public void showCommandList() {
        System.out.println("list of all quaggin commands:");
        showLine();
        System.out.println("list: lists out all your tasks");
        System.out.println("mark <index>: marks task corresponding to index");
        System.out.println("unmark <index>: unmarks task corresponding to index");
        System.out.println("todo <description>: adds task type todo to list");
        System.out.println("deadline <description> /by <dd/mm/yyyy hhmm>: adds task type deadline to list");
        System.out.println("event <description> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyyy hhmm>: adds task type event to list");
        System.out.println("delete <index>: delete task corresponding to index");
        System.out.println("due <dd/mm/yyyy>: deletes task type description to list");
        System.out.println("find <keyword>: finds all tasks with keyword in description");
        System.out.println("quag: exit program");
        showLine();
    }
}
