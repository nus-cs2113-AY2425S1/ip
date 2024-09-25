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

    public Ui() {
    }
}
