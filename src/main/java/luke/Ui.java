package luke;

/**
 * UI of the bot
 */
public class Ui {
    private static final String LOGO =
            "                              \n" +
                    ",--.           ,--.           \n" +
                    "|  |   ,--.,--.|  |,-. ,---.  \n" +
                    "|  |   |  ||  ||     /| .-. : \n" +
                    "|  '--.'  ''  '|  \\  \\\\   --. \n" +
                    "`-----' `----' `--'`--'`----' \n" +
                    "                              ";

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    /**
     * Prints horizontal line as divider
     */
    public void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a reply by printing divider before and after the reply
     * @param reply Reply to print
     */
    public void printReply(String reply) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(reply);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints welcome message
     */
    public void printGreeting() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("Hi im Luke!\nWhat can I do for you? :)");
        printDivider();
    }
}
