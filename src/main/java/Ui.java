public class Ui {
    
    private final String LINE_SEPERATOR = "____________________________________________________________";
    
    /**
     * Prints a line seperator before and after the desired text
     * @param text String that is to be printed between 2 line seperators
     */
    public void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    /**
     * Greet function called when V starts
     */
    public void greet() {
        final String LOGO = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(LOGO);
        printBlock("Hi I'm V\nWhat can I do for you?");
    }

    /**
     * Prints a singular line seperator
     */
    public void printLineSeperator() {
        System.out.println(LINE_SEPERATOR);
    }

    /**
     * Prints NumberFormatException error message
     */
    public void printNumberFormatExceptionErrorMessage() {
        printBlock("You need to input a valid integer for the task that you want to mark as done");
    }

    /**
     * Prints InvalidDeadlineException error message
     */
    public void printInvalidDeadlineExceptionErrorMessage() {
        printBlock("You did not enter a valid deadline." + 
                    " Remember to add a \"/by\" before a valid deadline.");
    }

    /**
     * Prints InvalidEventException error message
     */
    public void printInvalidEventExceptionErrorMessage() {
        printBlock("You did not enter a valid event." + 
                    " Remember to add a \"/from\" before the start time" +
                    " and a \"/to\" before the end time\n" +
                    "Make sure the \"/from\" is before the \"/to\" ");
    }

    /**
     * Prints IndexOutOfBounds error message
     */
    public void printIndexOutOfBoundsErrorMessage() {
        printBlock("The position you inputted is out of bounds of the list");
    }

    public Ui() {
    }
}
