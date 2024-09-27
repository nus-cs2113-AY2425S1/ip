package luke;

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

    public void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printReply(String reply) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(reply);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGreeting() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("Hi im Luke!\nWhat can I do for you? :)");
        printDivider();
    }
}
