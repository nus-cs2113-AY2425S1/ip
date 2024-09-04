public class UI {
    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static void printContent(String content) {
        System.out.println(DIVIDER_LINE);
        System.out.println(content);
        System.out.println(DIVIDER_LINE);
    }
    public static void greetUser() {
        printContent("Hello! I'm Aerus!\nWhat can I do for you?");
    }

    public static void exitProgram() {
        printContent("See you next time!");
    }
}
