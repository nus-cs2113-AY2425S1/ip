package freedom.ui;

/**
 * Subclass of <code>Ui</code> for chatbot responses related to <code>Task</code>.
 */
public class UiTask extends Ui {

    /**
     * Indicates a task is already marked done.
     */
    public void printAlreadyDone() {
        printHeadDivider();
        System.out.println("\tAlready done :) Time for other things");
        printTailDivider();
    }

    /**
     * Indicates a task is already marked undone.
     */
    public void printAlreadyUndone() {
        printHeadDivider();
        System.out.println("\tYou have not done it yet? >:(");
        printTailDivider();
    }

    /**
     * Indicates task description provided is empty.
     */
    public void printEmptyDescriptionError() {
        printHeadDivider();
        System.out.println("\tDescription cannot be empty!!");
        printTailDivider();
    }

    /**
     * Indicates no deadline is provided.
     */
    public void printNoDeadline() {
        printHeadDivider();
        System.out.print("""
                \tYou need to set a date/time!
                \tRemember:
                \t  Use /by before the date/time
                \t  Include a date/time
                """);
        printTailDivider();
    }

    /**
     * Indicates no duration is provided.
     */
    public void printNoDuration() {
        printHeadDivider();
        System.out.print("""
                \tYou need to set a duration!
                \tRemember:
                \t  Use /from and /to before the date/time
                \t  Include a date/time span
                """);
        printTailDivider();
    }
}
