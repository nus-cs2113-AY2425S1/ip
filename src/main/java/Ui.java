public class Ui {
    
    private final String LINE_SEPERATOR = "____________________________________________________________";
    
    // Print 2 line seperators between a block of text for cleaner CLI
    public void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    public void greet() {
        final String LOGO = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(LOGO);
        printBlock("Hi I'm V\nWhat can I do for you?");
    }

    public void printLineSeperator() {
        System.out.println(LINE_SEPERATOR);
    }

    public Ui() {
    }
}
