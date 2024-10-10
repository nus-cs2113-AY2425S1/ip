import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String BREAK_LINE = "____________________";
    private final String GREETING_LINE = BREAK_LINE + "\nHello! I'm DBot\nWhat can I do for you?\n" + BREAK_LINE;
    Scanner in;

    /**
     * Displays a greeting message when the Ui object is initialized.
     * Initializes the Scanner to read input from the user.
     */
    public Ui() {
        in = new Scanner(System.in);
        System.out.println(GREETING_LINE);
    }

    /**
     * Prints a short message surrounded by a break line to format the output.
     *
     * @param message the message to be printed
     */
    public void printShortMessage(String message) {
        System.out.println(BREAK_LINE + " Result " + BREAK_LINE);
        System.out.println(message);
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    /**
     * Prints a long message or a list of objects surrounded by a break line
     * to format the output. Each object is printed on a new line.
     *
     * @param list an array of objects to be printed
     */
    public void printLongMessage(Object[] list) {
        System.out.println(BREAK_LINE + " Result " + BREAK_LINE);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    private void printError(String message) {
        System.out.println(BREAK_LINE + " Error " + BREAK_LINE);
        System.out.println(message);
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    /**
     * Prints an error specific to DBot using the details from a DBotException.
     * The error includes the type of the error and its cause.
     *
     * @param error the DBotException containing the error type and message
     */
    public void printDBotError(DBotException error) {
        printError("Error: " + error.getErrorType() + "\nDue to: " + error.getMessage());
    }

    /**
     * Prints a generic error message for undocumented exceptions.
     * This is used to handle any unexpected or unhandled exceptions.
     *
     * @param e the Exception containing the error message
     */
    public void printGenericError(Exception e) {
        printError("Undocumented error occurred\nDue to: " + e.getMessage());
    }

    /**
     * Returns a formatted string representation of the list of tasks.
     * Each task is numbered and printed on a new line.
     *
     * @param tasks the list of tasks to be printed
     * @return a string containing the formatted list of tasks
     */
    public String listTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            sb.append('\n');
        }
        return sb.toString().trim();
    }

    /**
     * Reads a line of input from the user after printing a "Command:" prompt.
     *
     * @return the user's input as a string, with leading and trailing whitespace removed
     */
    public String readLine() {
        System.out.print("Command: ");
        return in.nextLine().strip();
    }

}
